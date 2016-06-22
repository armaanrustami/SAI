package sender_Receiver;

import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class MessageSender {

	Connection connection;
	Destination destination;
	MessageProducer producer;
	Session session;
	public MessageSender(String ChannelName) throws JMSException, NamingException{
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
		props.put(("queue."+ChannelName), ChannelName);
		Context jndiContext = new InitialContext(props);
		ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
		connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// connect to the sender destination
		destination = (Destination) jndiContext.lookup(ChannelName);
		producer = session.createProducer(destination);
		connection.start();
	}
	
	public Message createTextMessage(String body) throws JMSException{
			
		return session.createTextMessage(body);
	}
	
	public String Send(Message msg) throws JMSException{
		
		producer.send(msg);
		return msg.getJMSMessageID();
		
	}public void Send(Message msg, String ID) throws JMSException{
		msg.setJMSCorrelationID(ID);
		producer.send(msg);
				
	}
	
	
}