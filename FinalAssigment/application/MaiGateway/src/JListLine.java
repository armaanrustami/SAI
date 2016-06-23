
import booking.model.agency.AgencyReply;
import booking.model.agency.AgencyRequest;
import booking.model.client.ClientBookingRequest;


/**
 * This class represents one line in the JList in Loan Broker.
 * This class stores all objects that belong to one LoanRequest:
 * - LoanRequest,
 * - BankInterestRequest, and
 * - BankInterestReply.
 * Use objects of this class to add them to the JList.
 */
class JListLine {

    private ClientBookingRequest Request;
    private AgencyRequest agencyRequest;
    private AgencyReply agencyReply;

    public JListLine(ClientBookingRequest Request) {
        this.setRequest(Request);
    }

    public ClientBookingRequest getRequest() {
        return Request;
    }

    public void setRequest(ClientBookingRequest Request) {
        this.Request = Request;
    }

    public AgencyRequest getAgencyRequest() {
        return agencyRequest;
    }

    public void setAgencyRequest(AgencyRequest agencyRequest) {
        this.agencyRequest = agencyRequest;
    }

    public AgencyReply getAgencyReply() {
        return agencyReply;
    }

    public void setAgencyReply(AgencyReply agencyReply) {
        this.agencyReply = agencyReply;
    }

    @Override
    public String toString() {
        return Request.toString() + " || " + ((agencyReply != null) ? agencyReply.toString() : "waiting for reply...");
    }

}
