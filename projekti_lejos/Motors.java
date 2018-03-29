package projekti_lejos;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;


/**
 * Luokka luo moottorit ja niiden stoiminnot.
 * 
 * @author Ryhmä 6
 * @version 2.0
 *
 */
public class Motors {

	/*
	 * muuttujaa x  käytetään parametrina Kääntymismetodeissa.
	 */
	int x;
	RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
	RegulatedMotor rotating =  new EV3MediumRegulatedMotor(MotorPort.D);

	/**
	 * Konstruktori asettaa oletusnopeudet moottoreille.
	 */
	public Motors(){
		leftMotor.setSpeed(150);
		rightMotor.setSpeed(150);
		rotating.setSpeed(600);
	}

	
	/**
	 * aloittaa vetävien renkaiden moottoreiden synkronoinnin.
	 */
	public void startSync() {
		leftMotor.synchronizeWith(new EV3LargeRegulatedMotor[] { (EV3LargeRegulatedMotor) rightMotor });
		leftMotor.startSynchronization();
	}
	
	/*
	 * lopettaa vetävien renkaiden moottoreiden synkronoinnin
	 */
	public void endSync() {
		leftMotor.endSynchronization();

	}

	/**
	 * liikuttaa moottoreita eteenpäin.
	 */
	public void backward() {
		startSync();
		leftMotor.forward();
		rightMotor.forward();
		endSync();
	}
	/**
	 * liikuttaa moottoreita taaksepäin.
	 */
	public void forward() {
		startSync();
		leftMotor.backward();
		rightMotor.backward();
		endSync();
	}

	/**
	 * kääntää moottoreita tietyn verran.
	 * @param x on kulman suuruus kuinka paljon käännetään vasemalle.
	 */
	public void rotateleftB(int x){
		rotating.rotate(-x,true);
		rotating.waitComplete();
	}
	
	/**
	 * kääntää moottoreita tietyn verran.
	 * @param x on kulman suuruus kuinka paljon käännetään oikealle.
	 */
	public void rotaterightB(int x){
		rotating.rotate(x,true);
		rotating.waitComplete();
	}
	/**
	 * kääntää moottoreita tietyyn kulmaan.
	 * @param x on kulman suuruus kuinka paljon käännetään vasemalle.
	 */
	public void rotateleft(int x){
		rotating.rotateTo(-x,true);
		rotating.setSpeed(100);

	}
	/**
	 * kääntää moottoreita tiettyyn kulmaan.
	 * @param x on kulman suuruus kuinka paljon käännetään oikealle.
	 */
	public void rotateright(int x){
		rotating.rotateTo(x,true);
		rotating.setSpeed(100);
	}
		/**
		 * päästää moottorit vapaaalle ja asettaa pyörät oletuskulmaan.
		 */
		public void reset(){
			rotating.flt();
			rotating.rotateTo(0);


	}
		/**
		 * päästää moottorit vapaalle ja lopettaa kääntämisen.
		 */
		public void reset1(){
			rotating.flt();
			rotating.rotate(0);

	}
	
	/**
	 * nollaa moottorin pyötimisen laskemisen.
	 */
	public void resetTacho() {
		leftMotor.resetTachoCount();
	}
	/**
	 * kertoo kuinka paljon moottori on pyörinyt asteissa.
	 * @return palauttaa lukuarvon asteluvusta.
	 */
	public int Tacho(){
		int r = leftMotor.getTachoCount();

		return r;
	}

	/**
	 * pysäyttää vetävien pyörien moottorit.
	 */
	public void Stop() {
		leftMotor.stop();
		rightMotor.stop();
	}


}

