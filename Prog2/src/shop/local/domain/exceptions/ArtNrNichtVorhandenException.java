package shop.local.domain.exceptions;


public class ArtNrNichtVorhandenException extends Exception{

			/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

			public ArtNrNichtVorhandenException(int artNr) {
				super("Artikel mit Artikelnummer " + artNr 	+ " existiert nicht");
			}
		

	


}
