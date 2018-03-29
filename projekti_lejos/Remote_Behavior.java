package projekti_lejos;

import lejos.robotics.subsumption.Behavior;

/**
 * käyttäytymisluokka joka sulkee koko ohjelman painettaessa kaukosäädintä 
 * tai käyttöliittymän exit-painiketta.
 * 
 * @author Ryhmä 6
 * @version 2.0
 */
public class Remote_Behavior implements Behavior {
		
		read r;
		
		private IRSensor remote;
		/**
		 * suppressed totuusarvoa käytetään käyttäytymisen pysäyttämiseen.
		 */
		private boolean suppressed = false;
		/**
		 * muuttujaa b käytetään totuusarvon palauttamiseen jos käyttäytyminen ottaa kontrollin.
		 */
		boolean b= false;
		
		
		/**
		 * konstruktori
		 * @param k mahdollistaa IRSensor luokan ominaisuuksien käytön
		 * @param r mahdollistaa lukutoiminnon read luokasta
		 */
		public Remote_Behavior(IRSensor k ,read r) {
			remote = k;
			this.r=r;
		}

		@Override
		/**
		 * @see lejos.robotics.subsumption.Behavior#takeControl()
		 */
		public boolean takeControl() {
			b = false;
			if (r.getX() == 666){
				b = true;
			}
				else { 
					b = false;
				
				}
				
			
			return b; // painettiinko
		}
		@Override
		/**
		 * @see lejos.robotics.subsumption.Behavior#suppress()
		 */
		public void suppress() {
			suppressed = true;

		}
		@Override
		/**
		 * @see lejos.robotics.subsumption.Behavior#action()
		 */
		public void action() {
			suppressed = false;
				System.exit(1);

		}
	}


