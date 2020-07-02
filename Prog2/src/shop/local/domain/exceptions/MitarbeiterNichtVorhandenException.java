package shop.local.domain.exceptions;


import shop.local.valueobjects.*;

public class MitarbeiterNichtVorhandenException extends Exception{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public MitarbeiterNichtVorhandenException(Mitarbeiter mitarbeiter) {
		super("Mitarbeiter: " + mitarbeiter.getLoginName() + " nicht vorhanden!");
	}
}
