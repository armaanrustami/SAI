package AgencyGateway;

import java.util.HashMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.NamingException;

import Chain.MessageReceiver;
import Chain.MessageSender;
import booking.model.agency.AgencyReply;
import booking.model.agency.AgencyRequest;
import booking.model.client.ClientBookingRequest;

public abstract class AgencyGT {

	MessageReceiver Reciever;
	//MessageSender sender;

	AgencySerializer serializer;
	HashMap<AgencyRequest, String> Hash;

	public AgencyGT(String Channel) {
		// sender=new MessageSenderGateway(ChannelName)
		try {
		
			// todo
			Reciever = new MessageReceiver(Channel);
			//sender = new MessageSender("AgencyToClient");
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
					onAgencyReplyArrived(null, Req);
					//Hash.put(Reques, arg0.getJMSCorrelationID());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

	}

	public abstract void onAgencyReplyArrived(AgencyReply reply, AgencyRequest request);

	public void onAgencyReply(AgencyReply reply, AgencyRequest request) {

		/*try {
		//	sender.createTextMessage(serializer.replyToString(reply));
			//sender.Send(sender.createTextMessage(serializer.replyToString(reply)), Hash.get(request));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

}
