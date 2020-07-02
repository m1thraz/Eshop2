package shop.local.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shop.local.domain.exceptions.KundeBereitsVorhandenException;
import shop.local.domain.exceptions.LoginUngueltigException;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueobjects.Adresse;
import shop.local.valueobjects.Kunde;

public class KundenVerwaltung {
	
		private PersistenceManager pm = new FilePersistenceManager();	
	    private List<Kunde> kundenBestand = new ArrayList<>();	
	    
	     
	    public void registrieren(String vorname, String nachname, String loginName, String passwort,Adresse adresse) throws KundeBereitsVorhandenException {
	    	for (Kunde kunde : kundenBestand) {
	            if (kunde.getLoginName().equals(loginName) && kunde.getVorname().equals(vorname)) {
	                throw new KundeBereitsVorhandenException(vorname,nachname);
	            }
	        }
	    	kundenBestand.add(new Kunde(vorname, nachname, loginName, passwort,adresse));
	    } 
	    
	    
	    public Kunde einloggen(String login, String passwort) throws LoginUngueltigException {

	        Kunde kunde = sucheNachLogin(login);
	        
	        if(kunde != null && kunde.getPw().equals(passwort)) {
	            return kunde;
	        }
	        throw new LoginUngueltigException(login);
	        
	    }
	    
	    public List<Kunde> sucheKunde(String login) {	   
	        List<Kunde> suchErg = new ArrayList<>();

	        // Artikelbestand durchlaufen und nach der Bezeichnung suchen
	        Iterator<Kunde> iter = kundenBestand.iterator();	      
	        while (iter.hasNext()) {
	       
			for (Kunde kunde : kundenBestand) {
				if ((kunde).getLoginName().equals(login))
					suchErg.add(kunde);
			}		
	        
	    }
			return suchErg;
	    }
	    
	    
	    
	    
	    private Kunde sucheNachLogin(String login) {

	        for (Kunde kunde : kundenBestand) {
	            if (kunde.getLoginName().equals(login)) {
	                return kunde;
	            }
	        }
	        return null;
	    }
	    
	    public void liesDaten(String datei) throws IOException, KundeBereitsVorhandenException {
	        // PersistenzManager für Lesevorgänge öffnen
	        pm.openForReading(datei);

	        Kunde einKunde;
	        do {
	            // Artikel-Objekt einlesen
	            einKunde = pm.ladeKunde();
	            if (einKunde != null) {
	                einfuegen(einKunde);
	            }
	        } while (einKunde != null);

	        // Persistenz-Schnittstelle wieder schließen
	        pm.close();
	    }
		
		   public void schreibeDaten(String datei) throws IOException  {
		        // PersistenzManager für Schreibvorgänge öffnen
		        pm.openForWriting(datei);

			Iterator<Kunde> iter = kundenBestand.iterator();
			while (iter.hasNext()) {
				Kunde k = iter.next();
				pm.speichereKunde(k);
			}
			pm.close();
		   }
		   
		   public void einfuegen(Kunde einKunde) throws KundeBereitsVorhandenException  {
		        if (kundenBestand.contains(einKunde)) {
		            throw new KundeBereitsVorhandenException(einKunde);
		        }

		        // das übernimmt der Vector:
		        kundenBestand.add(einKunde);
		    }
		   
		   public List<Kunde> getKundenBestand() {
		        return new ArrayList<>(kundenBestand);
		   }
}
