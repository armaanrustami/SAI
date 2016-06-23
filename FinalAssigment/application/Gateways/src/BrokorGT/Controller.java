package BrokorGT;

import java.io.IOException;
import java.util.HashMap;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.NamingException;

import AgencyGateway.AgencyGT;
import AgencyGateway.AgencySerializer;
import ClientGateway.ClientSerializer;
import booking.model.agency.AgencyReply;
import booking.model.agency.AgencyRequest;
import booking.model.client.ClientBookingReply;
import booking.model.client.ClientBookingRequest;
import googleApi.DistanceFromGoogle;
import sender_Receiver.MessageReceiver;
import sender_Receiver.MessageSender;

public abstract class Controller {

    MessageSender clientSender, fastAgencySender, goodAgencySender, cheapAgencySender;
    MessageReceiver Clientreciever, AgencyReceiver;
    ClientSerializer serializer;
    AgencySerializer agencySerializer;
    DistanceFromGoogle googleDistance;
    AgencyGT agency;
    HashMap<String, ClientBookingRequest> Hash = new HashMap<>();

    public Controller() {

        try {
            googleDistance = new DistanceFromGoogle();
            clientSender = new MessageSender("ToClient");
            fastAgencySender = new MessageSender("bookFastQueue");
            goodAgencySender = new MessageSender("bookGoodServiceQueue");
            cheapAgencySender = new MessageSender("bookCheapQueue");
            Clientreciever = new MessageReceiver("FromClient");
            AgencyReceiver = new MessageReceiver("ToAgency");
            serializer = new ClientSerializer();
            agencySerializer = new AgencySerializer();

        } catch (JMSException e1) {
            e1.printStackTrace();
        } catch (NamingException e1) {
            e1.printStackTrace();
        }

        Clientreciever.setListener(new MessageListener() {

            @Override
            public void onMessage(Message arg0) {
                // TODO Auto-generated method stub
                try {
                    Double Distance = 0.0;
                    AgencyRequest angencyRequest;
                    ClientBookingRequest request = serializer.requestFromString(((TextMessage) arg0).getText());
                    onBookingRequestArrived(request, null);
                    arg0.setJMSCorrelationID(arg0.getJMSMessageID());
                    if (request.getTransferToAddress() != null) {
                        String origin = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
                                + request.getOriginAirport() + "&destinations=" + request.getDestinationAirport()
                                + "&mode=flights&language=en-FR&key=";
                        Distance = Double.parseDouble(
                                googleDistance.run(request.getOriginAirport(), request.getDestinationAirport()));
                        angencyRequest = new AgencyRequest(request.getDestinationAirport(), request.getOriginAirport(),
                                Distance);
                        if (Distance >= 10 && Distance <= 50) {
                            cheapAgencySender.Send(
                                    cheapAgencySender
                                            .createTextMessage(agencySerializer.agencyRequestToString(angencyRequest)),
                                    arg0.getJMSMessageID());
                        }
                        if (Distance <= 40) {
                            goodAgencySender.Send(
                                    goodAgencySender
                                            .createTextMessage(agencySerializer.agencyRequestToString(angencyRequest)),
                                    arg0.getJMSMessageID());
                        }
                    } else {
                        angencyRequest = new AgencyRequest(request.getDestinationAirport(), request.getOriginAirport(),
                                Distance);
                        fastAgencySender.Send(
                                fastAgencySender
                                        .createTextMessage(agencySerializer.agencyRequestToString(angencyRequest)),
                                arg0.getJMSMessageID());
                        goodAgencySender.Send(
                                goodAgencySender
                                        .createTextMessage(agencySerializer.agencyRequestToString(angencyRequest)),
                                arg0.getJMSMessageID());
                    }
                    if (!Hash.containsKey(arg0.getJMSMessageID())) {
                        Hash.put(arg0.getJMSMessageID(), request);
                    }

                } catch (JMSException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        AgencyReceiver.setListener(new MessageListener() {

            @Override
            public void onMessage(Message arg0) {
                // TODO Auto-generated method stub
                try {
                    AgencyReply reply = agencySerializer.replyFromString(((TextMessage) arg0).getText());
                    onBookingReplyArrived(Hash.get(arg0.getJMSCorrelationID()), reply);
                    ClientBookingReply clientReply = new ClientBookingReply(reply.getNameAgency(), reply.getTotalPrice());
                    clientSender.Send(clientSender.createTextMessage(serializer.replyToString(clientReply)), arg0.getJMSCorrelationID());

                } catch (JMSException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

    }

    public abstract void onBookingReplyArrived(ClientBookingRequest request, AgencyReply reply);

    public abstract void onBookingRequestArrived(ClientBookingRequest request, AgencyReply reply);

}
