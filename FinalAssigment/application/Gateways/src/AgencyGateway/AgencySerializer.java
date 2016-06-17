package AgencyGateway;

import com.owlike.genson.Genson;

import booking.model.agency.AgencyReply;
import booking.model.agency.AgencyRequest;
import booking.model.client.ClientBookingReply;
import booking.model.client.ClientBookingRequest;

public class AgencySerializer {

	

    Genson genson;
    public AgencySerializer() {
        this.genson = new Genson();
    } 
    public String requestToString(ClientBookingRequest request) {
        return genson.serialize(request);
    }
    public AgencyRequest requestFromString(String str) {
    	return genson.deserialize(str, AgencyRequest.class);
    }
    
    public String replyToString(AgencyReply reply){
       
			return genson.serialize(reply);
		
    }
    
    public AgencyReply replyFromString(String str){
    	return genson.deserialize(str, AgencyReply.class);
    	
    	
    }
    
    public ClientBookingReply replyFromSTring(String str){
        return genson.deserialize(str, ClientBookingReply.class);
    }



	
}
