package client;
import java.io.*;
import java.net.*;

public class Client extends Object
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
	
	private void envoyer(String message)
	{
		fluxSortieSocket.println(message);
	}
	
	private String recevoir() throws IOException
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
}
