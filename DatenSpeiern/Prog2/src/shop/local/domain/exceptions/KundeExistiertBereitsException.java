package shop.local.domain.exceptions;

import shop.local.valueObjects.Artikel;
import shop.local.valueObjects.Kunde;


public class KundeExistiertBereitsException extends Exception {

       /**
	 * 
	 */
	private Kunde kunde;


    public KundeExistiertBereitsException(Kunde kunde, String zusatzMsg) {
        super("Kunde " + kunde.getVorname()  + " existiert bereits" + zusatzMsg);
        this.kunde = kunde;
    }

    public Kunde getKunde() {
        return kunde;
    }
}
