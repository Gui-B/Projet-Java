/**
 * 
 */
package common;

import java.io.IOException;
import java.util.Scanner;

import client.Client;
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
		String commande;
		Scanner sc= new Scanner(System.in);
		
		do {
			System.out.print("1 écoute, 2 connection, q pour quitter: ");
			commande= sc.nextLine();
			if (commande.equals("q")) break;
			if (commande.equals("1")){
				try 
				{
					String port = this.InitialisationSock();
					String[] retour=this.c.communiquer(proto.ReqStartEcoute(port)).split("\\|");
					int c=0;
					if (retour[0].equals("200")){
						
					}
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (commande.equals("2")){
				
			}
		}while(true);
		return "Fin du mode méssagerie instantané";
	}
	
	private String InitialisationSock(){
		srv = new Serveur(12346);
		return "12346";
	}
	
	private int StopSock(){
		return 0;
	}

}
