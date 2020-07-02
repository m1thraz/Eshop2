package shop.local.domain.exceptions;

import shop.local.valueobjects.Artikel;

public class ArtikelNichtImWarenKorbException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Artikel artikel;
	
	public ArtikelNichtImWarenKorbException(Artikel artikel) {		
				
		super(artikel.getBezeichnung() + "ist nicht in Ihrem Warenkorb.");
		this.artikel = artikel;
	}
}
