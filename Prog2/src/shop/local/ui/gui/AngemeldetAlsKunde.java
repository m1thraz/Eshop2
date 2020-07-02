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
import shop.local.domain.exceptions.ArtikelBestandReichtNichtAusException;
import shop.local.domain.exceptions.KundeBereitsVorhandenException;
import shop.local.domain.exceptions.MassengutartikelException;
import shop.local.ui.gui.model.ArtikelTableModel;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.Rechnung;
import shop.local.valueobjects.Warenkorb;

public class AngemeldetAlsKunde extends JFrame{
	
		private static final long serialVersionUID = 1L;
			private JTextField suchTextfeld;
		    private JTextField titelAusWarenkorbTextfeld;
		    private JTextField anzahlAusWarenkorbTextfeld;
		    private JTextField titelInWarenkorbTextfeld;
		    private JTextField anzahlInWarenkorbTextfeld;	    		
		    private JList<Artikel> artikelListe;
		    private JTable artikelTabelle;
		    private EShop shop;
		    private Kunde kunde;
		    private final JLabel trenner = new JLabel("      ");
		    
		    
		    
		    
		    public AngemeldetAlsKunde(EShop shop, Kunde kunde)  {
		    	this.shop=shop;
		    	this.kunde=kunde;
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
		        loeschenPanel.setBorder(BorderFactory.createTitledBorder("Warenkorb"));
		        
		        // SÜD
		        JPanel logoutPanel = new JPanel();
		        logoutPanel.setLayout(new BoxLayout(logoutPanel, BoxLayout.PAGE_AXIS));
		        logoutPanel.setBorder(BorderFactory.createTitledBorder("Optionen"));
		        
		       
		        //Aus Warenkorb Felder und Labels
		        loeschenPanel.add(new JLabel("Anzahl: "));
		        anzahlAusWarenkorbTextfeld = new JTextField();
		        loeschenPanel.add(anzahlAusWarenkorbTextfeld);
		        loeschenPanel.add(new JLabel("Titel: "));
		        titelAusWarenkorbTextfeld = new JTextField();
		        loeschenPanel.add(titelAusWarenkorbTextfeld);
		        
		        loeschenPanel.add(trenner);
		        
		        JButton ausWarenkorbButton = new JButton("Aus Warenkorb");
		        loeschenPanel.add(ausWarenkorbButton);
		        ausWarenkorbButton.addActionListener(e7 -> verarbeiteAusWarenkorbKlick());
		        
		        loeschenPanel.add(trenner);
		        
		        
		        loeschenPanel.add(new JLabel("Anzahl: "));
		        anzahlInWarenkorbTextfeld = new JTextField();
		        loeschenPanel.add(anzahlInWarenkorbTextfeld);
		        loeschenPanel.add(new JLabel("Titel: "));
		        titelInWarenkorbTextfeld = new JTextField();
		        loeschenPanel.add(titelInWarenkorbTextfeld);
		        
		        loeschenPanel.add(trenner);
		        JButton inWarenkorbButton = new JButton("In Warenkorb");
		        loeschenPanel.add(inWarenkorbButton);
		        inWarenkorbButton.addActionListener(e7 -> verarbeiteInWarenkorbKlick());
		                
		        JButton logoutButton = new JButton("Logout");
		        logoutPanel.setLayout(new FlowLayout());
		        logoutButton.addActionListener(e9 -> verarbeiteLogoutKlick());
		        logoutPanel.add(logoutButton);
	        
		        JButton sortNameButton = new JButton("SortName");
		        logoutPanel.add(sortNameButton);
		        sortNameButton.addActionListener(e3 -> verarbeiteSortNameKlick());
		        
		        JButton sortNrButton = new JButton("SortNr");
		        logoutPanel.add(sortNrButton);	
		        sortNrButton.addActionListener(e10 -> verarbeiteSortNrKlick());
		      
		        loeschenPanel.add(new JLabel("        "));
		        JButton kaufen = new JButton("Kaufen");
		        loeschenPanel.add(kaufen);
		        kaufen.addActionListener(e3 -> verarbeiteKaufAbschliessenKlick());	        
		       
		        loeschenPanel.add(new JLabel("        "));
		        JButton warenkorbAnzeigen = new JButton("Anzeigen");
		        loeschenPanel.add(warenkorbAnzeigen);
		        warenkorbAnzeigen.addActionListener(e3 -> verarbeiteWarenkorbAnzeigenKlick());
		        
		        
		        JButton warenkorbLeerenButton = new JButton("W-leeren");
		        logoutPanel.add(warenkorbLeerenButton);
		        warenkorbLeerenButton.addActionListener(e3 -> verarbeiteWarenkorbLeerenKlick());
		        
		        
		        //Textfelder von Löschen Panel größe
		        Dimension fillerMinSizee = new Dimension(5,20);
		        Dimension fillerPreferredSizee = new Dimension(5,Short.MAX_VALUE);
		        Dimension fillerMaxSizee = new Dimension(5,Short.MAX_VALUE);
		        loeschenPanel.add(new Box.Filler(fillerMinSizee, fillerPreferredSizee, fillerMaxSizee));


		    	
		    	ArtikelTableModel tableModel = new ArtikelTableModel(new Vector<>());
		    	artikelTabelle = new JTable(tableModel);
		    	
		    	
		    	//JList in ScrollPane enfügen
		    	JScrollPane scrollPane = new JScrollPane(artikelTabelle );
		        
		        // "Zusammenbau" in BorderLayout des Frames
		        add(suchPanel, BorderLayout.NORTH);
		        add(scrollPane, BorderLayout.CENTER);
		        add(loeschenPanel, BorderLayout.EAST);
		        add(logoutPanel, BorderLayout.SOUTH);

		        setSize(750, 500);
		        setVisible(true);
		        setLocationRelativeTo(null); 
		    }

		    
		    private void verarbeiteWarenkorbAnzeigenKlick() {		    	
				
		    	Warenkorb warenkorb= shop.gibWarenImKorb(kunde);			

				aktualisiereArtikelAnzeige(warenkorb.toList());

		    	
		    }
		    
		    
		    
		    
		    
