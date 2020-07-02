package shop.local.valueobjects;

public class Kunde extends Benutzer{

	
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		protected  Adresse adresse;
		private Warenkorb warenkorb;
		private String login;

		public Kunde(String vorname, String nachname, String loginName, String pw, Adresse adresse) {
			super(loginName, vorname, nachname, pw);
			this.adresse=adresse;
			this.warenkorb=new Warenkorb();
			this.warenkorb.setKunde(this);
		}
		public  Warenkorb getWarenkorb() {
			return warenkorb;
		}
		public void setWarenkorb(Warenkorb warenkorb) {
			this.warenkorb = warenkorb;
		}
		
		public Adresse getAdresse() {
			return adresse;
		}
		public void setAdresse(Adresse adresse) {
			this.adresse = adresse;
		}
		
		public String getLogin() {
			return login;
		}
		
		public String toString() {
			return 	"Login Name:\t\t"	+ loginName +	"\n"+
					"Vorname:\t\t"	+ vorname 	+	"\n"+
					"Nachname:\t\t"	+ nachname  +	"\n"+
					adresse + "\n";
		}
}
