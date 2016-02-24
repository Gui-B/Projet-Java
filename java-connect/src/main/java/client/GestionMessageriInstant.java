/**
 * 
 */
package client;

import java.io.IOException;

import common.Protocole;
import serveur.Serveur;

/**
 * @author kriss
 *
 */
public class GestionMessageriInstant {
	private Protocole proto;
	private Client c;
	private Serveur srv;

	/**
	 * 
	 */
	public GestionMessageriInstant() {
		// TODO Auto-generated constructor stub
		this.proto= new Protocole();
		this.c= new Client();
		this.srv = new Serveur();
	}
	
	public String traiter (){
		System.out.println("Mode méssagerie instantané");
		
		Boolean OK = false;
		try 
		{
			String port = this.InitialisationSock();
			String[] retour=this.c.communiquer(proto.ReqStartEcoute(port)).split("\\|");
			int c=0;
			if (retour[0].equals("200")){
				OK = true;
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (OK) {
			
		} else {
			System.out.println("erreur pendant l'initialisation de la messagerie instantané");
		}
		return "Fin du mode méssagerie instantané";
	}
	
	private String InitialisationSock(){
		return "blbl";
	}
	
	private int StopSock(){
		return 0;
	}

}
