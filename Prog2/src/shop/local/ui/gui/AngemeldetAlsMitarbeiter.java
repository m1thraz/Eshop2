package shop.local.ui.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import shop.local.domain.EShop;
import shop.local.domain.exceptions.ArtikelNrSchonVorhandenException;
import shop.local.domain.exceptions.KundeBereitsVorhandenException;
import shop.local.domain.exceptions.MitarbeiterExistiertBereitsException;
import shop.local.ui.gui.model.ArtikelTableModel;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Kunde;

public class AngemeldetAlsMitarbeiter extends JFrame{
	
		private static final long serialVersionUID = 1L;
			private JTextField nummernFeld;
		    private JTextField titelFeld;
		    private JTextField nummernFeld2;
		    private JTextField titelFeld2;
		    private JTextField preisFeld;
		    private JTextField bestandFeld;
		    private JTextField einheitFeld;
		    private JTextField suchTextfeld;
		    private JTextField benutzerName;
		    private JTextField vorName;
		    private JTextField nachName;
		    private JTextField passwort;   		    
		    private JList<Artikel> artikelListe;
		    private JTable artikelTabelle;
		    private EShop shop;
		    private final JLabel trenner = new JLabel("      ");
		    
		    public AngemeldetAlsMitarbeiter(EShop shop)  {
		    	this.shop=shop;
		    	initialize();
		    	aktualisiereArtikelAnzeige(shop.gibAlleArtikel());
		    }

