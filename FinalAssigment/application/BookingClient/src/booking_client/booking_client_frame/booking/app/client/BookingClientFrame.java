/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booking_client.booking_client_frame.booking.app.client;

import javax.swing.DefaultListModel;
import booking.model.client.Address;
import booking.model.client.ClientBookingReply;
import booking.model.client.ClientBookingRequest;
import javax.swing.JCheckBox;

import ClientGateway.BookingClientGT;


/**
 *
 * @author mpesic
 */

public class BookingClientFrame extends javax.swing.JFrame {
	  BookingClientGT gateway;

    private DefaultListModel<ClientListLine> listModel = new DefaultListModel<>();


    /**
     * Creates new form NewJFrame
     */
    public BookingClientFrame() {
    	gateway=new BookingClientGT( ) {
			
			@Override
			public void onBookingReplyArrived(ClientBookingRequest request, ClientBookingReply reply) {
				// TODO Auto-generated method stub
				 for (int i = 0; i < listModel.getSize(); i++) {
			            ClientListLine rr = listModel.get(i);
			            if (rr.getRequest() == request) {
			               listModel.remove(i);
			               listModel.insertElementAt(new ClientListLine(request,reply), i);;return;
			            }}
				
			}
		};
        initComponents();
        setTransfer(this.jcbTransfer.isSelected());
    }

    private ClientListLine getRequestReply(ClientBookingRequest request) {

        for (int i = 0; i < listModel.getSize(); i++) {
            ClientListLine rr = listModel.get(i);
            if (rr.getRequest() == request) {
                return rr;
            }
        }

        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfFromAirport = new javax.swing.JTextField();
        tfToAirport = new javax.swing.JTextField();
        tfNrTravellers = new javax.swing.JTextField();
        tfTransferStreet = new javax.swing.JTextField();
        tfTransferHouseNumber = new javax.swing.JTextField();
        tfTransferCity = new javax.swing.JTextField();
        jbSend = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jcbTransfer = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Booking Client");
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        getContentPane().setLayout(layout);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("FLIGHT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel3.setText("from airport");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jLabel3, gridBagConstraints);

        jLabel4.setText("street");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jLabel4, gridBagConstraints);

        jLabel5.setText("to airport");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jLabel5, gridBagConstraints);

        jLabel6.setText("number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jLabel6, gridBagConstraints);

        jLabel7.setText("number of travellers");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jLabel7, gridBagConstraints);

        jLabel8.setText("city");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jLabel8, gridBagConstraints);

        tfFromAirport.setText("Schiphol");
        tfFromAirport.setName(""); // NOI18N
        tfFromAirport.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(tfFromAirport, gridBagConstraints);

        tfToAirport.setText("Heathrow");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(tfToAirport, gridBagConstraints);

        tfNrTravellers.setText("3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(tfNrTravellers, gridBagConstraints);

        tfTransferStreet.setText("Portman Square");
        tfTransferStreet.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(tfTransferStreet, gridBagConstraints);

        tfTransferHouseNumber.setText("30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(tfTransferHouseNumber, gridBagConstraints);

        tfTransferCity.setText("London");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(tfTransferCity, gridBagConstraints);

        jbSend.setText("send booking request");
        jbSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSendActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jbSend, gridBagConstraints);

        jList1.setModel(listModel);
        jScrollPane2.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 30;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane2, gridBagConstraints);

        jcbTransfer.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jcbTransfer.setText("TRANSFER ADDRESS");
        jcbTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTransferActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jcbTransfer, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSendActionPerformed
        String fromAirport = tfFromAirport.getText();
        String toAirport = tfToAirport.getText();
        int nrTravellers = Integer.parseInt(this.tfNrTravellers.getText());

        Address transferAddress = null;
        if (this.jcbTransfer.isSelected()) {
            String street = tfTransferStreet.getText();
            int number = Integer.parseInt(tfTransferHouseNumber.getText());
            String city = tfTransferCity.getText();
            transferAddress = new Address(street, number, city);
        }

        ClientBookingRequest request = new ClientBookingRequest(fromAirport, toAirport, nrTravellers, transferAddress);
        gateway.applyForBooking(request);
        listModel.addElement(new ClientListLine(request, null));
    }//GEN-LAST:event_jbSendActionPerformed

    private void setTransfer(boolean withTransfer) {
        this.tfTransferStreet.setEnabled(withTransfer);
        this.tfTransferHouseNumber.setEnabled(withTransfer);
        this.tfTransferCity.setEnabled(withTransfer);
    }
    private void jcbTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTransferActionPerformed
        JCheckBox cbTransfer = (JCheckBox) evt.getSource();
        setTransfer(cbTransfer.isSelected());
    }//GEN-LAST:event_jcbTransferActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookingClientFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<ClientListLine> jList1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbSend;
    private javax.swing.JCheckBox jcbTransfer;
    private javax.swing.JTextField tfFromAirport;
    private javax.swing.JTextField tfNrTravellers;
    private javax.swing.JTextField tfToAirport;
    private javax.swing.JTextField tfTransferCity;
    private javax.swing.JTextField tfTransferHouseNumber;
    private javax.swing.JTextField tfTransferStreet;
    // End of variables declaration//GEN-END:variables
}
