package projekti_lejos;

import lejos.hardware.ev3.LocalEV3;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;




/**
 * Luokka luo infrapunasensorin kaukosäätimen toimintaa varten.
 * 
 * @author Ryhmä6
 * @version 2.0
 *
 */
public class IRSensor {
	
		
		/**
		 *  a muuttujaan tallennetaan totuusarvo, mikäli kaukosäätimessä painetaan oikeeaa näppäintä.
		 */
	 	boolean a = false;
	 	Port port1;
	 	/**
	 	 * irsensor on EV3IRSensor olio jota käytetään infrapuna-anturin luonnissa.
	 	 */
	 	EV3IRSensor irsensor;
	 	/**
	 	 * remote on SampleProvider olio joka tuottaa sample- muuttujaan numeroarvon infrapuna-anturin havaiinnoista.
	 	 */
	 	SampleProvider remote;
	 	/**
	 	 *sample kertoo infrapuna-anturin saaman väriarvon.
	 	 */
	 	float[] sample;

		/**
		 * Konstruktori joka luo  värianturit
		 * @param portti Pääohjelma antaa portin nimen parametrina johon infrapuna-anturi on kytketty.
		 */
		public IRSensor(String portti) {
	  	 port1 = LocalEV3.get().getPort(portti);
		 irsensor = new EV3IRSensor(port1);

		 remote = ((EV3IRSensor) irsensor).getDistanceMode();
		 sample = new float[remote.sampleSize()];
		}

		/**
		 * palauttaa float muuttujan
		 * @return palauttaa sample muuttujan joka on numeroarvo anturin havainnoista.
		 */
		public float[] getSample() {
			return sample;
		}





		
		/**
		 * RemoteControl palauttaa totuusarvon jso saadaan haluttu komento
		 * @return palauttaa muuttujaan a arvon true jos kaukosäätimeltä saadaan oike komento.
		 */
	  	 public boolean RemoteControl(){

	  		 int remote = ((EV3IRSensor) irsensor).getRemoteCommand(0);
	  		 if (remote == 1)
	  		 {	a = true;

	  		 }return a;

}
}
