package shop.local.domain.exceptions;

import shop.local.valueobjects.Artikel;


public class ArtikelSchonVorhandenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArtikelSchonVorhandenException(Artikel artikel) {
        super("Artikel " + artikel.getBezeichnung() + " und Nummer " + artikel.getArtNr()
                + " ist schon Vorhanden" );
    }


}

