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
    public ClientBookingRequest requestFromString(String str) {
    	return genson.deserialize(str, ClientBookingRequest.class);
    }
    
    public String replyToString(AgencyReply reply){
       
			return genson.serialize(reply);
		
    }
    public String agencyRequestToString(AgencyRequest request){
        return genson.serialize(request);
    }
    public AgencyRequest agencyRequestFromString(String request){
        return  genson.deserialize(request, AgencyRequest.class);
    }
    
    public AgencyReply replyFromString(String str){
    	return genson.deserialize(str, AgencyReply.class);
    	
    	
    }
    
    public ClientBookingReply replyFromSTring(String str){
        return genson.deserialize(str, ClientBookingReply.class);
    }



	
}
