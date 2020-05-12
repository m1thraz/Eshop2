package shop.local.persistence;



import java.io.IOException;

import shop.local.valueObjects.Artikel;
import shop.local.valueObjects.Kunde;
import shop.local.valueObjects.Mitarbeiter;

public class DBPersistenceManager implements PersistenceManager {

	private Artikel b;
	private Kunde k;
	public DBPersistenceManager(Artikel b) {
		this.b = b;
	}
	public DBPersistenceManager(Kunde k) {
		this.k = k;
	}
	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Artikel ladeArtikel() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void openForReading(String datenquelle) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void openForWriting(String datenquelle) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean speichereArtikel(Artikel b) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	



	@Override
	public Kunde ladeKunde() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean speichereKunde(Kunde k) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean speichereMitarbeiter(Mitarbeiter m) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Mitarbeiter ladeMitarbeiter() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
