package shop.local.valueObjects;

public class Mitarbeiter extends Benutzer{
	
	private int personNr = 10000;


		public int getPersonNr() {
		return personNr;
	}

		public Mitarbeiter(String loginName, String vorname, String nachname, String pw) {
			super(loginName, vorname,nachname,pw);
			this.personNr = ++ personNr;
		}

		public String toString() {
			return 	"Login Name: 		"	+ loginName +	"\n"+
					"Vorname: 			"	+ vorname 	+	"\n"+
					"Nachname: 			"	+ nachname  +	"\n";
		}


}
