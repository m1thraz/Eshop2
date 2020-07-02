package shop.local.valueobjects;

public class Mitarbeiter extends Benutzer{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public Mitarbeiter(String loginName, String vorname, String nachname, String pw) {
			super(loginName, vorname,nachname,pw);

		}

		public String toString() {
			return 	"Login Name: "	+ loginName +	"\n"+
					"Vorname: "	+ vorname 	+	"\n"+
					"Nachname: "	+ nachname  +	"\n";
		}
}
