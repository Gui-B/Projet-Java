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

/**
 * @author robin
 *
 */
public class GestionClient 
{
	private Protocole proto;
	private Client c;
	private Utilisateur u;

	public GestionClient() 
	{
		this.proto= new Protocole();
		final String listUserString = proto.getListUserString();
		this.u= new Utilisateur(0, "Anonyme", "", "", "", 0);
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

	/**
	 * Dit si un utilisateur est connecte
	 * @return
	 */
	private boolean connecte ()
	{
		return this.u!=null;
	}

	/**
	 * Traite le listage des users
	 * @param splitMess
	 * @return
	 */
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

	/**
	 * Donne le detail d'un utilisateur
	 * @param splitMess
	 * @return
	 */
	private String detailUser(String[] splitMess){
		String mess="lol", id;
		Scanner sc= new Scanner(System.in);		


		String commande="DETAIL_USER|"+this.u.getId()+"|";

		try 
		{

			System.out.print("Id user:");
			id= sc.nextLine();

			String[] retour=this.c.communiquer(commande+id).split("\\|");
			int c=0;

			String[] s1= retour[1].split (";");
			System.out.println("Id: "+s1[0]+", Nom: "+s1[1].toUpperCase()+", Prenom:"+s1[2]+", Mail:"+s1[3]);

			//Diplomes
			System.out.print("Diplomes: ");
			for(String s2: retour[2].split(";"))
			{
				System.out.print(s2+" ");
			}
			System.out.println("");

			//Competences
			System.out.print("Competences: ");
			for(String s2: retour[3].split(";"))
			{
				System.out.print(s2+" ");
			}

			System.out.println("");

		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "lol";
	}

	/**
	 * Liste les competences de la base
	 * @param splitMess
	 * @return
	 */
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

	/**
	 * Cree un compte utilisateur
	 * @param splitMess
	 * @return
	 */
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

	/**
	 * Effectue une demande de connexion
	 * @param splitMess
	 * @return
	 */
	private String connexion (String[] splitMess){
		String pseudo, mdp;
		Scanner sc= new Scanner(System.in);
		String commande="CONNECTION|";
		try 
		{

			System.out.print("pseudo:");
			pseudo= sc.nextLine();

			System.out.print("mdp:");
			mdp= sc.nextLine();

			String[] retour=this.c.communiquer(commande+pseudo+"|"+mdp).split("\\|");

			if(retour[0].equalsIgnoreCase("200"))
			{
				System.out.println("Connexion OK");
				int id= Integer.parseInt(retour[1]);

				//Dire bonjour
				commande="DETAIL_USER|"+this.u.getId()+"|"+id;

				retour=this.c.communiquer(commande).split("\\|");

				String[] s1= retour[1].split (";");
				System.out.println("Bonjour "+s1[1].toUpperCase()+" "+s1[2].toLowerCase());

				this.u=new Utilisateur(Integer.parseInt(s1[0]), s1[1],s1[2], s1[3], "", 0);

				System.out.println("");

			}
			else
			{
				System.out.println("Connection KO");
			}
		} 
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}

		return "lol";
	}

	/**
	 * Modifie les infos d'un utilisateur
	 * @param splitMess
	 * @return
	 */
	private String modifInfo(String[] splitMess){

		String id, nom, prenom, mdp, mail;
		Scanner sc= new Scanner(System.in);		


		String commande="CREER_COMPTE|0|";

		try 
		{
			System.out.print("id:");
			id= sc.nextLine();

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

	/**
	 * Ajoute un diplome a un utilisateur
	 * @param splitMess
	 * @return
	 */
	private String addDip(String[] splitMess){
		String idU, idD, annee;
		Scanner sc= new Scanner(System.in);		


		String commande="AJOUT_DIPLOME|";

		try 
		{
			System.out.print("ID Utilisateur:");
			idU= sc.nextLine();

			System.out.print("ID Diplome:");
			idD= sc.nextLine();

			System.out.print("Annee d'obtention:");
			annee= sc.nextLine();



			String retour=this.c.communiquer(commande+idU+"|"+idD+"|"+annee);

			System.out.println(retour);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "lol";
	}

	/**
	 * Enleve un diplome a un utilisateur
	 * @param splitMess
	 * @return
	 */
	private String delDip(String[] splitMess){
		String idU, idD;
		Scanner sc= new Scanner(System.in);		
		String commande="SUPP_DIPLOME|";

		try 
		{
			System.out.print("ID Utilisateur:");
			idU= sc.nextLine();

			System.out.print("ID Diplome:");
			idD= sc.nextLine();

			String retour=this.c.communiquer(commande+idU+"|"+idD);

			System.out.println(retour);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "lol";
	}

	/**
	 * Ajoute une competence a un utilisateur
	 * @param splitMess
	 * @return
	 */
	private String addCompt(String[] splitMess){
		String idU, idC;
		Scanner sc= new Scanner(System.in);		
		String commande="AJOUT_COMP|";

		try 
		{
			System.out.print("ID Utilisateur:");
			idU= sc.nextLine();

			System.out.print("ID Competence:");
			idC= sc.nextLine();

			String retour=this.c.communiquer(commande+idU+"|"+idC);

			System.out.println(retour);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "lol";
	}

	/**
	 * Enleve une competence a un utilisateur
	 * @param splitMess
	 * @return
	 */
	private String delComp(String[] splitMess){
		String idU, idC;
		Scanner sc= new Scanner(System.in);		
		String commande="DEL_COMP|";

		try 
		{
			System.out.print("ID Utilisateur:");
			idU= sc.nextLine();

			System.out.print("ID Competence:");
			idC= sc.nextLine();

			String retour=this.c.communiquer(commande+idU+"|"+idC);

			System.out.println(retour);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "lol";
	}
}
