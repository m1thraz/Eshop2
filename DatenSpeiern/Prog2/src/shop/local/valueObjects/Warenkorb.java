package shop.local.valueObjects;

import java.util.HashMap;
import java.util.Map;

public class Warenkorb {


    private int datum;
    private Artikel artikel;
    private Kunde kunde;

    public Warenkorb(Artikel artikel, Kunde _kunde) {
        this.artikel = artikel;
        this.kunde = _kunde;
    }

    @Override
	public String toString() {
		return "Warenkorb  artikel = " + artikel.getBezeichnung() + " Kunde = " + kunde.getVorname()  ;
	}

	public int getDatum() {
        return datum;
    }

    public void setDatum(int datum) {
        this.datum = datum;
    }

 

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }
		
}
