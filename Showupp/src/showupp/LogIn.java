package showupp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class LogIn extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField KorIme;
	private JTextField LozinkaPolje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LogIn dialog = new LogIn();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LogIn() {
		getContentPane().setBackground(new Color(255, 255, 255));
		
		setBounds(100, 100, 580, 383);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		KorIme = new JTextField();
		KorIme.setBounds(208, 167, 208, 29);
		contentPanel.add(KorIme);
		KorIme.setColumns(10);
		
		LozinkaPolje = new JTextField();
		LozinkaPolje.setBounds(208, 235, 208, 29);
		contentPanel.add(LozinkaPolje);
		LozinkaPolje.setColumns(10);
		
		JTextArea txtrIme = new JTextArea();
		txtrIme.setEditable(false);
		txtrIme.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtrIme.setText("Korisničko Ime:");
		txtrIme.setBounds(74, 166, 120, 29);
		contentPanel.add(txtrIme);
		
		JTextArea txtrLozinka = new JTextArea();
		txtrLozinka.setEditable(false);
		txtrLozinka.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtrLozinka.setText("Lozinka:");
		txtrLozinka.setBounds(125, 234, 66, 29);
		contentPanel.add(txtrLozinka);
		
		JLabel SlikaLokot = new JLabel("");
		Image lokot = new ImageIcon(this.getClass().getResource("/lokot.png")).getImage();
		SlikaLokot.setIcon(new ImageIcon(lokot));
		SlikaLokot.setOpaque(true);
		SlikaLokot.setBackground(new Color(255, 255, 255)); 
		SlikaLokot.setBounds(258, 53, 52, 64);
		contentPanel.add(SlikaLokot);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	provjeraAdmina();
		            }
		        });
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	void provjeraAdmina() {
		 try {
		        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		        Connection conn = 
		      DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/ibistricki?" +
		      "user=ibistricki&password=11");

		      String sql = "SELECT * FROM Admin";
		      Statement stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      String imeAdmin = KorIme.getText();
		      String lozinkaAdmin = LozinkaPolje.getText();
		      while (rs.next()) {
	                String korisnik = rs.getString("Korisnik");
	                String lozinka = rs.getString("lozinka");
	                if (korisnik.equals(imeAdmin) && lozinka.equals(lozinkaAdmin)){ 
	                	UpisEvenata dlg = new UpisEvenata();
	                    dlg.setVisible(true);
	                }}
	                
		      
		      conn.close();
		    
		} catch(Exception ex) {
		        JOptionPane.showMessageDialog(null, ex.getMessage(),"Greška",
		    JOptionPane.ERROR_MESSAGE);
		    }
	}
}
