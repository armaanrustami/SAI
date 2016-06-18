package booking.app.agency;

import java.awt.EventQueue;

import AgencyGateway.AgencyGT;
import booking.model.agency.AgencyReply;
import booking.model.agency.AgencyRequest;


public class StartAllAgencies {
	static AgencyGT agency;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingAgencyFrame  frame = new BookingAgencyFrame ("Book Fast",
							"bookFastQueue");
					
					 agency=new AgencyGT("bookFastQueue") {
							
							@Override
							public void onAgencyReplyArrived(AgencyReply reply, AgencyRequest request) {
								System.out.print(request);
								//todo
								frame.addRequest(new BookingAgencyListLine(request, reply));
							}
						};
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				/*	BookingAgencyFrame  frame = new BookingAgencyFrame ("Book Cheap",
							"bookCheapQueue");*/
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//BookingAgencyFrame  frame = new BookingAgencyFrame ("Book Good Service",
				//			"bookGoodServiceQueue");
				//	frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
