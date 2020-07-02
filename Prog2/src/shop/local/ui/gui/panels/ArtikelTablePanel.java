package shop.local.ui.gui.panels;


import javax.swing.*;

import shop.local.valueobjects.Artikel;

import java.util.Collections;



	public class ArtikelTablePanel extends JTable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ArtikelTablePanel(java.util.List<Artikel> artikel) {
			super();


			updateArtikel(artikel);
		}
		
		public void updateArtikel(java.util.List<Artikel> artikel) {
			Collections.sort(artikel, (b1, b2) -> b1.getArtNr() - b2.getArtNr());
		}
	


}
