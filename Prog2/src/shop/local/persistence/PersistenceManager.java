package shop.local.persistence;

import java.io.IOException;

import shop.local.domain.exceptions.MitarbeiterNichtVorhandenException;
import shop.local.valueobjects.*;


public interface PersistenceManager {

    public void openForReading(String datenquelle) throws IOException;

    public void openForWriting(String datenquelle) throws IOException;

    public boolean close();

    //----------------------------------------Artikel--------------------------------------------------------
    public Artikel ladeArtikel() throws IOException;
    
    public boolean speichereArtikel(Artikel b) throws IOException;

    //----------------------------------------Kunde--------------------------------------------------------
    public Kunde ladeKunde() throws IOException;
    
    public boolean speichereKunde(Kunde k) throws IOException;

    //----------------------------------------Mitarbeiter-------------------------------------------------------- 
    public Mitarbeiter ladeMitarbeiter() throws MitarbeiterNichtVorhandenException, Exception;

	public boolean speichereMitarbeiter(Mitarbeiter m);
	
	//----------------------------------------Warenkorb--------------------------------------------------------
    
	public Warenkorb ladeWarenkorb() throws IOException;

		
}