package shop.local.domain.exceptions;

import shop.local.valueObjects.Artikel;
import shop.local.valueObjects.Kunde;
import shop.local.valueObjects.Mitarbeiter;


public class MitarbeiterExistiertBereitsException extends Exception {

       /**
	 * 
	 */
	private Mitarbeiter mitarbeiter;


    public MitarbeiterExistiertBereitsException(Mitarbeiter mitarbeiter, String zusatzMsg) {
        super("Mitarbeiter " + mitarbeiter.getVorname()  + " existiert bereits" + zusatzMsg);
        this.mitarbeiter = mitarbeiter;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }
}
