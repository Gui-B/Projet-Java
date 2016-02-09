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
		if (this.usersConnectes.containsKey(idS))
		{
			System.out.println(this.usersConnectes.get(idS));
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
	
	public void fullAdminAException(int idS) throws Exception
	{
		if(!this.usersConnectes.containsKey(idS))
		{
			throw new Exception("CONTROLE ADMIN:cet id n'est pas connecte");
		}
		else
		{
			if(this.usersConnectes.get(idS).getSatuts()!=1)
			{
				throw new Exception("CONTROLE ADMIN: cet utilisateur n'est pas admin");
			}
		}
	}
	
	public void adminOuProprietaireException(int idS, int idU) throws Exception
	{
		if(!this.usersConnectes.containsKey(idS))
		{
			throw new Exception("CONTROLE adminOuProprietaire:cet id n'est pas connecte");
		}
		else
		{
			if((this.usersConnectes.get(idS).getId()!=idU)&&(this.usersConnectes.get(idS).getSatuts()!=1))
			{
				throw new Exception("CONTROLE adminOuProprietaire: "+this.usersConnectes.get(idS).getMail()+" "+this.usersConnectes.get(idS).getSatuts()+" "+"cet utilisateur n'est pas admin ou proprietaire!");
			}
		}
	}
}
