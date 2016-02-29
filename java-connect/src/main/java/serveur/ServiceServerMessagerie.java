package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServiceServerMessagerie extends Thread
{
	PrintStream fluxSortieSocket;
	BufferedReader fluxEntreeSocket;
	Socket socketService;
	int id;

	public ServiceServerMessagerie(Socket pSocket, int idClient)
	{
		this.socketService=pSocket;
		this.id=idClient;
	}

	public void envoyer(String message)
	{
		fluxSortieSocket.println(message);
	}
	
	public void quit() throws IOException
	{
		this.socketService.close();
	}
	
	public void run ()
	{
		try 
		{
			fluxEntreeSocket= new BufferedReader(new InputStreamReader(this.socketService.getInputStream()));
			fluxSortieSocket= new PrintStream(this.socketService.getOutputStream());
			String entree;
			String sortie;

			do
			{
				//Recevoir
				entree= fluxEntreeSocket.readLine();
				System.out.println(" ");
				System.out.println("Recu de "+this.id+": "+entree);
				System.out.print("Dire:");
				
				if (entree.equals("q") || entree.equals(null)) break;

			}while(true);
			
			System.out.println("Fermeture de connexion client "+this.id);
			socketService.close();
		} 
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}
	}
}