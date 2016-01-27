package client;

import java.util.Scanner;

public class mainClient {

	public static void main(String[] args) 
	{
		GestionClient gs= new GestionClient();
		Scanner sc= new Scanner(System.in);
		String commande;
		String retour;
		
		try 
		{
			Client client= new Client();
			do
			{
				System.out.print("Commande: ");;
				commande= sc.nextLine();
				
				if (commande.equals("q")) break;
				retour=client.communiquer(commande);		
				System.out.println("Retour: "+retour);
			}while(true);
			
			client.fermer();
		} 
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}

	}

}
