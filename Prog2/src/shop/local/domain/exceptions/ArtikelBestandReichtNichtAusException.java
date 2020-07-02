package shop.local.domain.exceptions;

import shop.local.valueobjects.Artikel;

public class ArtikelBestandReichtNichtAusException extends Exception{
	
	

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public ArtikelBestandReichtNichtAusException(Artikel artikel) {
			super(artikel.getBezeichnung() + " " + artikel.getBestand() 
					+ " haben wir Ihre gewünschte Menge leider nicht auf Lager.");
		}
	


}
