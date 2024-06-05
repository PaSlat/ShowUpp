package showupp;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JPanel;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	    frame = new JFrame();
	    frame.getContentPane().setBackground(new Color(250, 250, 250));
	    frame.setBackground(new Color(34, 34, 34));
	    frame.setBounds(100, 100, 991, 591);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);
	
	    JButton btnIspisEvenata = new JButton("Ispis evenata");
	    btnIspisEvenata.setForeground(new Color(255, 255, 255));
	    btnIspisEvenata.setBackground(new Color(100, 100, 100));
	    btnIspisEvenata.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    btnIspisEvenata.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            DlgIspisEvenata dlg = new DlgIspisEvenata();
	            dlg.setVisible(true);
	        }
	    });
	    btnIspisEvenata.setBounds(595, 374, 138, 60);
	    frame.getContentPane().add(btnIspisEvenata);
	
	    JLabel lblImage1 = new JLabel("Slika1");
	    Image img2 = new ImageIcon(this.getClass().getResource("/png.png")).getImage();
	    lblImage1.setIcon(new ImageIcon(img2));
	    lblImage1.setBounds(175, 25, 659, 198);
	    lblImage1.setOpaque(true);
	    lblImage1.setBackground(new Color(250, 250, 250));
	    frame.getContentPane().add(lblImage1);
	
	    JButton UpisGumb = new JButton("Upis evenata");
	    UpisGumb.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    UpisGumb.setForeground(new Color(255, 255, 255));
	    UpisGumb.setBackground(new Color(100, 100, 100));
	    UpisGumb.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            LogIn dlg = new LogIn();
	            dlg.setVisible(true);
	        }
	    });
	    UpisGumb.setBounds(258, 374, 138, 60);
	    frame.getContentPane().add(UpisGumb);
	}
