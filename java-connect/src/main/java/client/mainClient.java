package client;

import java.util.Scanner;

public class mainClient {

	public static void main(String[] args) 
	{
		GestionClient gc= new GestionClient();
		Scanner sc= new Scanner(System.in);
		String commande;
		String retour;
		
		try 
		{
			do
			{
				System.out.print("Commande: ");
				commande= sc.nextLine();
				
				if (commande.equals("q")) break;
				
				gc.traiter(commande.toUpperCase());
			}while(true);
			
		} 
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}

	}

}
