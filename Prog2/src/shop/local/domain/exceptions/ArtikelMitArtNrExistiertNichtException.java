package shop.local.domain.exceptions;

public class ArtikelMitArtNrExistiertNichtException extends Exception{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public ArtikelMitArtNrExistiertNichtException(int falscheArtNr) {
			super("Artikel mit Artikelnummer " + falscheArtNr 
					+ " existiert nicht");
		}
}
