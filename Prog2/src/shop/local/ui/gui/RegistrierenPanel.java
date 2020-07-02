package shop.local.ui.gui;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import shop.local.domain.EShop;
import shop.local.domain.exceptions.KundeBereitsVorhandenException;
import shop.local.valueobjects.Adresse;

public class RegistrierenPanel extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private EShop shop;

    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JTextField text6;
    private JTextField text7;
    private JTextField text8;
    
    public RegistrierenPanel(EShop shop) {
		 this.shop=shop;	    
		 initialize();
		
	}
	
	private void initialize() {
		
		//Textfeld von Vorname
	     text1 = new JTextField();
	    this.add(text1);
	    text1.setBounds(110,20, 100,30); 
		
		//Textfeld von Nachname
		 text2 = new JTextField();
		this.add(text2);
	    text2.setBounds(110,75,100,30);
	    
	  //Textfeld von Benutzername
		 text3 = new JTextField();
		this.add(text3);
	    text3.setBounds(110,125,100,30);
	    
	  //Textfeld von Passwort
		 text4 = new JTextField();
		this.add(text4);
	    text4.setBounds(110,175,100,30);
	    
	  //Textfeld von Strasse
		 text5 = new JTextField();
		this.add(text5);
	    text5.setBounds(110,225,100,30);
	    
	  //Textfeld von Hausnummer
		 text6 = new JTextField();
		this.add(text6);
	    text6.setBounds(110,275,100,30);
	    
	  //Textfeld von Poistleitzahl
		 text7 = new JTextField();
		this.add(text7);
	    text7.setBounds(110,325,100,30);
	    
	  //Textfeld von Ort
		 text8 = new JTextField();
		this.add(text8);
	    text8.setBounds(110,375,100,30);
	    
	    ///////////////////////////////////////////////////////////////
	    
	    
	    
	    //Label "Loginname"
	    JLabel l1=new JLabel("Vorname:");    
	    this.add(l1);
	    l1.setBounds(20,20, 80,30);    
	    
	    //Label "Passwort"
	    JLabel l2=new JLabel("Nachname:");    
	    this.add(l2); 
	    l2.setBounds(20,70, 80,30);  
	    
	  //Label "Passwort"
	    JLabel l3=new JLabel("Benutzername:");    
	    this.add(l3); 
	    l3.setBounds(20,120, 80,30);  
	    
	  //Label "Passwort"
	    JLabel l4=new JLabel("Passwort:");    
	    this.add(l4); 
	    l4.setBounds(20,170, 80,30);  
	    
	  //Label "Passwort"
	    JLabel l5=new JLabel("Strasse:");    
	    this.add(l5); 
	    l5.setBounds(20,220, 80,30);  
	    
	  //Label "Passwort"
	    JLabel l6=new JLabel("Hausnummer:");    
	    this.add(l6); 
	    l6.setBounds(20,270, 80,30);  
	    
	  //Label "Passwort"
	    JLabel l7=new JLabel("Poistleitzahl:");    
	    this.add(l7); 
	    l7.setBounds(20,320, 80,30);  
	    
	  //Label "Passwort"
	    JLabel l8=new JLabel("Ort:");    
	    this.add(l8); 
	    l8.setBounds(20,370, 80,30);    
	    
	    // Registrieren Button 
	    JButton b = new JButton("Registrieren"); 
	    this.add(b);
	    b.setBounds(100,440, 120,30);
	    b.addActionListener(e -> {
			try {
				verarbeiteRegistrierenKlick();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});    
	                         
	    this.setSize(300,600); 
	    this.setLayout(null); //keinen LayoutManager benutzen
	    this.setVisible(true);//setzt den Frame auf sichtbar
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Damit wird der Frame Beendet  
	    setLocationRelativeTo(null);
	}
	
	private void verarbeiteRegistrierenKlick() throws IOException {
		
		
		String vorname = text1.getText();
		String nachname = text2.getText();
		String login = text3.getText();
		String passwort = text4.getText();
		String strasse = text5.getText();
		String hausNr = text6.getText();
		String plz = text7.getText();
		String ort = text8.getText();
		
		Adresse adresse = new Adresse(strasse,hausNr, plz,ort);
		try {
		shop.registrieren(vorname,nachname,login,passwort,adresse);
		JOptionPane.showMessageDialog(null,"wurde ausgeführt! " );
		
		}catch(KundeBereitsVorhandenException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		this.dispose();
		new EinloggenPanel(shop); 
	}
	
	 

}