		    private void initialize() {

		        // Klick auf Kreuz / roten Kreis (Fenster schließen)
		        // behandeln lassen:
		        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		        addWindowListener(new WindowCloser());

		        

		        // Layout des Frames: BorderLayout
		        setLayout(new BorderLayout());

		        // NORTH
		        JPanel suchPanel = new JPanel();
		        suchPanel.setBorder(BorderFactory.createTitledBorder("Suchen"));


		        GridBagLayout gridBagLayout = new GridBagLayout();
		        suchPanel.setLayout(gridBagLayout);
		        GridBagConstraints c = new GridBagConstraints();
		        c.fill = GridBagConstraints.HORIZONTAL;
		        c.gridy = 0;	// Zeile 0
		        
		        JLabel suchLabel = new JLabel("Suchbegriff:");
		        c.gridx = 0;	// Spalte 0
		        c.weightx = 0.2;	// 20% der gesamten Breite
		        gridBagLayout.setConstraints(suchLabel, c);
		        suchPanel.add(suchLabel);

		        suchTextfeld = new JTextField();
		        suchTextfeld.setToolTipText("Suchbegriff eingeben.");
		        c.gridx = 1;	// Spalte 1
		        c.weightx = 0.6;	// 60% der gesamten Breite
		        gridBagLayout.setConstraints(suchTextfeld, c);
		        suchPanel.add(suchTextfeld);

		        JButton suchButton = new JButton("Such!");
		        suchButton.addActionListener(e6 -> verarbeiteSuchenKlick());

		        c.gridx = 2;	// Spalte 2
		        c.weightx = 0.2;	// 20% der gesamten Breite
		        gridBagLayout.setConstraints(suchButton, c);
		        suchPanel.add(suchButton);

		        // WEST: BoxLayout (vertikal)
		        JPanel einfuegePanel = new JPanel();
		        einfuegePanel.setLayout(new BoxLayout(einfuegePanel, BoxLayout.PAGE_AXIS));
		        einfuegePanel.setBorder(BorderFactory.createTitledBorder("Einfügen")); 
		        
		        // OST: BoxLayout (vertikal)
		        JPanel loeschenPanel = new JPanel();
		        loeschenPanel.setLayout(new BoxLayout(loeschenPanel, BoxLayout.PAGE_AXIS));
		        loeschenPanel.setBorder(BorderFactory.createTitledBorder("Optionen"));
		        
		        // SÜD
		        JPanel logoutPanel = new JPanel();
		        logoutPanel.setLayout(new BoxLayout(logoutPanel, BoxLayout.PAGE_AXIS));
		        logoutPanel.setBorder(BorderFactory.createTitledBorder("Optionen"));
		        
		        JButton loeschButton = new JButton("Löschen!");
		        loeschButton.addActionListener(e7 -> verarbeiteLoeschenKlick());
		        
		       
		        
		        c.gridx = 2;	// Spalte 2
		        c.weightx = 0.2;	// 20% der gesamten Breite
		        gridBagLayout.setConstraints(loeschButton, c);
		        loeschenPanel.add(loeschButton);
		        
		        loeschenPanel.add(new JLabel("Nummer: "));
		        nummernFeld2 = new JTextField();
		        loeschenPanel.add(nummernFeld2);
		        loeschenPanel.add(new JLabel("Titel: "));
		        titelFeld2 = new JTextField();
		        loeschenPanel.add(titelFeld2);
		        
		        JLabel k = trenner;
		        loeschenPanel.add(k);
		        
		       //FlowLayout setzen für den logout Panel
		        FlowLayout fl = new FlowLayout();
		        logoutPanel.setLayout(fl);
		        
		        //Logout Button
		        JButton logoutButton = new JButton("Logout");
		        logoutButton.addActionListener(e9 -> verarbeiteLogoutKlick());
		        logoutPanel.add(logoutButton);
		        c.gridx = 2;	// Spalte 2
		        c.weightx = 0.2;	// 20% der gesamten Breite
		        gridBagLayout.setConstraints(logoutButton, c);
		        logoutPanel.add(logoutButton);
		        
		        JButton kAnzeigenButton = new JButton("K Anzeigen");
		        kAnzeigenButton.addActionListener(e9 -> verarbeiteKAnzeigenKlick());
		        logoutPanel.add(kAnzeigenButton);
		        logoutPanel.add(logoutButton);
		        
		        JButton bestandButton = new JButton("Bestand Ä");
		        bestandButton.addActionListener(e9 -> verarbeiteBestandKlick());
		        logoutPanel.add(bestandButton);
		        logoutPanel.add(bestandButton);
		        
		        
		        
		        JButton sortNameButton = new JButton("SortName");
		        loeschenPanel.add(sortNameButton);
		        sortNameButton.addActionListener(e3 -> verarbeiteSortNameKlick());
		        
		        JLabel h = trenner;
		        loeschenPanel.add(h);
		        
		        JButton sortNrButton = new JButton("SortNr");
		        loeschenPanel.add(sortNrButton);
		        sortNrButton.addActionListener(e4 -> verarbeiteSortNrKlick());
		        
		        
		        JLabel i = trenner;
		        loeschenPanel.add(i);
		        
		        JButton mEinfuegenButton = new JButton("mEinfuegen");
		        mEinfuegenButton.addActionListener(e7 -> verarbeiteMEinfuegenKlick());
		        loeschenPanel.add(mEinfuegenButton);
		        
		        JLabel j = new JLabel("      ");
		        loeschenPanel.add(j);
		        
		        
		        
		        loeschenPanel.add(new JLabel("Benutzername: "));
		        benutzerName = new JTextField();
		        loeschenPanel.add(benutzerName);
		        loeschenPanel.add(new JLabel("Vorname: "));
		        vorName = new JTextField();
		        loeschenPanel.add(vorName);
		        loeschenPanel.add(new JLabel("Nachname: "));
		        nachName = new JTextField();
		        loeschenPanel.add(nachName);
		        loeschenPanel.add(new JLabel("Passwort: "));
		        passwort = new JTextField();
		        loeschenPanel.add(passwort);
		        
		        
		        //Textfelder von Löschen Panel größe
		        Dimension fillerMinSizee = new Dimension(5,20);
		        Dimension fillerPreferredSizee = new Dimension(5,Short.MAX_VALUE);
		        Dimension fillerMaxSizee = new Dimension(5,Short.MAX_VALUE);
		        loeschenPanel.add(new Box.Filler(fillerMinSizee, fillerPreferredSizee, fillerMaxSizee));

		        einfuegePanel.add(new JLabel("Nummer: "));
		        nummernFeld = new JTextField();
		        einfuegePanel.add(nummernFeld);
		        einfuegePanel.add(new JLabel("Titel: "));
		        titelFeld = new JTextField();
		        einfuegePanel.add(titelFeld);
		        einfuegePanel.add(new JLabel("Preis: "));
		        preisFeld = new JTextField();
		        einfuegePanel.add(preisFeld);
		        einfuegePanel.add(new JLabel("Bestand: "));
		        bestandFeld = new JTextField();
		        einfuegePanel.add(bestandFeld);
		        einfuegePanel.add(new JLabel("Einheit: "));
		        einheitFeld = new JTextField();
		        einfuegePanel.add(einheitFeld);
		        

		        // Abstandhalter ("Filler") zwischen letztem Eingabefeld und Add-Button
		        Dimension fillerMinSize = new Dimension(5,20);
		        Dimension fillerPreferredSize = new Dimension(5,Short.MAX_VALUE);
		        Dimension fillerMaxSize = new Dimension(5,Short.MAX_VALUE);
		        einfuegePanel.add(new Box.Filler(fillerMinSize, fillerPreferredSize, fillerMaxSize));

		        JButton einfuegenButton = new JButton("Einfügen");
		        einfuegenButton.addActionListener(e -> verarbeiteEinfuegenKlick());

		        einfuegePanel.add(einfuegenButton);
		        
		      //Center
		    			    	
		    	ArtikelTableModel tableModel = new ArtikelTableModel(new Vector<>());
		    	artikelTabelle = new JTable(tableModel);
		    	
		    	//JList in ScrollPane enfügen
		    	JScrollPane scrollPane = new JScrollPane(artikelTabelle );
		        
		        // "Zusammenbau" in BorderLayout des Frames
		        add(suchPanel, BorderLayout.NORTH);
		        add(einfuegePanel, BorderLayout.WEST);
		        add(scrollPane, BorderLayout.CENTER);
		        add(loeschenPanel, BorderLayout.EAST);
		        add(logoutPanel, BorderLayout.SOUTH);

		        setSize(750, 550);
		        setVisible(true);
		        setLocationRelativeTo(null); 
		    }
		    
