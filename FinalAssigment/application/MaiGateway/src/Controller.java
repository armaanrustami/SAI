

import java.util.HashMap;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import Chain.MessageReceiver;
import Chain.MessageSender;
import ClientGateway.ClientSerializer;
import booking.model.agency.AgencyReply;
import booking.model.client.ClientBookingReply;
import booking.model.client.ClientBookingRequest;

public abstract class Controller {

	MessageSender clientSender,fastAgencySender,goodAgencySender,cheapAgencySender;
	MessageReceiver Clientreciever;
	ClientSerializer serializer;
	HashMap<Message,ClientBookingRequest> Hash;
	
	public Controller() {

		try {
			clientSender = new MessageSender("ToClient");
			fastAgencySender = new MessageSender("ToFastAgency");
			goodAgencySender= new MessageSender("ToGoodAgency");
			cheapAgencySender= new MessageSender("ToCheapAgency");
			Clientreciever = new MessageReceiver("ToGateway");
			serializer = new ClientSerializer();
			Hash=new HashMap<>();
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
					ClientBookingRequest request=serializer.requestFromString(((TextMessage) arg0).getText());
					onBookingReplyArrived(request ,  null);
					
					Hash.put(arg0, request);
					fastAgencySender.Send(arg0, arg0.getJMSMessageID());
					goodAgencySender.Send(arg0, arg0.getJMSMessageID());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}

	public void applyForBooking(ClientBookingRequest req) {

		String MsgId = "";
		try {
			
			MsgId = clientSender.Send(clientSender.createTextMessage(serializer.requestToString(req)));
			//Hash.put(MsgId, req);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("ClienSend: " + MsgId);

	}

	public abstract void onBookingReplyArrived(ClientBookingRequest request, AgencyReply reply);

}
