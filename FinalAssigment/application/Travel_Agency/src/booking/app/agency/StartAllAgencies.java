package booking.app.agency;

import java.awt.EventQueue;

import AgencyGateway.AgencyGT;
import booking.model.agency.AgencyReply;
import booking.model.agency.AgencyRequest;

public class StartAllAgencies {
	static AgencyGT Fast, Good, Cheap;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingAgencyFrame frame = new BookingAgencyFrame("Book Fast", "bookFastQueue");

					Fast = new AgencyGT("ToFastAgency") {

						@Override
						public void onAgencyReplyArrived(AgencyReply reply, AgencyRequest request) {
							System.out.print(request);
							// todo
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
					BookingAgencyFrame frame = new BookingAgencyFrame("Book Cheap", "bookCheapQueue");
					frame.setVisible(true);
					Cheap = new AgencyGT("ToCheapAgency") {

						@Override
						public void onAgencyReplyArrived(AgencyReply reply, AgencyRequest request) {
							System.out.print(request);
							// todo
							frame.addRequest(new BookingAgencyListLine(request, reply));
						}
					};
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 BookingAgencyFrame frame = new BookingAgencyFrame ("BookGood Service",
					 "bookGoodServiceQueue");
					 frame.setVisible(true);
					 Good = new AgencyGT("ToGoodAgency") {

							@Override
							public void onAgencyReplyArrived(AgencyReply reply, AgencyRequest request) {
								System.out.print(request);
								// todo
								frame.addRequest(new BookingAgencyListLine(request, reply));
							}
						};
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
