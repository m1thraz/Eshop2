package shop.local.domain.exceptions;

import shop.local.valueObjects.Artikel;


public class ArtikelExistiertBereitsException extends Exception {

       /**
	 * 
	 */
	private Artikel artikel;


    public ArtikelExistiertBereitsException(Artikel artikel, String zusatzMsg) {
        super("Artikel " + artikel.getBezeichnung() + " und Nummer " + artikel.getArtNr()
                + " existiert bereits" + zusatzMsg);
        this.artikel = artikel;
    }

    public Artikel getArtikel() {
        return artikel;
    }
}
