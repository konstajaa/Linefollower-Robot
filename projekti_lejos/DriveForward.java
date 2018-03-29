package projekti_lejos;


import lejos.robotics.subsumption.Behavior;


/**
 * käyttäytymisluokka joka liikuttaa robottia automaattiohjauksessa.
 * 
 * @author Ryhmä6 
 * @version 2.0
 * 
 */
public class DriveForward implements Behavior {
		/**
		 * suppressed totuusarvoa käytetään käyttäytymisen pysäyttämiseen.
		 */
		private boolean suppressed = false;
		/**
		 * m on Motors-olio jota tarvitaan moottoritoimintojen suorittamisessa.
		 */
		private Motors m;
		/**
		 * r on read olio jota köytetöön datan vastaanottamiseen käyttöliittymästä.
		 */
		private read r;
		/**
		 * muuttujaa b käytetään totuusarvon palauttamiseen jos käyttäytyminen ottaa kontrollin.
		 */
		private boolean b = false;
		/**
		 * muuttujaan x tallennetaan käyttöliittymästä saatu lukuarvo.
		 */
		int x;
		
		
		/**
		 * konstruktori joka saa parametrinaan Motors olion ja read olion.
		 * @param m mahdollistaa moottoritoiminnot
		 * @param r mahdollistaa lukutoiminnon.
		 */
		public DriveForward(Motors m, read r) {
			this.m = m ;
			this.r = r;


		}
		
	
		@Override
		/*
		 * (non-Javadoc)
		 * @see lejos.robotics.subsumption.Behavior#takeControl()
		 */
		public boolean takeControl() {
				x = r.getX();
				if (x== 999){

					b = true;
				}
				else {
				b = false;
				}

			
			return b;		// * true kun käyttöliittymästä painetaan autopilot tai semiauto välilehdeltä "START".

		}

		@Override
		/*
		 * (non-Javadoc)
		 * @see lejos.robotics.subsumption.Behavior#suppress()
		 */
		public void suppress() {
			suppressed = true;
		}

		@Override
		/*
		 * (non-Javadoc)
		 * @see lejos.robotics.subsumption.Behavior#action()
		 */
		public void action() {
			suppressed = false;
			m.backward();
			m.leftMotor.setSpeed(200);
			m.rightMotor.setSpeed(200);
			
			while (!suppressed)
				Thread.yield();
			


		}

	}


