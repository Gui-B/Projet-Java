package client;
import java.io.*;
import java.net.*;

public class Client extends Thread
{
	String reponse;
	Socket leSocket;
	PrintStream fluxSortieSocket;
	BufferedReader fluxEntreeSocket;
	
	public Client() 
	{
		try
		{
			// TODO Auto-generated method stub
			//Creation d'un socket + connexion a machine X port Y
			leSocket= new Socket("127.0.0.1", 12345);
			System.out.println("Connecte sur :"+ leSocket);
						
			//Creation d'un flux d'entree
			fluxEntreeSocket= new BufferedReader(new InputStreamReader(leSocket.getInputStream()));
					
			//Creation d'un flux de sortie
			fluxSortieSocket= new PrintStream(leSocket.getOutputStream());
		}
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public Client(String ip, int port) 
	{
		try
		{
			// TODO Auto-generated method stub
			//Creation d'un socket + connexion a machine X port Y
			leSocket= new Socket(ip, port);
			System.out.println("Connecte sur :"+ leSocket);
						
			//Creation d'un flux d'entree
			fluxEntreeSocket= new BufferedReader(new InputStreamReader(leSocket.getInputStream()));
					
			//Creation d'un flux de sortie
			fluxSortieSocket= new PrintStream(leSocket.getOutputStream());
		}
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public void envoyer(String message)
	{
		fluxSortieSocket.println(message);
	}
	
	public String recevoir() throws IOException
	{
		return fluxEntreeSocket.readLine();
	}
	public String communiquer(String message) throws IOException
	{
		String retour=new String("Vraiment pas bon ...");
		this.envoyer(message);
		retour=this.recevoir();
		return retour;
	}
	
	public void fermer() throws IOException
	{
		try 
		{
			this.leSocket.close();
			System.out.println("Fermeture de la connexion");
		} 
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public void run ()
	{
		String entree;
		try 
		{

			do
			{
				//Recevoir
				entree= this.recevoir();
				System.out.println("");
				System.out.println("Recu :"+ entree);
				System.out.print("Dire: ");
				
				if (entree.equals("q") || entree.equals(null)) break;

			}while(true);
			
			this.fermer();
		} 
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}
	}
}
