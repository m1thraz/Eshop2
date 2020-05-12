package shop.local.valueObjects;

public class Kunde extends Benutzer{

	
		protected  Adresse adresse;
		private Warenkorb warenkorb;
		private int personNr = 1000;


	
		public Kunde(String vorname, String nachname, String loginName, String pw, int personenNr, Adresse adresse) {
			super(loginName, vorname, nachname, pw);
			this.adresse=adresse;
			this.warenkorb=new Warenkorb(null, null);
			this.warenkorb.setKunde(this);
			this.personNr = ++ personNr; 
		}
		public Warenkorb getWarenkorb() {
			return warenkorb;
		}
		public void setWarenkorb(Warenkorb warenkorb) {
			this.warenkorb = warenkorb;
		}
		public String toString() {
			return 	"Login Name:\t\t"	+ loginName +	"\n"+
					"Vorname:\t\t"	+ vorname 	+	"\n"+
					"Nachname:\t\t"	+ nachname  +	"\n"+
					adresse + "" +"PersonNummer:\t\t"+ personNr + "\n";
		}
		public Adresse getAdresse() {
			return adresse;
		}
		public void setAdresse(Adresse adresse) {
			this.adresse = adresse;
		}
		public int getPersonNr() {
			return personNr;
		}
}
