package AgencyGateway;

import java.util.HashMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.NamingException;

import booking.model.agency.AgencyReply;
import booking.model.agency.AgencyRequest;
import booking.model.client.ClientBookingRequest;
import sender_Receiver.MessageReceiver;
import sender_Receiver.MessageSender;

public abstract class AgencyGT {

	MessageReceiver Reciever;
	MessageSender sender;
	AgencySerializer serializer;
	HashMap<AgencyRequest, String>	Hash=new HashMap<>();
	public AgencyGT(String Send) {
		// sender=new MessageSenderGateway(ChannelName)
		try {
			
			// todo
			
			Reciever = new MessageReceiver(Send);
			sender = new MessageSender("ToAgency");
			serializer = new AgencySerializer();
		} catch (JMSException | NamingException e) {
			e.printStackTrace();
		}
		Reciever.setListener(new MessageListener() {

			@Override
			public void onMessage(Message arg0) {
				// TODO Auto-generated method stub

				try {
					System.out.println("DONE");
					ClientBookingRequest Reques = serializer.requestFromString(((TextMessage) arg0).getText());
					AgencyRequest Req=new AgencyRequest(Reques.getDestinationAirport(), Reques.getOriginAirport(), Reques.getNumberOfTravellers());
					onAgencyRequestArrived(null, Req);
					
					System.out.println(arg0.getJMSCorrelationID());
					if(!Hash.containsValue(arg0.getJMSCorrelationID())){
					Hash.put(Req, arg0.getJMSCorrelationID());}
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

	}

	public abstract void onAgencyRequestArrived(AgencyReply reply, AgencyRequest request);

	public void onAgencyReply(AgencyReply reply, AgencyRequest request) {

		try {
			sender.createTextMessage(serializer.replyToString(reply));
			
			System.out.println( Hash.get(request));
			sender.Send(sender.createTextMessage(serializer.replyToString(reply)), Hash.get(request));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
