package projekti_lejos;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;



/**
 * Tämä luokka ajaa pääohjelman ja luo käyttäytymishierarkian.
 * 
 * @author Ryhmä 6
 * @version 2.0
 *
 */
public class Main {
	
	/**
	 * pääohjelma
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.start();
	}
		
		/*
		 * pääohjelman toteutus
		 */
		public void start(){

		try {


			
			read r = new read();
			Motors m = new Motors();
			Colorsensor vasen = new Colorsensor("S3");
			Colorsensor oikea = new Colorsensor("S4");
			IRSensor IR = new IRSensor("S1");
			// testataan väri
			vasen.Initialize();
			oikea.Initialize();
			
			//luodaan käyttäytmiset
			Behavior b0 = r;
			Behavior b1 = new Idle(m,r);
			Behavior b2 = new ManualB(m,r);
			Behavior b3 = new DriveForward(m,r);
			Behavior b4 = new TestColor(vasen, oikea, m ,r);
			Behavior b5 = new Remote_Behavior(IR,r);

			// käyttäytmislista
			Behavior[] behaviorList = { b1, b3, b4,  b2 , b5, b0};
			
			Arbitrator eka = new Arbitrator(behaviorList);

			System.out.println("ready:");
			eka.go();
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("virhe");
			Delay.msDelay(10000);

		}
	}
	}
