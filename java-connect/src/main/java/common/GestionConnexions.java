package common;

import java.io.PrintStream;
import java.security.IdentityScope;
import java.util.HashMap;

import serveur.Serveur;
import common.Utilisateur;

public class GestionConnexions 
{
	//Regroupe les socket en cours
	private HashMap<Integer,PrintStream> streamClients;
	//Regroupe les socket identifiés (utilisateurs connectés)
	private HashMap<Integer,Utilisateur> usersConnectes;
	
	public GestionConnexions() 
	{
		// TODO Auto-generated constructor stub
		this.streamClients=new HashMap<Integer, PrintStream>();
		this.usersConnectes= new HashMap<Integer, Utilisateur>();
	}

	public void nouveauClient(int id, PrintStream sortie) 
	{
		this.streamClients.put(id,sortie);	
	}
	
	public void identification(int idSocket, Utilisateur u)
	{
		this.usersConnectes.put(idSocket, u);
	}
	
	public void deconnexion(int idS)
	{
		this.usersConnectes.remove(idS);
	}
	
	public void fermetureSocket (int idS)
	{
		this.streamClients.remove(idS);
	}
	
	public boolean estConnecte(Utilisateur pU)
	{
		for(Utilisateur u: this.usersConnectes.values())
		{
			if(u.getMail().equals(pU.getMail()))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean streamUtilisateurExiste(int idS)
	{
		return this.usersConnectes.containsKey(idS);
	}
	
	public boolean streamExiste(int idS)
	{
		return this.streamClients.containsKey(idS);
	}
	
	public int getIdProprietaireSocket(int idS)
	{
		if (this.usersConnectes.containsValue(idS))
		{
			return this.usersConnectes.get(idS).getId();
		}
		return 0;
	}
	
	public int getIdSocketUtilisateur(Utilisateur u)
	{
		for(int i: this.usersConnectes.keySet())
		{
			if(this.usersConnectes.get(i).getMail().equals(u.getMail()))
			{
				return i;
			}
		}
		return 0;
	}
	
	public PrintStream getPrintSteam (int idS)
	{
		return this.streamClients.get(idS);
	}
}
