package shop.local.valueObjects;

import java.time.LocalDateTime;

public class Ereignis {

		private LocalDateTime datum;
		private Artikel artikel;
		private boolean einOderAus;
		private int anzahl;
		private Benutzer benutzer;
		
		//Konstruktor fuer das Einlesen
		public Ereignis(LocalDateTime datum, Artikel artikel, int anzahl, Benutzer benutzer) {
			this.datum=datum;
			this.artikel=artikel;
			this.anzahl=anzahl;
			this.benutzer=benutzer;
		}
		public Ereignis(Artikel artikel, int anzahl, Benutzer benutzer) {
			this.setBenutzer(benutzer);
			this.datum = LocalDateTime.now();
			this.artikel=artikel;
			//WENN TRUE DANN AUSLAGERUNG(KAUF) WENN FALSE DANN EINLAGERUNG(MITARBEITER)
			this.einOderAus=true;
			this.anzahl=anzahl;

		}
		public LocalDateTime getDatum() {
			return datum;
		}
		public boolean isEinOderAus() {
			return einOderAus;
		}
		public void setEinOderAus(boolean einOderAus) {
			this.einOderAus = einOderAus;
		}
		public Artikel getArtikel() {
			return artikel;
		}
		public int getAnzahl() {
			return anzahl;
		}
		public Benutzer getBenutzer() {
			return benutzer;
		}
		public void setBenutzer(Benutzer benutzer) {
			this.benutzer = benutzer;
		}
}
