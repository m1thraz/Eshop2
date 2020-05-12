package shop.local.domain;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.local.domain.exceptions.ArtikelExistiertBereitsException;
import shop.local.domain.exceptions.KundeExistiertBereitsException;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueObjects.*;

public class KundenVerwaltung {
	private PersistenceManager pm = new FilePersistenceManager();
	

//	    Vector<Kunde> alleKunden = new Vector();
	    private List<Kunde> kundenBestand = new Vector<Kunde>();;	
	    
	    
	    public void registrieren(String vorname, String nachname, String loginName, String passwort, int personNr, Adresse adresse) {
	    	kundenBestand.add(new Kunde(vorname, nachname, loginName, passwort,personNr, adresse));
	    } 
	    
	    
	    public Kunde einloggen(String login, String passwort) {

	        Kunde kunde = sucheNachLogin(login);
	        
	        if(kunde.getPw().equals(passwort)) {
	            return kunde;
	        }
	        return null;
	    }
	    
	    public List<Kunde> sucheKunde(String login) {	   
	        List<Kunde> suchErg = new Vector<Kunde>();

	        // Artikelbestand durchlaufen und nach Titel suchen
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
	    
	    public void liesDaten(String datei) throws IOException, KundeExistiertBereitsException {
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

//		        for (Kunde k : kundenBestand) {
	//	            pm.speichereKunde(k);
		           
		//        }
		     // Alternative Implementierung mit Iterator:
			Iterator<Kunde> iter = kundenBestand.iterator();
			while (iter.hasNext()) {
				Kunde k = iter.next();
				pm.speichereKunde(k);
			}
			pm.close();
		   }
		   
		   
		   public void einfuegen(Kunde einKunde) throws KundeExistiertBereitsException  {
		        if (kundenBestand.contains(einKunde)) {
		            throw new KundeExistiertBereitsException(einKunde, " - in 'einfuegen()'");
		        }

		        // das übernimmt der Vector:
		        kundenBestand.add(einKunde);
		    }
		   public List<Kunde> getKundenBestand() {
		        return new Vector<Kunde>(kundenBestand);
		    }
}
