package showupp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Color;
import java.text.ParseException;
import java.util.Calendar;

public class UpisEvenata extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField Ime;
    private JTextField Vrijeme;
    private JTextArea VrijemeEventa;
    private JComboBox<String> Dropdown; 

 
    public static void main(String[] args) {
        try {
            UpisEvenata dialog = new UpisEvenata();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
    public UpisEvenata() {
        setBounds(100, 100, 456, 413);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        Ime = new JTextField();
        Ime.setBounds(152, 136, 143, 26);
        contentPanel.add(Ime);
        Ime.setColumns(10);

        Vrijeme = new JTextField();
        Vrijeme.setBounds(152, 204, 143, 26);
        contentPanel.add(Vrijeme);
        Vrijeme.setColumns(10);

        JTextArea ImeEventa = new JTextArea();
        ImeEventa.setBounds(152, 105, 143, 26);
        ImeEventa.setFont(new Font("Tahoma", Font.PLAIN, 17));
        ImeEventa.setEditable(false);
        ImeEventa.setText("Ime Eventa:");
        contentPanel.add(ImeEventa);

        VrijemeEventa = new JTextArea();
        VrijemeEventa.setBounds(152, 174, 143, 26);
        VrijemeEventa.setFont(new Font("Tahoma", Font.PLAIN, 17));
        VrijemeEventa.setEditable(false);
        VrijemeEventa.setText("Vrijeme Eventa:");
        contentPanel.add(VrijemeEventa);

        Dropdown = new JComboBox<>();
        Dropdown.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Dropdown.setBounds(152, 275, 143, 26);
        Dropdown.addItem("Festival");
        Dropdown.addItem("Koncert");
        Dropdown.addItem("Techno");
        Dropdown.addItem("Lokalni");
        Dropdown.addItem("Komedija");
        Dropdown.addItem("Ostalo");
        contentPanel.add(Dropdown);

        JTextArea TipEventa = new JTextArea();
        TipEventa.setEditable(false);
        TipEventa.setFont(new Font("Tahoma", Font.PLAIN, 17));
        TipEventa.setText("Tip Eventa:");
        TipEventa.setBounds(152, 241, 143, 23);
        contentPanel.add(TipEventa);

        JTextArea txtrUpisEvenata = new JTextArea();
        txtrUpisEvenata.setEditable(false);
        txtrUpisEvenata.setForeground(new Color(128, 0, 255));
        txtrUpisEvenata.setFont(new Font("Tahoma", Font.PLAIN, 30));
        txtrUpisEvenata.setText("Upis Evenata");
        txtrUpisEvenata.setBounds(131, 29, 178, 41);
        contentPanel.add(txtrUpisEvenata);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(new Color(255, 255, 255));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        upisEvenata();
                    }
                });
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

    void upisEvenata() {
        try {
            String VrijemeEventa = Vrijeme.getText();
            String ImeEventa = Ime.getText();
            String TipEventa = (String) Dropdown.getSelectedItem();
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd.MM", Locale.ENGLISH);
            Date parsedDate = inputFormat.parse(VrijemeEventa);
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            calendar.setTime(parsedDate);
            calendar.set(Calendar.YEAR, currentYear);

            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = outputFormat.format(calendar.getTime());

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/ibistricki?" +
                    "user=ibistricki&password=11");
            String sql = "INSERT INTO Event (naziv_eventa, vrijeme_eventa, tip_eventa) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ImeEventa);
            pstmt.setString(2, formattedDate);
            pstmt.setString(3, TipEventa);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Uspješno ste unijeli event!");
            }

            conn.close();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Pogreška pri unosu podataka", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Pogreška pri unosu podataka", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
