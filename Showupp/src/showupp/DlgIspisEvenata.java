package showupp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.sql.*;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.text.SimpleDateFormat;

public class DlgIspisEvenata extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel contentPanel = new JPanel();
    private JPanel eventPanel = new JPanel();

    
    public static void main(String[] args) {
        try {
            DlgIspisEvenata dialog = new DlgIspisEvenata();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public DlgIspisEvenata() {
        setBounds(100, 100, 1057, 712);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel lblNewLabel = new JLabel("Nadolazeći Eventi");
        lblNewLabel.setBounds(390, 11, 283, 114);
        lblNewLabel.setForeground(new Color(128, 0, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
        contentPanel.add(lblNewLabel);
        eventPanel.setBackground(Color.WHITE);
        eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(eventPanel);
        scrollPane.setBounds(20, 120, 1000, 500);
        contentPanel.add(scrollPane);

        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(new Color(255, 255, 255));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);

        JTextArea txtrHvalaVamto = new JTextArea();
        txtrHvalaVamto.setEditable(false);
        txtrHvalaVamto.setForeground(new Color(128, 0, 255));
        txtrHvalaVamto.setFont(new Font("Tahoma", Font.PLAIN, 17));
        txtrHvalaVamto.setText("Hvala Vam što koristite ShowUpp!");
        txtrHvalaVamto.setBounds(83, 642, 257, 31);
        contentPanel.add(txtrHvalaVamto);

        selectIspisEvenata();
    }

    void selectIspisEvenata() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/ibistricki?" +
                    "user=ibistricki&password=11");

            String sql = "SELECT * FROM Event";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd.MM.yyyy");

            while (rs.next()) {
                JTextArea eventTextArea = new JTextArea();
                eventTextArea.setEditable(false);
                eventTextArea.setForeground(new Color(128, 0, 255));
                eventTextArea.setFont(new Font("Tahoma", Font.PLAIN, 17));

                String nazivEventa = rs.getString("naziv_eventa");
                String datumDogadjanja = rs.getString("vrijeme_eventa");
                String tipEventa = rs.getString("tip_eventa");

                java.util.Date date = dbDateFormat.parse(datumDogadjanja);
                String formattedDate = displayDateFormat.format(date);

                String tekst = "Naziv Eventa: " + nazivEventa + "\n" +
                               "Datum događanja: " + formattedDate + "\n" +
                               "Tip Eventa: " + tipEventa + "\n";
                eventTextArea.setText(tekst);
                eventPanel.add(eventTextArea);
            }
            eventPanel.revalidate();
            eventPanel.repaint();

            conn.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}
