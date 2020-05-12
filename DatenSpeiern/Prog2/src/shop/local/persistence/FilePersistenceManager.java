package shop.local.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import shop.local.valueObjects.*;




public class FilePersistenceManager implements PersistenceManager {

	private BufferedReader reader = null;
	private PrintWriter writer = null;

	public void openForReading(String datei) throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(datei));
	}

	public void openForWriting(String datei) throws IOException {
		writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
	}

	public boolean close() {
		if (writer != null)
			writer.close();

		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				return false;
			}
		}

		return true;
	}


	public Artikel ladeArtikel() throws IOException {
		// Titel einlesen
		String titel = liesZeile();
		if (titel == null) {
			// keine Daten mehr vorhanden
			return null;
		}
		// Nummer einlesen ...
		String nummerString = liesZeile();
		// ... und von String in int konvertieren
		int nummer = Integer.parseInt(nummerString);
				
		// Preis einlesen
		String preisString = liesZeile();
		// ... und von String in int konvertieren
		double preis = Double.parseDouble(preisString);

		String bestandString = liesZeile();
		// ... und von String in int konvertieren
		int bestand = Integer.parseInt(bestandString);
			// keine Daten mehr vorhanden

		
		
		
		return new Artikel(titel, nummer, preis, bestand);
	}

	
	public Kunde ladeKunde() throws IOException {
		String vorname = liesZeile();
		if (vorname == null) {
			// keine Daten mehr vorhanden
			return null;
		}
		String nachname = liesZeile();
		if (nachname == null) {
			// keine Daten mehr vorhanden
			return null;
		}
		String loginName = liesZeile();
		if (loginName == null) {
			return null;
		}
		String pw = liesZeile();
		if (pw == null) {			
			return null;
		}
		String personNrS = liesZeile();		
		int personNr = Integer.parseInt(personNrS);
		
		String strasse = liesZeile();
		if (strasse == null) {			
			return null;
		}
		
		
		String hausNr = liesZeile();
		if (hausNr == null) {			
			return null;
		}
		String plz = liesZeile();
		if (plz == null) {			
			return null;
		}
		String ort = liesZeile();
		if (ort == null) {			
			return null;
		}
		Adresse adresse =new Adresse( strasse, hausNr, plz, ort);
		return new Kunde(vorname, nachname, loginName, pw, personNr, adresse );// strasse, hausNr, plz, ort);
	}
	public Mitarbeiter ladeMitarbeiter() throws IOException {
		String vorname = liesZeile();
		if (vorname == null) {
			// keine Daten mehr vorhanden
			return null;
		}
		String nachname = liesZeile();
		if (nachname == null) {
			// keine Daten mehr vorhanden
			return null;
		}
		String loginName = liesZeile();
		if (loginName == null) {
			return null;
		}
		String pw = liesZeile();
		if (pw == null) {			
			return null;
		}
		String personNrS = liesZeile();		
		int personNr = Integer.parseInt(personNrS);
		return new Mitarbeiter(vorname, nachname, loginName, pw);
		
	}

	public boolean speichereArtikel(Artikel b) throws IOException {
		//hier wird der Arikel in der Datei gespeciher
		// 1. Bezeichnung 2.ArtNr 3. Verfuegbar 4. Preis 5.Bestand
	    schreibeZeile(b.getBezeichnung());
		schreibeZeile(b.getArtNr() + "");		
		schreibeZeile(b.getPreis() + "");
		schreibeZeile(b.getBestand()+ "");

		return true;
	}
	public boolean speichereKunde(Kunde k) throws IOException {
		// Kunde  wird in Datei gerspeichert
		// 1. Vorname 2.Nachname 3. Login 4. Passwort 5.Adresse		
		schreibeZeile(k.getVorname() + "");
		schreibeZeile(k.getNachname() + "");
		schreibeZeile(k.getLoginName() + "");
		schreibeZeile(k.getPw() + "");
		schreibeZeile(k.getPersonNr() + "");
		schreibeZeile(k.getAdresse().getStrasse() + "");
		schreibeZeile(k.getAdresse().getHausNr() + "");
		schreibeZeile(k.getAdresse().getPlz() + "");
		schreibeZeile(k.getAdresse().getOrt() + "");
		
		return true;
	}
	public boolean speichereMitarbeiter(Mitarbeiter m) throws IOException {
		// Kunde  wird in Datei gerspeichert
		// 1. Vorname 2.Nachname 3. Login 4. Passwort 5.Adresse		
		schreibeZeile(m.getVorname() + "");
		schreibeZeile(m.getNachname() + "");
		schreibeZeile(m.getLoginName() + "");
		schreibeZeile(m.getPw() + "");
		schreibeZeile(m.getPersonNr() + "");
		return true;
	}
	

	private String liesZeile() throws IOException {
		if (reader != null)
			return reader.readLine();
		else
			return "";
	}

	private void schreibeZeile(String daten) {
		if (writer != null)
			writer.println(daten);
	}



	

	

}
