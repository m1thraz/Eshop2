package shop.local.domain.exceptions;

public class ArtikelNrSchonVorhandenException extends Exception{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArtikelNrSchonVorhandenException(int artNr) {
		super( artNr + " bereits vorhanden");
		
	}

}
