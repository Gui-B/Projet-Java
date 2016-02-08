package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import common.Competence;
import common.Diplome;
import common.Protocole;
import common.Utilisateur;
import sql.DBCompetence;
import sql.DBDiplome;
import sql.DBUtilisateur;

public class GestionClient 
{
	private Protocole proto;
	private Client c;
	private Utilisateur u;
	
	public GestionClient() 
	{
		this.proto= new Protocole();
		final String listUserString = proto.getListUserString();
		this.u= null;
		this.c= new Client();
	}
	
	public String traiter (String message)
	{
		String retour ="";
		String[] splitMess = message.split("\\|");
		if (splitMess[0].equals(proto.getListUserString())){
			retour = listUsers(splitMess);
		}else if (splitMess[0].equals(proto.getDetailUserString())){
			retour = detailUser(splitMess);
		}else if (splitMess[0].equals(proto.getCreerCompteString())){
			retour = creerCompte(splitMess);
		}else if (splitMess[0].equals(proto.getModifInfoString())){
			retour = modifInfo(splitMess);
		}else if (splitMess[0].equals(proto.getAjoutDiplomeString())){
			retour = addDip(splitMess);
		}else if (splitMess[0].equals(proto.getSuppDiplomeString())){
			retour = delDip(splitMess);
		}else if (splitMess[0].equals(proto.getAddCompString())){
			retour = addCompt(splitMess);
		}else if (splitMess[0].equals(proto.getDelCompString())){
			retour = delComp(splitMess);
		}else if (splitMess[0].equals(proto.getConnectionString())){
			retour = connexion(splitMess);
		} else if (splitMess[0].equals(proto.getListCompString())){
			retour = listComp(splitMess);
		} else { 
			retour = "erreur message non reconnu";
		}
		
		
		return retour;
	}
	
	private boolean connecte ()
	{
		return this.u!=null;
	}
	
	private String listUsers(String[] splitMess){
		String mess="lol", id;
		Scanner sc= new Scanner(System.in);		
		
		
		String commande="LIST_USERS|0|";
		
		try 
		{
			String[] retour=this.c.communiquer(commande).split("\\|");
			int c=0;
			for(String s: retour)
			{
				String[] s1= s.split (";");
				for(String s2: s1)
				{
					System.out.print(s2+" ");
				}
				System.out.println("");
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "lol";
	}
	
	private String detailUser(String[] splitMess){
		String mess="lol", id;
		Scanner sc= new Scanner(System.in);		
		
		
		String commande="DETAIL_USER|0|";
		
		try 
		{
			
			System.out.print("Id user:");
			id= sc.nextLine();
			
			String[] retour=this.c.communiquer(commande+id).split("\\|");
			int c=0;
			for(String s: retour)
			{
				String[] s1= s.split (";");
				for(String s2: s1)
				{
					System.out.print(s2+" ");
				}
				System.out.println("");
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "lol";
	}
	
	private String connexion (String[] splitMess){
		String pseudo, mdp, retour="lol";
		Scanner sc= new Scanner(System.in);
		
		try 
		{

				System.out.print("pseudo:");
				pseudo= sc.nextLine();
				
				System.out.print("mdp:");
				mdp= sc.nextLine();
				
				System.out.println(pseudo+" "+mdp);
		} 
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}

		return retour;
	}
	
	private String listComp(String[] splitMess){
		String mess="lol", id;
		Scanner sc= new Scanner(System.in);		
		
		
		String commande="LIST_COMP|0";
		
		try 
		{
			String[] retour=this.c.communiquer(commande).split("\\|");
			int c=0;
			for(String s: retour)
			{
				String[] s1= s.split (";");
				for(String s2: s1)
				{
					System.out.print(s2+" ");
				}
				System.out.println("");
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "lol";
	}
	
	private String creerCompte(String[] splitMess){
		String mess="lol", nom, prenom, mdp, mail;
		Scanner sc= new Scanner(System.in);		
		
		
		String commande="CREER_COMPTE|0|";
		
		try 
		{
			
			System.out.print("Nom:");
			nom= sc.nextLine();
			
			System.out.print("Prenom:");
			prenom= sc.nextLine();
			
			System.out.print("Mail");
			mail= sc.nextLine();
			
			System.out.print("Mot de passe:");
			mdp= sc.nextLine();
			
			
			String[] retour=this.c.communiquer(commande+nom+"|"+prenom+"|"+mdp+"|"+mail).split("\\|");
			
			for(String s: retour)
			{
				System.out.println(s);
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "lol";
	}
	
	private String modifInfo(String[] splitMess){
		String retour ="";
		return retour;
	}
	
	private String addDip(String[] splitMess){
		String retour="lol";

		return retour;
	}
	
	private String delDip(String[] splitMess){
		String retour;
	
		return retour="lol";
	}
	
	private String addCompt(String[] splitMess){
		String retour="lol";
	
		return retour;
	}
	
	private String delComp(String[] splitMess){
		String mess, retour="lol";
	
		return retour;
	}
}
