package shop.local.domain.exceptions;

public class ArtikelMitArtikelNrExistiertBereitsException extends Exception{
	
	public ArtikelMitArtikelNrExistiertBereitsException(int artNr) {
		super("Artikel mit Artikelnummer " + artNr 
				+ " existiert bereits");
	}

}
