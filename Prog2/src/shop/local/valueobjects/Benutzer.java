package shop.local.valueobjects;

import java.io.Serializable;

public class Benutzer implements Serializable {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		protected int nr;
		protected String loginName;
		protected String vorname;
		protected String nachname;
		protected String passwort;

		
		public Benutzer(String loginName, String vorname, String nachname, String passwort) {
			this.loginName=loginName;
			this.vorname=vorname;
			this.nachname=nachname;
			this.passwort=passwort;		
		}
		
		public Benutzer(int nr,String loginName, String vorname, String nachname, String passwort) {
			this.nr=nr;
			this.loginName=loginName;
			this.vorname=vorname;
			this.nachname=nachname;
			this.passwort=passwort;		
		}

		public String getVorname() {
			return vorname;
		}

		public void setVorname(String vorname) {
			this.vorname = vorname;
		}

		public String getNachname() {
			return nachname;
		}

		public void setNachname(String nachname) {
			this.nachname = nachname;
		}

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public String getPw() {
			return passwort;
		}

		public void setPw(String passwort) {
			this.passwort = passwort;
		}
}
