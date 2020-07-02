package shop.local.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shop.local.domain.exceptions.LoginUngueltigException;
import shop.local.domain.exceptions.MitarbeiterExistiertBereitsException;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueobjects.Mitarbeiter;

public class MitarbeiterVerwaltung {
	
	private PersistenceManager pm = new FilePersistenceManager();
	private List<Mitarbeiter> mitarbeiterBestand = new ArrayList<>();	

    public void registrieren(String vorname, String nachname, String loginName, String passwort) {
    	mitarbeiterBestand.add(new Mitarbeiter(vorname, nachname, loginName, passwort));
    } 	
	
    public Mitarbeiter einloggen(String login, String passwort) throws LoginUngueltigException {  
        return sucheNachLogin(login,passwort);
    }
	
    private Mitarbeiter sucheNachLogin(String login, String passwort) throws LoginUngueltigException {

        for (Mitarbeiter mitarbeiter : mitarbeiterBestand) {
            if (mitarbeiter.getLoginName().equals(login) && mitarbeiter.getPw().equals(passwort) ) {
                return mitarbeiter;
            }
        }
        throw new LoginUngueltigException(login);
    }
    
    
    public List<Mitarbeiter> sucheMitarbeiter(String login) {	   
        List<Mitarbeiter> suchErg = new ArrayList<>();

        // Artikelbestand durchlaufen und nach Titel suchen
        Iterator<Mitarbeiter> iter = mitarbeiterBestand.iterator();	      
        while (iter.hasNext()) {
       
		for (Mitarbeiter mitarbeiter : mitarbeiterBestand) {
			if ((mitarbeiter).getLoginName().equals(login))
				suchErg.add(mitarbeiter);
		}		
        
    }
		return suchErg;
    }
    
	
    public void liesDaten(String datei) throws  Exception{
        // PersistenzManager f�r Lesevorg�nge �ffnen
        pm.openForReading(datei);

        Mitarbeiter einMitarbeiter ;        
        do {    
        
				einMitarbeiter = pm.ladeMitarbeiter();		
            if (einMitarbeiter != null) {
                einfuegen(einMitarbeiter);
            }
        } while (einMitarbeiter != null);

        // Persistenz-Schnittstelle wieder schlie�en
        pm.close();
    }
	
	
		/**
		 * Mit dieser Methode wird der Mitarbeiter gespeichert in der Datei
		 * @param datei
		 * @throws IOException
		 */
	   public void schreibeDaten(String datei) throws IOException  {
	        // PersistenzManager f�r Schreibvorg�nge �ffnen
	        pm.openForWriting(datei);

		Iterator<Mitarbeiter> iter = mitarbeiterBestand.iterator();
		while (iter.hasNext()) {
			Mitarbeiter m = iter.next();
			pm.speichereMitarbeiter(m);
		}
		pm.close();
	   }
	   
	  /**
	   * In dieser Methode wird ein Mitarbeite den man hinzuf�gen will erstmal �berpr�ft, 
	   * ob er schon im Bestand vorhanden ist
	   * wenn nicht wird er angelegt
	   * wenn er schon vorhanden ist wird eine Exception geworfen 
	   * @param einMitarbeiter
	   * @throws MitarbeiterExistiertBereitsException
	   */
	  public void einfuegen (Mitarbeiter einMitarbeiter) throws MitarbeiterExistiertBereitsException  {
		   for(Mitarbeiter b:mitarbeiterBestand) {
				if(b.getLoginName().equals(einMitarbeiter.getLoginName())) {
					throw new MitarbeiterExistiertBereitsException(einMitarbeiter);
				}
		   }
	        // das �bernimmt der Vector:
	        mitarbeiterBestand.add(einMitarbeiter);
	  }
	   
	   public List<Mitarbeiter> getMitarbeiterBestand() {
	        return new ArrayList<>(mitarbeiterBestand);
	    }
	
}

	