		    private void verarbeiteAusWarenkorbKlick() {
		    	
				
				String titel = titelAusWarenkorbTextfeld.getText();
				String anzahlString = anzahlAusWarenkorbTextfeld.getText();
				int menge = Integer.parseInt(anzahlString);
				
				List<Artikel>liste1 = shop.sucheArtikelBezeichnung(titel);
				
				
				shop.artikelAusWarenkorbentfernen( liste1.get(0), kunde, menge);
				Warenkorb warenkorb= shop.gibWarenImKorb(kunde);
				aktualisiereArtikelAnzeige(warenkorb.toList());
				
				try {
					shop.schreibeArtikel();
					JOptionPane.showMessageDialog(null, liste1.get(0).getBezeichnung() + " wurde aus dem Warenkorb " + menge +" mal entfernt!");

				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
		    	
		    }
		   	    
		    private void verarbeiteInWarenkorbKlick() {
		    	
		    	
				String titel = titelInWarenkorbTextfeld.getText();
				String anzahlString = anzahlInWarenkorbTextfeld.getText();
				int menge = Integer.parseInt(anzahlString);
				
				List<Artikel>liste1 = shop.sucheArtikelBezeichnung(titel);
			   
				
				
					try {
						shop.warenHinzufuegen(liste1.get(0),kunde, menge);
						shop.schreibeArtikel();
						JOptionPane.showMessageDialog(null, liste1.get(0).getBezeichnung() + " wurde dem Warenkorb " + menge +" mal hinzugefuegt!");
					} catch (ArtikelBestandReichtNichtAusException | MassengutartikelException | IOException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());	
						
					}
								
		    	   }
		    
		    
		    private void verarbeiteKaufAbschliessenKlick() {		 
		    	Rechnung rechnung = new Rechnung(kunde);				
				JOptionPane.showMessageDialog(null, rechnung);
				this.dispose();
		    	new EinloggenPanel(shop);
			        
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
		    
		    private void verarbeiteLogoutKlick() {
		    	
		    	this.dispose();
		    	
		    }
		    
		    private void verarbeiteWarenkorbLeerenKlick() {
		    	shop.warenkorbLeeren(kunde);
		    	JOptionPane.showMessageDialog(null,"Warenkorb wurde gelöscht");
		    	
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
						} catch (IOException | KundeBereitsVorhandenException  e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
		            }
		        });
		    }	    
}
