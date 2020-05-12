package shop.local.valueObjects;

public class Adresse {

		protected String strasse = null;
		protected String hausNr = null;
		protected String plz = null;
		protected String ort = null;

		public Adresse(String strasse, String hausNr, String plz, String ort) {
			this.strasse = strasse;
			this.hausNr = hausNr;
			this.plz = plz;
			this.ort = ort;
		}

		public Adresse(Adresse adresse) {
			// TODO Auto-generated constructor stub
		}



		public String getStrasse() {
			return strasse;
		}
		
		public void setStrasse(String strasse) {
			this.strasse = strasse;
		}

		public String getHausNr() {
			return hausNr;
		}
		
		public void setHausNr(String hausNr) {
			this.hausNr = hausNr;
		}

		public String getPlz() {
			return plz;
		}

		public void setPlz(String plz) {
			this.plz = plz;
		}
		
		public String getOrt() {
			return ort;
		}

		public void setOrt(String ort) {
			this.ort = ort;
		}

		public String toString() {
			return 	"Ort:\t\t\t" + ort + "\n" +
					"PLZ:\t\t\t" + plz + "\n" +
					"Strasse/Hausnummer:\t" + strasse + ", " + hausNr + "\n"; 
		}
}
