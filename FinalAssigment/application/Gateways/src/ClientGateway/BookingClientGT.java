package ClientGateway;

import java.util.HashMap;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import Chain.MessageReceiver;
import Chain.MessageSender;
import booking.model.client.ClientBookingReply;
import booking.model.client.ClientBookingRequest;

public abstract  class BookingClientGT {

	MessageSender sender; 
	 MessageReceiver reciever;
	 ClientSerializer serializer;
	String Channel="ClientToAgency";
	String Channel1="AgencyToClient";
	HashMap<String,ClientBookingRequest> Hash ;
	
	
	public BookingClientGT(String Channel){
		this.Channel=Channel;
		try {
			sender=new MessageSender(Channel1);
			reciever=new MessageReceiver(Channel );
			serializer=new ClientSerializer();
		} catch (JMSException e1) {
			e1.printStackTrace();
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
	
		Hash=new HashMap<String, ClientBookingRequest>();
		
		reciever.setListener(new MessageListener(){
				@Override
				public void onMessage(Message msg) {
				try {
					if(Hash.containsKey(msg.getJMSCorrelationID()))		
					{				
						ClientBookingReply reply=serializer.replyFromSTring(((TextMessage)msg).getText());
						try {
						onBookingReplyArrived(Hash.get(msg.getJMSCorrelationID()) , reply);
						} catch (JMSException e) {	
						}
						}
				} catch (JMSException e) {	
				}
					
				}
		});
	}
	public void applyForBooking(ClientBookingRequest loan){
		
	
		String MsgId="";
		try {
			MsgId = sender.Send(sender.createTextMessage(serializer.requestToString(loan)));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Hash.put(MsgId,loan);
		System.out.println("ClienSend: "+MsgId);
		
		
		
	} 
	public abstract void onBookingReplyArrived(ClientBookingRequest request, ClientBookingReply reply);
		
	
	
}