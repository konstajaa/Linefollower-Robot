package projekti_lejos;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;


/**
 * Luo värianturit ja niiden kättämät toiminnot.
 *
 * @author Ryhmä 6
 * @version 2.0
 */
public class Colorsensor {


	/**
	 * muuttujaa käytetään värisensorin paauttmana totuusarvona.
	 */
	boolean a = false;
	/**
	 * muuttujiin r,g,b, tallennetaan arvot jotka värianturi saa alustuksessa.
	 */
	int r, g, b;
	/**
	 * muuttujia r2,g2,b2 käytetään värien testaamisessa automaattiohjauksessa.
	 */
	int r2, g2, b2;
	/**
	 * sample kertoo värianturin saaman väriarvon.
	 */
	float[] sample;
	/**
	 * csensor on SensorMode olio jolle annetaan sensorin käyttötapa.
	 */
	SensorMode csensor;
	/**
	 * colorprovider on olio joka tuottaa sample muuttujan numeroarvon vrianturin havainnoista.
	 */
	SampleProvider colorProvider;
	/**
	 * Konstruktori joka luo  värianturit
	 * @param portti pääohjelma antaa portin nimen johon värianturi on kytketty.
	 */
	public Colorsensor(String portti) {
		Port port = LocalEV3.get().getPort(portti);
		csensor = new EV3ColorSensor(port);
		colorProvider = ((EV3ColorSensor) csensor).getRGBMode();
		sample = new float[colorProvider.sampleSize()];
	}


	/**
	 *
	 * Initialize() alustaa värianturit.
	 */
	public void Initialize() {

		System.out.println("Show Color");
		// Delay.msDelay(2000);

		colorProvider.fetchSample(sample, 0);

		r = Math.round(sample[0] * 765);
		g = Math.round(sample[1] * 765);
		b = Math.round(sample[2] * 765);

		System.out.println("Color values: ");
		System.out.println(" " + r + " " + g + " " + b);
		Delay.msDelay(1000);
	}
	/**
	 * Colortesting() -metodi vertaa väriäanturin arvoja initialize() -metodissa saatuihin arvoihin.
	 * @return palauttaa totuusarvon muuttujaan. Jos väri on sama, true.
	 */
	public boolean ColorTesting() {

		colorProvider.fetchSample(sample, 0);

		int nr = Math.round(sample[0] * 765);
		int ng = Math.round(sample[1] * 765);
		int nb = Math.round(sample[2] * 765);



		if (nr < r + 20 && nr > r - 20 && ng < g + 20 && ng > g - 20 && nb < b + 20 && nb > b - 20) {

			a = true;

		} else {
			a = false;
		}

		return a
				;

	}
}
