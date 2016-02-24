package serveur;
import java.io.*;
import java.net.*;

import java.net.ServerSocket;

import common.GestionMessageriInstant;

public class Serveur
{
	private ServerSocket socketEcoute;
	private int idSocketClient;

	public Serveur() 
	{
		try 
		{
			//Creation socket 12345
			this.socketEcoute= new ServerSocket(12345);
			System.out.println("Nouveau serveur: "+socketEcoute);
			this.idSocketClient=0;
		} 
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public Serveur(int port) 
	{
		try 
		{
			//Creation socket port
			this.socketEcoute= new ServerSocket(port);
			System.out.println("Nouveau serveur: "+socketEcoute);
			this.idSocketClient=0;
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
		System.out.println("Nouvelle connexion: "+socketService+" "+socketService.getInetAddress()+" "+socketService.getLocalAddress());
		ServiceServer client=new ServiceServer(socketService, gs, this.idSocketClient++);
		client.start();
	}
	
	public void connectClient(GestionMessageriInstant gmi) throws IOException
	{
		Socket socketService= socketEcoute.accept();
		System.out.println("Nouvelle connexion: "+socketService+" "+socketService.getInetAddress()+" "+socketService.getLocalAddress());
		ServiceServer client=new ServiceServer(socketService, gmi, this.idSocketClient++);
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
}

class ServiceServer extends Thread
{
	PrintStream fluxSortieSocket;
	BufferedReader fluxEntreeSocket;
	Socket socketService;
	GestionServeur gs;
	int id;

	public ServiceServer(Socket pSocket, GestionServeur pGestion, int idClient)
	{
		this.socketService=pSocket;
		this.gs= pGestion;
		this.id=idClient;
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
				System.out.println("Recu de "+this.id+": "+entree);
				
				if (entree==null) break;

				
				//Envoyer
				sortie= gs.traiter(this.id, this.fluxSortieSocket, entree);
				
				fluxSortieSocket.println(sortie);
				System.out.println("Envoye a: "+this.id+": "+sortie);
				System.out.println("Fin de transfert de:"+this.id);
			}while(true);

			this.gs.getGestionConnexions().deconnexion(id);
			this.gs.getGestionConnexions().fermetureSocket(id);
			
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

