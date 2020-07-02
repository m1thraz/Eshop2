package shop.local.ui.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import shop.local.domain.EShop;
import shop.local.domain.exceptions.LoginUngueltigException;
import shop.local.valueobjects.Benutzer;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.Mitarbeiter;

public class EinloggenPanel extends JFrame{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EShop shop;

    private JTextField text1;
    private JTextField text2;
    private Benutzer aktuellerBenutzer;
    
	public EinloggenPanel(EShop shop) {
		 this.shop=shop;	    
		 initialize();
		
	 }
	
	private void initialize() {
		//Textfeld von Loginname
	     text1 = new JTextField();
	    this.add(text1);
	    text1.setBounds(110,20, 100,30); 
		
		//Textfeld von Passwort
		 text2 = new JPasswordField();
		this.add(text2);
	    text2.setBounds(110,75,100,30); 
	    
	    //Label "Loginname"
	    JLabel l1=new JLabel("Loginname:");    
	    this.add(l1);
	    l1.setBounds(20,20, 80,30);    
	    
	    //Label "Passwort"
	    JLabel l2=new JLabel("Passwort:");    
	    this.add(l2); 
	    l2.setBounds(20,75, 80,30);    
	    
	    //Login Button Mitarbeiter
	    JButton b = new JButton("Login M"); 
	    this.add(b);
	    b.setBounds(50,140, 80,30);
	    b.addActionListener(e6 -> verarbeiteLoginMKlick());
	    
	    //Login Button Kunde
	    JButton c = new JButton("Login K"); 
	    this.add(c);
	    c.setBounds(150,140, 80,30);
	    c.addActionListener(e6 -> verarbeiteLoginKKlick());
	    
	    //Login Button Kunde
	    JButton a = new JButton("Registrieren"); 
	    this.add(a);
	    a.setBounds(90,180, 110,30);
	    a.addActionListener(e -> verarbeiteRegistrierenKlick());
	                         
	    this.setSize(300,300); 
	    this.setLayout(null); //keinen LayoutManager benutzen
	    this.setVisible(true);//setzt den Frame auf sichtbar
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Damit wird der Frame Beendet  
	    setLocationRelativeTo(null);
		
	}
	
	private void verarbeiteRegistrierenKlick(){		
		this.dispose();				
	}
	
	private void verarbeiteLoginMKlick() {
		String login = text1.getText();
		String passwort = text2.getText();

		try {
				
			aktuellerBenutzer = shop.mEinloggen(login, passwort);
			JOptionPane.showMessageDialog(null, aktuellerBenutzer.getVorname() + " " + aktuellerBenutzer.getNachname());
			if(aktuellerBenutzer instanceof Mitarbeiter) {
				new AngemeldetAlsMitarbeiter(shop);
				this.dispose();
			if(aktuellerBenutzer == null) {
				new EinloggenPanel(shop);
	
			}	
			}		
		} catch (LoginUngueltigException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
		
	}
	
	private void verarbeiteLoginKKlick() {
		String login = text1.getText();
		String passwort = text2.getText();
		
		
		try {
				
			aktuellerBenutzer = shop.kEinloggen(login, passwort);
			JOptionPane.showMessageDialog(null, aktuellerBenutzer.getVorname() + " " + aktuellerBenutzer.getNachname());
			if(aktuellerBenutzer instanceof Kunde) {
				new AngemeldetAlsKunde(shop,(Kunde) aktuellerBenutzer);
				
				this.dispose();
			if(aktuellerBenutzer == null) {
				new EinloggenPanel(shop);
			
			}	
			}		
		} catch (LoginUngueltigException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
		
	}
}
