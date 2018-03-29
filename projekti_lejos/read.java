package projekti_lejos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.robotics.subsumption.Behavior;


/**
 * read-luokka hoitaa tiedon liikkumisen tietokoneelle ja takaisin.
 * 
 * @author Ryhmä 6
 * @version 2.0
 */
public class read implements Behavior{
	
	ServerSocket server ;
	DataOutputStream out;
	DataInputStream in;
	
	/*
	 * x muuttujaan tallennetaan tietokoneelta luettu lukuarvo.
	 */
	private int x;
	/**
	 * suppressed totuusarvoa käytetään käyttäytymisen pysäyttämiseen.
	 */
	private boolean suppressed = false;
	
	static boolean readboolean = true;

	/**
	 * konstruktori joka luo yhteyden tietokoneeseen.
	 */
	public read(){
		try {

			server = new ServerSocket(1111);

			Socket s = server.accept();
			s.setSoTimeout(50);
			in = new DataInputStream(s.getInputStream());




		} catch (IOException e) {
			
		}
		// TODO Auto-generated method stub



	}




	
	/*
	 * paluttaa muuttujan x arvon.
	 */
	public int getX() {

		return x;
	}

	/**
	 * asettaa muutttujaan x arvon.
	 * @param x asettaa uuden arvon muuttujaan.
	 */
	public void setX(int x) {
		this.x = x;
	}





	@Override
	/**
	 * @see lejos.robotics.subsumption.Behavior#takeControl()
	 */
	public boolean takeControl() {

		// TODO Auto-generated method stub
		return readboolean;
	}

	@Override
	/**
	 * @see lejos.robotics.subsumption.Behavior#action()
	 */
	public void action() {

	try {
//			do{
			x = in.readInt();
			System.out.println("-");
			System.out.println(x);
//			}while( x != 999 || x != 123 );


	

		} catch (IOException e) {
		}
		// TODO Auto-generated method stub
		readboolean = false;
		suppressed = true;
	}

	@Override
	/**
	 *
	 * @see lejos.robotics.subsumption.Behavior#suppress()
	 */
	public void suppress() {
		suppressed = true;

		// TODO Auto-generated method stub

	}

}
