import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import BrokorGT.Controller;
import booking.model.agency.AgencyReply;
import booking.model.agency.AgencyRequest;
import booking.model.client.ClientBookingReply;
import booking.model.client.ClientBookingRequest;

public class GTFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static DefaultListModel<JListLine> listModel = new DefaultListModel<JListLine>();
    private static JList<JListLine> list;
    static Controller gt;
    static HashMap<String, ClientBookingRequest> Hash = new HashMap<>();

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GTFrame frame = new GTFrame();
                    gt = new Controller() {

                        @Override
                        public void onBookingRequestArrived(ClientBookingRequest request, AgencyReply reply) {
                            // TODO Auto-generated method stub

                            add(request);
                            add(request, reply);

                        }

                        @Override
                        public void onBookingReplyArrived(ClientBookingRequest request, AgencyReply reply) {
                            // TODO Auto-generated method stub

                            add(request, reply);
                        }
                    };

                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public GTFrame() {
        setTitle("Gateway");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{46, 31, 86, 30, 89, 0};
        gbl_contentPane.rowHeights = new int[]{233, 23, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 7;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        contentPane.add(scrollPane, gbc_scrollPane);

        list = new JList<JListLine>(listModel);
        scrollPane.setViewportView(list);
    }

    private static JListLine getRequestReply(ClientBookingRequest request) {

        for (int i = 0; i < listModel.getSize(); i++) {
            JListLine rr = listModel.get(i);
            if (rr.getRequest() == request) {
                return rr;
            }
        }

        return null;
    }

    public static void add(ClientBookingRequest Request) {
        listModel.addElement(new JListLine(Request));
    }

    public void add(ClientBookingRequest Request, AgencyRequest agencyRequest) {
        JListLine rr = getRequestReply(Request);

        if (rr != null && agencyRequest != null) {
            rr.setAgencyRequest(agencyRequest);
            list.repaint();
        }
    }

    public static void add(ClientBookingRequest Request, AgencyReply agencyReply) {
        JListLine rr = getRequestReply(Request);
        if (rr != null && agencyReply != null) {
            rr.setAgencyReply(agencyReply);
            ;
            list.repaint();
        }
    }

}