		    private void gibKundenListeAus(List<Kunde> liste) {
				if (liste.isEmpty()) {

					JOptionPane.showMessageDialog(null, "Liste ist leer!");
				} else {
		
					JOptionPane.showMessageDialog(null, liste);
				}
			}
		    
		    private void verarbeiteKAnzeigenKlick() {
		    	List<Kunde> liste2;
		    	liste2 = shop.gibAlleKunden();
				gibKundenListeAus(liste2);
		    	
		    }
		    
		    
		    
		    private void verarbeiteBestandKlick() {
		    	String nrAlsString= nummernFeld.getText();
		    	int zuAendernderArtikel = Integer.parseInt(nrAlsString);
		    	
		    	String bestandAlsString= bestandFeld.getText();
		    	int zuErhoenderBestand = Integer.parseInt(bestandAlsString);    	
		    	
		    	
		    	


				shop.aendereBestandVonArtikel(zuAendernderArtikel, zuErhoenderBestand);
				try {
					shop.schreibeArtikel();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
		    	
	            
		    }

		    private void verarbeiteMEinfuegenKlick() {
		  
	            String loginName = benutzerName.getText();
	            String vorname = vorName.getText();
	            String nachname = nachName.getText();
	            String pw = passwort.getText();
	            
				try {
					shop.fuegeMitarbeiterEin(loginName, vorname, nachname, pw);
				} catch (MitarbeiterExistiertBereitsException | IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} 
		    	
		    }
		    
		    public void aktualisiereArtikelAnzeige(java.util.List<Artikel> artikeln) {
		    	 ArtikelTableModel tableModel = (ArtikelTableModel) artikelTabelle.getModel();
		         tableModel.setArtikel(artikeln);
		    }
		    
		    /**
		     * Diese Methode wird in der suchButton.addActionListener(e6 -> verarbeiteSuchenKlick()); aufgerufen
		     * 
		     */
		    private void verarbeiteSuchenKlick() {
		    	String text = suchTextfeld.getText();
		    	java.util.List<Artikel> suchErgebnisArtikels;
		    	if(text.isEmpty()) {
		    		suchErgebnisArtikels = shop.gibAlleArtikel();
		    		aktualisiereArtikelAnzeige(suchErgebnisArtikels);
		    		return;
		    	}
		    	suchErgebnisArtikels = shop.sucheNachTitel(text);
				aktualisiereArtikelAnzeige(suchErgebnisArtikels);
		    }
		    
		    private void verarbeiteSortNameKlick() {
		    	
		    	List <Artikel> ergebnisListe = shop.gibArtikelListeAusKunde(1);
		    	aktualisiereArtikelAnzeige(ergebnisListe);
				
		    	
		    }
		    
		    private void verarbeiteSortNrKlick() {
		    	
		    	List <Artikel> ergebnisListe = shop.gibArtikelListeAusKunde(2);
		    	aktualisiereArtikelAnzeige(ergebnisListe);
		    	
		    	
		    }
		   

		    private void verarbeiteEinfuegenKlick() {
		    	List<Artikel>sucherGebnisArtikels;
		    	
		    	String artikel =  titelFeld.getText();
				
				String nrAlsString= nummernFeld.getText();
		    	int nr = Integer.parseInt(nrAlsString);
		    	
		    	String preisAlsString= preisFeld.getText();
		    	double preis = Double.parseDouble(preisAlsString);
		    	
		    	
		    	String bestandAlsString= bestandFeld.getText();
		    	int bestand = Integer.parseInt(bestandAlsString);
		    	
		    	
		    	String einheitAlsString= einheitFeld.getText();
		    	int einheit = Integer.parseInt(einheitAlsString);
		
	            try {
	                shop.fuegeArtikelEin(artikel, nr, preis, bestand, einheit);
	                sucherGebnisArtikels = shop.gibAlleArtikel();
	                aktualisiereArtikelAnzeige(sucherGebnisArtikels);
	               
	            } catch (ArtikelNrSchonVorhandenException e) {
	            	JOptionPane.showMessageDialog(null, e.getMessage());
	            }
	            try {
					shop.schreibeArtikel();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		    
		    private void verarbeiteLogoutKlick() {
		    	
		    	this.dispose();
		    	new EinloggenPanel(shop);
		    	
		    }
		    
			private void verarbeiteLoeschenKlick() {
				
			 List<Artikel>sucherGebnisArtikels;
			
			
					    	
				double preis=0;
				int bestand=0;
				String nummerAlsString= nummernFeld2.getText();
				int nr = Integer.parseInt(nummerAlsString);
				String artikel = titelFeld2.getText();
				
			       shop.loescheArtikel(artikel, nr, preis, bestand);
			       sucherGebnisArtikels = shop.gibAlleArtikel();
			       aktualisiereArtikelAnzeige(sucherGebnisArtikels);
			       try {
					shop.schreibeArtikel();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			 }		   
		    
		    class VerhaltenDesSuchButtons implements ActionListener{
		    	public void actionPerformed(ActionEvent e5) {
		    		String suchbegriff = suchTextfeld.getText();
		    		java.util.List<Artikel> suchErgebnis = null;
		    		if(suchbegriff.isEmpty()) {
		    			suchErgebnis = shop.gibAlleArtikel();
		    		}else {
		    			suchErgebnis = shop.sucheNachTitel(suchbegriff);
		    			
		    		}
		    		artikelListe.setListData(new Vector<>(suchErgebnis));
		    	}
		    }

		    public static void main(String[] args) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	try {
							new ShopGuiClient("OnlineShop", "SHOP");
						} catch (IOException | KundeBereitsVorhandenException e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
							
						
		            }
		        });
		    }	    

}
