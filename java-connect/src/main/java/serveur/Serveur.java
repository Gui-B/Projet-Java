package serveur;
import java.io.*;
import java.net.*;

import java.net.ServerSocket;

public class Serveur
{
	ServerSocket socketEcoute;

	public Serveur() 
	{
		try 
		{
			//Creation socket 12345
			this.socketEcoute= new ServerSocket(12345);
			System.out.println("Nouveau serveur: "+socketEcoute);
		} 
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}
	}


	public void connectClient(GestionServeur gs) throws IOException
	{
		Socket socketService= socketEcoute.accept();
		System.out.println("Nouvelle connexion: "+socketService);
		ServiceServer client=new ServiceServer(socketService, gs);
		client.start();
	}

	public void fermerServeur ()
	{
		try 
		{
			this.socketEcoute.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void communiquer(GestionServeur gs) throws IOException
//	{	
//		String entree;
//		String sortie;
//		Socket socketService;
//		PrintStream fluxSortieSocket;
//		BufferedReader fluxEntreeSocket;
//		socketService= socketEcoute.accept();
//		System.out.println("Nouvelle connexion: "+socketService);
//
//
//		fluxEntreeSocket= new BufferedReader(new InputStreamReader(socketService.getInputStream()));
//		fluxSortieSocket= new PrintStream(socketService.getOutputStream());
//
//		try 
//		{
//			//Recevoir
//			entree= fluxEntreeSocket.readLine();
//			System.out.println("Recu: "+entree);
//
//			//Envoyer
//			sortie= gs.traiter(entree);
//			fluxSortieSocket.println(sortie);
//
//			System.out.println("Fin de transfert");
//			this.fermerService();
//		} 
//		catch (Exception e) 
//		{
//			System.err.println(e);
//			e.printStackTrace();
//		}
//	}
}

class ServiceServer extends Thread
{
	PrintStream fluxSortieSocket;
	BufferedReader fluxEntreeSocket;
	Socket socketService;
	GestionServeur gs;

	public ServiceServer(Socket pSocket, GestionServeur pGestion)
	{
		this.socketService=pSocket;
		this.gs= pGestion;
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
				System.out.println("Recu: "+entree);
				
				if (entree==null) break;

				
				//Envoyer
				sortie= gs.traiter(entree);
				fluxSortieSocket.println(sortie);

				System.out.println("Fin de transfert");
			}while(true);

			System.out.println("Fermeture de connexion client");
			socketService.close();
		} 
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}
	}

}

