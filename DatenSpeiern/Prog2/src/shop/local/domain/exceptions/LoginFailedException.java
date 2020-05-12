package shop.local.domain.exceptions;

public class LoginFailedException extends Exception{
		/**
		 * 
		 */
		private String loginName;
		
		public LoginFailedException(String loginName) {
			super("Login für Benutzername " + loginName + " ist fehlgeschlagen.");
			this.setLoginName(loginName);		
		}

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}
}