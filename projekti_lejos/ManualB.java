package projekti_lejos;


import lejos.robotics.subsumption.Behavior;

/**
 * Manuaaliohjauksen suorittava käyttäytyminen.
 * 
 * @author Ryhmä 6
 * @version 2.0
 *
 */
public class ManualB implements Behavior {
	/**
	 * suppressed totuusarvoa käytetään käyttäytymisen pysäyttämiseen.
	 */
	private boolean suppressed = false;
	/**
	 * m on Motors-olio jota tarvitaan moottoreiden pysäyttämiseen.
	 */
	private Motors m;
	/**
	 * r on read olio jota köytetöön datan vastaanottamiseen käyttöliittymästä.
	 */
	private read r;
	/*
	 * nopeus muuttujalla sädetään moottorien nopeutta.
	 */
	private int nopeus = 100;
	/**
	 * muuttujaan x tallennetaan käyttöliittymästä saatu lukuarvo.
	 */
	int x ;
	/**
	 * muuttujaa b käytetään totuusarvon palauttamiseen jos käyttäytyminen ottaa kontrollin.
	 */
	boolean b = false;
	
	/**
	 * muuttujaan command tallennetaan käyttöliittymästä saatu lukuarvo kun luetaan uudestaan.
	 */
	int command;

	/**
	 * konstruktori joka saa parametrinaan Motors olion ja read olion.
	 * @param m mahdollistaa moottoritoiminnot.
	 * @param rmahdollistaa lukutoiminnon.
	 */
	public ManualB(Motors m, read r){
		this.m = m;
		this.r = r;

	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see lejos.robotics.subsumption.Behavior#takeControl()
	 */
	public boolean takeControl() {
		x = r.getX();
		if (x == 50){
			b = true;

		}
		else {
			b = false;
			}
		return b;
		//true kun käyttöliittymästä manual välilehdeltä painetaan Start.
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see lejos.robotics.subsumption.Behavior#action()
	 */
	public void action() {
		int y = 0;
		System.out.println("XXX");
		while (command!=1){
	try {


					long t = System.currentTimeMillis();
					long t2 =t + 1000 ;
					long t3 =t + 2000 ;
					long t4 =t + 3000 ;



					boolean p = false;
					command = r.in.readInt();
					r.setX(command);
					System.out.println("x");
					System.out.println(command);



					if (command == 39){
							m.rotateright(55);



					}

					else if (command == 38) {
						t = System.currentTimeMillis();
							m.backward();
							nopeus = nopeus + 100;
							m.leftMotor.setSpeed(nopeus);
							m.rightMotor.setSpeed(nopeus);
							command = r.in.readInt();
							r.setX(command);
								} else if (command == 37) {


							m.rotateleft(55);


						} else if (command == 40) {
							m.leftMotor.setSpeed(200);
							m.rightMotor.setSpeed(200);
							nopeus = 200;
						}

							else if (command == -2 ){
							m.reset1();


							}



		} catch (Exception E) {
		}

	}
		m.reset();
		r.setX(1);





		suppressed = false;
		// TODO Auto-generated method stub

}

	@Override
	/*
	 * (non-Javadoc)
	 * @see lejos.robotics.subsumption.Behavior#suppress()
	 */
	public void suppress() {
		suppressed =true;

	}


}
