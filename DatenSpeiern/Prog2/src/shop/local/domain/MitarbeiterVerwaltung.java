package shop.local.domain;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.local.domain.exceptions.KundeExistiertBereitsException;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueObjects.Adresse;
import shop.local.valueObjects.Kunde;
import shop.local.valueObjects.Mitarbeiter;

public class MitarbeiterVerwaltung {
	private PersistenceManager pm = new FilePersistenceManager();
	private List<Mitarbeiter> mitarbeiterBestand = new Vector<Mitarbeiter>();;	

    public void registrieren(String vorname, String nachname, String loginName, String passwort) {
    	mitarbeiterBestand.add(new Mitarbeiter(vorname, nachname, loginName, passwort));
    } 	
	
    public Mitarbeiter einloggen(String login, String passwort) {

        Mitarbeiter mitarbeiter = sucheNachLogin(login);
        
        if(mitarbeiter.getPw().equals(passwort)) {
            return mitarbeiter;
        }
        return null;
    }
	
    private Mitarbeiter sucheNachLogin(String login) {

        for (Mitarbeiter mitarbeiter : mitarbeiterBestand) {
            if (mitarbeiter.getLoginName().equals(login)) {
                return mitarbeiter;
            }
        }
        return null;
    }
	
    public void liesDaten(String datei) throws IOException, KundeExistiertBereitsException {
        // PersistenzManager für Lesevorgänge öffnen
        pm.openForReading(datei);

        Mitarbeiter einMitarbeiter;
        do {
            // Artikel-Objekt einlesen
            einMitarbeiter = pm.ladeMitarbeiter();
            if (einMitarbeiter != null) {
                einfuegen(einMitarbeiter);
            }
        } while (einMitarbeiter != null);

        // Persistenz-Schnittstelle wieder schließen
        pm.close();
    }
	
	
	
	   public void schreibeDaten(String datei) throws IOException  {
	        // PersistenzManager für Schreibvorgänge öffnen
	        pm.openForWriting(datei);

		Iterator<Mitarbeiter> iter = mitarbeiterBestand.iterator();
		while (iter.hasNext()) {
			Mitarbeiter m = iter.next();
			pm.speichereMitarbeiter(m);
		}
		pm.close();
	   }
	   
	   
	   public void einfuegen(Mitarbeiter einMitarbeiter)  {

	        // das übernimmt der Vector:
	        mitarbeiterBestand.add(einMitarbeiter);
	    }
	   public List<Mitarbeiter> getMitarbeiterBestand() {
	        return new Vector<Mitarbeiter>(mitarbeiterBestand);
	    }
	
}

	
