package projekti_lejos;

import lejos.robotics.subsumption.Behavior;




	/** 
	 * Käyttäytymisluokka joka huolehtii robotin automaattiohjauksen kääntymisestä ja nopeudesta.
	 * 
	 * @author Ryhmä 6
	 * @version 2.0
	 */
	public class TestColor implements Behavior {
		private Colorsensor O;
		private Colorsensor V;
		private Motors M;
		private read r;
		
		/**
		 * suppressed totuusarvoa käytetään käyttäytymisen pysäyttämiseen.
		 */
		private boolean suppressed = false;
		
		private boolean s = false;
		/**
		 * muuttujaa kulma käytetään kääntymiskulman asettamiseen.
		 * 
		 */
		private int kulma = 40;
		/**
		 * i muuttujaan tallennetaan tietokoneelta saatu lukuarvo.
		 */
		int i;
		/**
		 * muuttuja y on kerroin palautettavalle kulmalle robotin käännöksen jälkeen.
		 */
		private int y=0;
		/**
		 * muuttujaa x kasvatetaan jos robotti liikkuu tarpeeksi pitkälle ennen kuin se huomaa väriä.
		 */
		private int x;
		

		/**
		 * Konstruktori
		 * @param V mahdollistaa vasemman värianturin käytön
		 * @param O mahdollistaa oikean värianturin käytön
		 * @param m mahdollistaa moottoritoiminnot.
		 * @param r mahdollistaa lukutoiminnon.
		 */
		public TestColor(Colorsensor V , Colorsensor O ,Motors m, read r){
			M = m;
			this.V = V;
			this.O = O;
			this.r = r;
		}



		/**
		 *
		 *@see lejos.robotics.subsumption.Behavior#takeControl()
		 */
		public boolean takeControl() {
			boolean b = false;
//			x = r.getX();
//			if( x == 999 || x == 777){
			if (!V.ColorTesting()) {
				b = true;
			} else if (!O.ColorTesting()) {
				b = true;

			}else {
				b = false;
			}

			return b;
			}

		/**
		 *
		 * @see lejos.robotics.subsumption.Behavior#suppress()
		 */
		public void suppress() {
			suppressed = true;

		}
		/**
		 *
		 * @see lejos.robotics.subsumption.Behavior#action()
		 */
		public void action() {
			try {
				i = r.getX();
				System.out.println(i);
				if(i >= 100 && i <= 600){
				M.leftMotor.setSpeed(i);
				M.rightMotor.setSpeed(i);
				}


			} catch (Exception e) {
			}


			M.rotating.setSpeed(1000);
		

			suppressed=false;

		if (!O.ColorTesting()) {
			M.resetTacho();

			M.rotateleftB(kulma);
			y++;
			while(!O.ColorTesting()){
				int tacho = -M.Tacho();
				if(tacho< -420 && x==0){
					M.rotateleftB(10);
					x++;
				}

	
			}

			M.resetTacho();

			M.rotaterightB((y+1)*kulma+(x*10));
			x=0;
			while(!V.ColorTesting()){
				int tacho = -M.Tacho();
				if(tacho< -420 && x==0){
					M.rotaterightB(10);
					x++;
				}

			}

			M.rotateleftB(kulma+(x*10));


			x = 0;
			y=0;
			s=false;

		}



		if (!V.ColorTesting()) {
				M.resetTacho();
				M.rotaterightB(kulma);
				y++;
				while(!V.ColorTesting()){
					int tacho = -M.Tacho();
					if(tacho< -420 && x==0){
						M.rotaterightB(10);
						x++;
					}

				}

				M.resetTacho();
				M.rotateleftB((y+1)*kulma+(x*10));
				x=0;
				while(!O.ColorTesting()){
					int tacho = -M.Tacho();
					if(tacho< -420 && x==0){
						M.rotateleftB(10);
						x++;
					}

				}

				M.rotaterightB(kulma+(x*10));

				x = 0;
				y=0;
				s=false;
				
			}




		read.readboolean = true;
		}


}

