package shop.local.persistence;

import java.io.IOException;

import shop.local.valueObjects.Artikel;
import shop.local.valueObjects.Kunde;
import shop.local.valueObjects.Mitarbeiter;


public interface PersistenceManager {

    public void openForReading(String datenquelle) throws IOException;

    public void openForWriting(String datenquelle) throws IOException;

    public boolean close();


    public Artikel ladeArtikel() throws IOException;


    public boolean speichereArtikel(Artikel b) throws IOException;

	
	

	public Kunde ladeKunde() throws IOException;

	public boolean speichereKunde(Kunde k) throws IOException;
	
	public boolean speichereMitarbeiter(Mitarbeiter m) throws IOException;
	public Mitarbeiter ladeMitarbeiter() throws IOException;

	
}