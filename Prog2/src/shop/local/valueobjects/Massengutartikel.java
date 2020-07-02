package shop.local.valueobjects;

public class Massengutartikel extends Artikel{

		private int einheit;

		public Massengutartikel(String bezeichnung, int artNr, double preis, int bestand, int einheit) {
			super(bezeichnung, artNr, preis, bestand);
			this.einheit = einheit;
		}

		public int getEinheit() {
			return einheit;
		}

		public void setEinheit(int einheit) {
			this.einheit = einheit;
		}		

		public boolean equals(Object andererArtikel) {
			if (andererArtikel instanceof Massengutartikel) 
				return ((this.artNr == ((Massengutartikel) andererArtikel).artNr) 
						&& (this.bezeichnung.equals(((Massengutartikel) andererArtikel).bezeichnung)));
			else
				return false;
		}
		
		@Override
		public String toString() {

			return ("Massengutartikel Nummer:  " 	+ artNr 			+  
					"  Bezeichnung:\t" 	+ bezeichnung 		+  
					"  Preis:\t\t" 	+ preis 			+  "  Packungsgröße: " +
					einheit + "  Menge:  "	+ bestand 
					);
		}
}
