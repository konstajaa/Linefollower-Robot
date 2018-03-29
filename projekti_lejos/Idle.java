package projekti_lejos;


import lejos.robotics.subsumption.Behavior;



/**
 * Tämä luokka on käyttäytymisluokka, joka ottaa kontrollin,
 * kun käyttäjä painaa käyttöliittymässä painiketta "STOP".
 * Tämä luokka pysäyttää kaikkien moottoreiden liikkeet.
 * 
 * 
 * @author Ryhmä 6
 * @version 2.0
 *
 */
public class Idle implements Behavior{
	/**
	 * suppressed totuusarvoa käytetään käyttäytymisen pysäyttämiseen.
	 */
	private boolean suppressed = false;
	/**
	 * muuttujaa b käytetään totuusarvon palauttamiseen jos käyttäytyminen ottaa kontrollin.
	 */
	boolean b = false;
	/**
	 * m on Motors-olio jota tarvitaan moottoreiden pysäyttämiseen.
	 */
	private Motors m;
	/**
	 * r on read olio jota köytetöön datan vastaanottamiseen käyttöliittymästä.
	 */
	private read r;
	/**
	 * muuttujaan x tallennetaan käyttöliittymästä saatu lukuarvo.
	 */
	int x;

	/**
	 * konstruktori joka saa parametrinaan Motors olion ja read olion.
	 * @param m mahdollistaa moottoritoiminnot.
	 * @param r mahdollistaa lukutoiminnon.
	 */
	public Idle (Motors m, read r){
		this.m= m;
		this.r = r;
	}


	@Override
	/**
	 * @see lejos.robotics.subsumption.Behavior#takeControl()
	 */
	public boolean takeControl() {
			x = r.getX();
			 if (x == 1){
				b= true;

			}
			else {
				b = false;
		}
		return b;
		// true kun käyttöliittymästä painetaan STOP.
	}

	@Override
	/**
	 * @see lejos.robotics.subsumption.Behavior#action()
	 */
	public void action() {

		//pysäyttää kaikki moottorit.
		suppressed = false;
		m.leftMotor.stop();
		m.rightMotor.stop();
		m.rotating.stop();

	}

	@Override
	/**
	 * 
	 * @see lejos.robotics.subsumption.Behavior#suppress()
	 */
	public void suppress() {

		suppressed =true;

	}


}
