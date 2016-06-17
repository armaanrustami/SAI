package ClientGateway;

import com.owlike.genson.Genson;

import booking.model.client.ClientBookingReply;
import booking.model.client.ClientBookingRequest;

public class ClientSerializer {
    Genson genson;

    public ClientSerializer() {
        this.genson = new Genson();
    }
    
    public String requestToString(ClientBookingRequest request) {
        return genson.serialize(request);
    }
    
    public ClientBookingRequest requestFromString(String str) {
        return genson.deserialize(str, ClientBookingRequest.class);
    }
    
    public String replyToString(ClientBookingReply reply){
       
			return genson.serialize(reply);
		
    }
    
    public ClientBookingReply replyFromSTring(String str){
        return genson.deserialize(str, ClientBookingReply.class);
    }
}
