package serveur;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import common.Competence;
import common.Diplome;
import common.Protocole;
import common.Utilisateur;
import common.GestionConnexions;
import common.Message;
import sql.DBCompetence;
import sql.DBDiplome;
import sql.DBMessages;
import sql.DBRecommander;
import sql.DBUtilisateur;

public class GestionServeur
{	
	private Protocole proto;
	private GestionConnexions gc;
	
	public GestionServeur()
	{
		// TODO Auto-generated constructor stub
		this.proto = new Protocole();
		this.gc= new GestionConnexions();
	}
	
	public GestionConnexions getGestionConnexions()
	{
		return this.gc;
	}
	
	/**
	 * fonction de traitement du message reçu par le serveur
	 * @param message le message reçu par le serveur
	 * @return la réponse que le serveur envoie au client
	 */
	public String traiter (int idS,PrintStream pC, String message)
	{
		if(!this.gc.streamExiste(idS))
		{
			this.gc.nouveauClient(idS, pC);
		}
			
		String retour ="";
		String[] splitMess = message.split("\\|");
		if (splitMess[0].equals(proto.getListUserString())){
			retour = listUsers(splitMess);
		}else if (splitMess[0].equals(proto.getDetailUserString())){
			retour = detailUser(splitMess, idS);
		}else if (splitMess[0].equals(proto.getCreerCompteString())){
			retour = creerCompte(splitMess);
		}else if (splitMess[0].equals(proto.getModifInfoString())){
			retour = modifInfo(splitMess, idS);
		}else if (splitMess[0].equals(proto.getAjoutDiplomeString())){
			retour = addDip(splitMess, idS);
		}else if (splitMess[0].equals(proto.getSuppDiplomeString())){
			retour = delDip(splitMess, idS);
		}else if (splitMess[0].equals(proto.getAddCompString())){
			retour = addCompt(splitMess, idS);
		}else if (splitMess[0].equals(proto.getDelCompString())){
			retour = delComp(splitMess, idS);
		}else if (splitMess[0].equals(proto.getConnectionString())){
			retour = connexion(splitMess, idS);
		}else if (splitMess[0].equals(proto.getListCompString())){
			retour = listComp(splitMess);
		}else if (splitMess[0].equals(proto.getListDipString())){
			retour = listDip(splitMess);
		}else if (splitMess[0].equals(proto.getEcrireMail())){
			retour = ecrireMail(splitMess, idS);
		}else if (splitMess[0].equals(proto.getReleverMessages())){
			retour = releverMessages(splitMess, idS);
		}else if (splitMess[0].equals(proto.getLireMessage())){
			retour = lireMessage(splitMess, idS);
		}else if (splitMess[0].equals(proto.getListUserCo())){
			retour = listerEcoutesMessagerie(splitMess, idS);
		}else if (splitMess[0].equals(proto.getPasserEnEcoute())){
			retour = passerEnEcoute(splitMess, idS);
		}else{ 
			retour = "erreur message non reconnu";
		}
		return retour;
	}
	
	private String listUsers(String[] splitMess){
		String retour;
		String id = "0"; //contient l'id de l'utilisateur qui demande la liste des utilisateurs
		if ( splitMess.length > 1){
			id = splitMess[1];
		}
		ArrayList<Utilisateur> users = DBUtilisateur.lireUtilisateurs(); //ici apelle a la bdd pour récupérer la liste des utilisateurs
		//ajout de la gestion d'erreur.
		String mess = "";
		for (Utilisateur user : users) {
			mess = mess + user.getId() + ";" + user.getNom() + ";" + user.getPrenom() + ";" + user.getMail() + "|";
		}
		retour = proto.reponse(mess);
		return retour;
	}
	
	private String detailUser(String[] splitMess, int idS){
		String retour;
		String id = "0"; //contient l'id de l'utilisateur qui demande la liste des utilisateurs
		String idc; // id de l'utilisateur dont on récupére les détails
		String mess = "";
		if ( splitMess.length > 2)
		{
			id = splitMess[1];
			idc = splitMess[2];

			Utilisateur user = DBUtilisateur.lireUtilisateur(Integer.parseInt(idc)); //Utilisateur dont on récupérer les détails
			Utilisateur demandeur = DBUtilisateur.lireUtilisateur(gc.getIdProprietaireSocket(idS));
			ArrayList<Diplome> dips = DBDiplome.lireDiplomesUtilisateur(user); // tableau de string contenant les diplome de l'utilisateur
			ArrayList<Competence> comps = DBCompetence.lireCompetencesUtilisateur(user); //tableau de compétence de l'utilisateur
			mess = user.getId() + ";" + user.getNom() + ";" + user.getPrenom() + ";";
			
			System.out.println(demandeur.getSatuts()+" "+user.getVuMail());
			if (demandeur.getSatuts()<user.getVuMail() && demandeur.getId() != user.getId())
			{
				mess = mess + "Caché" + "|";
			} 
			else 
			{
				mess = mess + user.getMail() + "|";
			}
			
			if (demandeur.getSatuts()<user.getVuDip() && demandeur.getId() != user.getId())
			{
				mess = mess + "0;Caché";
			} 
			else 
			{
				for (Diplome dip : dips) {
					mess = mess + dip.getId() + ";" + dip.getDiplome() + ";" + dip.getAnnee() + ";" + "/";
				}
			}
			mess = mess  + "|";
			
			if (demandeur.getSatuts()<user.getVuComp() && demandeur.getId() != user.getId())
			{
				mess = mess + "0;Caché";
			} 
			else 
			{
				for (Competence comp : comps) {
					int i = 0;
					for (Utilisateur ut : DBRecommander.getRecommandeurs(user, comp)) {
						i++;
					}
					comp.setLike(i);
					mess = mess + comp.getId() + ";" + comp.getCompetence() + ";" + comp.getLike() + " recomendation(s)/";
				}
			}
			System.out.println(mess);
			retour = proto.reponse(mess);
		}
		else
		{
			mess ="Erreur nombre de parametre invalide";
			retour = proto.erreur("400", mess);
		}
		return retour;
	}
	
	private String creerCompte(String[] splitMess){
		String retour;
		String mess;
		if ( splitMess.length > 4){
			String nom = splitMess[2];
			String prenom = splitMess[3];
			String mdp = splitMess[4];
			String email = splitMess[5];
			String vuMail = splitMess[6];
			String vuComp = splitMess[7];
			String vuDip= splitMess[8];
			
			if (DBUtilisateur.insererUtilisateur(new Utilisateur(0, nom, prenom, email, mdp, 0, Integer.parseInt(vuMail), Integer.parseInt(vuComp), Integer.parseInt(vuDip)))){ //replacer la condition par une vérification de la bonne execution de l'ajout user
				mess = "OK";
				retour = proto.reponse(mess);
			}else{
				mess = "erreur de création de compte, déjà existant ?";
				retour = proto.erreur("400", mess);
			}
		}else{
			mess ="Erreur nombre de parametre invalide";
			retour = proto.erreur("400", mess);
		}
		return retour;
	}
	
	private String modifInfo(String[] splitMess, int idS){
		String retour ="";
		String id = splitMess[1];
		try {
			gc.adminOuProprietaireException(idS, Integer.parseInt(id));
			Utilisateur user = DBUtilisateur.lireUtilisateur(Integer.parseInt(id));
			user.setMotDePasse(splitMess[3]);
			user.setNom(splitMess[4]);
			user.setPrenom(splitMess[5]);
			user.setVuMail(Integer.parseInt(splitMess[6]));
			user.setVuComp(Integer.parseInt(splitMess[7]));
			user.setVuDip(Integer.parseInt(splitMess[8]));
			DBUtilisateur.modifierUtilisateur(user);
			retour = proto.reponse("OK");
		}
		catch (Exception e){
			retour = e.getMessage();
		}
		return retour;
	}
	
	private String addDip(String[] splitMess, int idS)
	{
		String retour;
		String mess;
		try 
		{
			
			if ( splitMess.length > 3){
				String id = splitMess[1];
				String idd = splitMess[2];
				String annee = splitMess[3];
				
				//Verifier les droits sur la commande: user ou admin
				gc.adminOuProprietaireException(idS, Integer.parseInt(id));
				
				//requet d'ajout du diplome
				if (DBDiplome.ajoutDiplomeUtilisateur(new Diplome(Integer.parseInt(idd), ""), new Utilisateur(Integer.parseInt(id), "", "", "", "", 0), Integer.parseInt(annee)))
				{ //retour de la bdd
					mess = "ok";
					retour = proto.reponse(mess);
				}
				else
				{
					mess = "erreur pendant l'ajout du diplome";
					retour = proto.erreur("400", mess);
					throw new Exception(mess);
				}
			}
			else
			{
				mess ="Erreur nombre de parametre invalide";
				retour = proto.erreur("400", mess);
				throw new Exception(mess);
			}
		} 
		catch (Exception e) 
		{
			retour= e.getMessage();
		}
		return retour;
	}
	
	private String delDip(String[] splitMess, int idS)
	{
		String retour;
		String mess;
		
		try 
		{
			if ( splitMess.length > 2){
				String id = splitMess[1];
				String idd = splitMess[2];
				
				//Verifier les droits sur la commande: user ou admin
				gc.adminOuProprietaireException(idS, Integer.parseInt(id));
				
				if (DBDiplome.supprimerDiplomeUtilisateur(new Diplome(Integer.parseInt(idd), ""), new Utilisateur(Integer.parseInt(id), "", "", "", "", 0))){ //retour de la bdd
					mess = "ok";
					retour = proto.reponse(mess);
				}else{
					mess = "erreur pendant la supression du diplome";
					retour = proto.erreur("400", mess);
				}
			}
			else
			{
				mess ="Erreur nombre de parametre invalide";
				retour = proto.erreur("400", mess);
				
				throw new Exception(retour);
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			retour=e.getMessage();
		}
		
		return retour;
	}
	
	private String addCompt(String[] splitMess, int idS)
	{
		String retour;
		String mess;
		
		try 
		{
			if ( splitMess.length > 2)
			{
				String id = splitMess[1];
				String idc = splitMess[2];
				
				//Verifier les droits sur la commande: user ou admin
				gc.adminOuProprietaireException(idS, Integer.parseInt(id));
				
				if (DBCompetence.ajoutCompetenceUtilisateur(new Competence(Integer.parseInt(idc), ""), new Utilisateur(Integer.parseInt(id), "", "", "", "", 0))){ //retour de la bdd
					mess = "ok";
					retour = proto.reponse(mess);
				}
				else
				{
					mess = "erreur pendant l'ajout de compétence";
					retour = proto.erreur("400", mess);
					throw new Exception(retour);
				}
			}
			else
			{
				mess ="Erreur nombre de parametre invalide";
				retour = proto.erreur("400", mess);
				throw new Exception(retour);
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			retour= e.getMessage();
		}
		
		return retour;
	}
	
	private String listComp(String[] splitMess){
		String mess = "";
		ArrayList<Competence> comps = DBCompetence.lireCompetences(); //tableau de compétence de l'utilisateur
		for (Competence comp : comps) {
			mess = mess + comp.getId() + ";" + comp.getCompetence() + "|";
		}
		return proto.reponse(mess);
	}
	
	private String listDip(String[] splitMess){
		String mess = "";
		ArrayList<Diplome> dips = DBDiplome.lireDiplomes(); //tableau de compétence de l'utilisateur
		for (Diplome dip : dips) {
			mess = mess + dip.getId() + ";" + dip.getDiplome() + "|";
		}
		return proto.reponse(mess);
	}
	
	private String connexion (String[] splitMess, int idS)
	{
		String mess, retour;
		if ( splitMess.length > 2)
		{
			String pseudo = splitMess[1];
			String mdp = splitMess[2];
			//requet de connection retournant l'id de l'utilisateur
			Utilisateur user = DBUtilisateur.checkConnexion(new Utilisateur(0, "", "", pseudo, mdp, 0)); 
			if (!gc.streamUtilisateurExiste(idS))
			{
				if(!gc.estConnecte(user))
				{
					if( user != null)
					{ 
						//TO DO
						mess = Integer.toString(user.getId());
						retour = proto.reponse(mess);
						
						//Initialiser la co dans Gestion connexion
						gc.identification(idS, user);
						
						//Dire bonjour
						System.out.println("CONNECTE "+user);

					} 
					else 
					{
						mess = "Erreur de connexion";
						retour = proto.erreur("400", mess);
					}
				}
				else
				{
					mess = "Vous etes deja connecte ailleurs";
					retour = proto.erreur("400", mess);
				}
			}
			else
			{
				mess = "Vous etes deja connecte sous:"+user.getMail();
				retour = proto.erreur("400", mess);
			}
		} 
		else 
		{
			mess ="Erreur nombre de parametre invalide";
			retour = proto.erreur("400", mess);
		}
		return retour;
	}
	
	/**
	 * Supprimer une competence uniquement admin ou proprietaire
	 * @param splitMess
	 * @return
	 */
	private String delComp(String[] splitMess,int idS)
	{
		String mess, retour;
		try
		{
			if ( splitMess.length > 2){
				String id = splitMess[1];
				String idc = splitMess[2];
				
				//Verif des droits
				gc.adminOuProprietaireException(idS, Integer.parseInt(id));
				
				
				if (DBCompetence.supprimerCompetenceUtilisateur(new Competence(Integer.parseInt(idc), ""), new Utilisateur(Integer.parseInt(id), "", "", "", "", 0))){ //retour de la bdd
					mess = "ok";
					retour = proto.reponse(mess);
				}
				else
				{
					mess = "erreur pendant la supression de compétence";
					retour = proto.erreur("400", mess);
					throw new Exception(mess);
				}
			}
			else
			{
				mess ="Erreur nombre de parametre invalide";
				retour = proto.erreur("400", mess);
				throw new Exception(mess);
			}
		}
		catch (Exception e)
		{
			retour=e.getMessage();
		}
		
		return retour;
	}
	
//MESSAGERIE DIFFEREE ----------------------------------------------------------------------------------------
	private String ecrireMail(String[] splitMess,int idS)
	{
		String retour= "lol", mess="default mess";
		try 
		{
			if (splitMess.length==4)
			{	
				//TODO: Gerer le groupware utilisateur
				//Modifier pour eco gestion de co
				
				//Interdire aux anonymes
				if(gc.getIdProprietaireSocket(idS)==0)
				{
					throw new Exception ("SERVEUR: ECRIRE_MAIL: Interdit aux anonymes");
				}
				
				//Retrouver l'id du proprietaire du socket
				Utilisateur env= DBUtilisateur.lireUtilisateur(gc.getIdProprietaireSocket(idS));
				
				
//				Utilisateur env= DBUtilisateur.lireUtilisateur(Integer.parseInt(splitMess[1]));
				
				Utilisateur dest= DBUtilisateur.lireUtilisateur(Integer.parseInt(splitMess[2]));
				Message message= new Message(env, dest, splitMess[3]);
				
				if(DBMessages.insererMessage(message))
				{
					mess="ok";
					retour=proto.reponse(mess);
				}
				else
				{
					retour="ecrireMail: la requete DB s'est mal passee";
					mess= proto.erreur("400", retour);
					throw new Exception (mess);
				}
			}
			else
			{
				throw new Exception ("ecrireMail: le nombre de parametres de la requete est incorrect "+splitMess.length);
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			retour= proto.erreur("400", e.getMessage());
		}
		
		return retour;
	}
	
	public String releverMessages(String[] splitMess,int idS)
	{
		String retour= "lol", mess="default mess";
		try 
		{
			if (splitMess.length==2)
			{	
				//TODO: GERER LE GROUPWARE UTILISATEUR
				//Interdire aux anonymes
				if(gc.getIdProprietaireSocket(idS)==0)
				{
					throw new Exception ("SERVEUR: ECRIRE_MAIL: Interdit aux anonymes");
				}
				
				//Retrouver l'id du proprietaire du socket
				Utilisateur demandeur= DBUtilisateur.lireUtilisateur(gc.getIdProprietaireSocket(idS));
				
				gc.adminOuProprietaireException(idS, demandeur.getId());
//				//Modifier pour eco gestion de co
//				Utilisateur demandeur= DBUtilisateur.lireUtilisateur(Integer.parseInt(splitMess[1]));
				
				ArrayList<Message> messages= DBMessages.lireMessagesNonLus(demandeur);
				mess="";
				
				for (Message message: messages)
				{
					mess+=message.getIdMessage()+";"+message.getEnvoyeur().getId()+";"+message.getDestinataire().getId()+";"+message.getDate()+";"+message.getMessage()+"|";
				}
				
				retour=proto.reponse(mess);
			}
			else
			{
				throw new Exception ("releverMesages: le nombre de parametres de la requete est incorrect "+splitMess.length);
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			retour= proto.erreur("400", e.getMessage());
		}
		
		return retour;
	}
	
	public String lireMessage(String[] splitMess,int idS)
	{
		String retour= "lol", mess="default mess";
		try 
		{
			if (splitMess.length==3)
			{	
				//GERER LE GROUPWARE UTILISATEUR
				//Interdire aux anonymes
				if(gc.getIdProprietaireSocket(idS)==0)
				{
					throw new Exception ("SERVEUR: ECRIRE_MAIL: Interdit aux anonymes");
				}
				//Modifier pour eco gestion de co
				//Retrouver l'id du proprietaire du socket
				Utilisateur demandeur= DBUtilisateur.lireUtilisateur(gc.getIdProprietaireSocket(idS));				

				//Traitement
				Message message= DBMessages.consulterMessage(Integer.parseInt(splitMess[2]));
				
				//Interdire de lire le msg de qqun d'autre
				if (demandeur.getId() != message.getDestinataire().getId())
				{
					throw new Exception("SERVEUR: lireMessage: ce message n'est pas a vous");
				}
				
				mess=message.getIdMessage()+";"+message.getEnvoyeur().getId()+";"+message.getDestinataire().getId()+";"+message.getDate()+";"+message.getLu()+";"+message.getMessage();
				
				//Passer le message en lu
				DBMessages.passerMessageLu(Integer.parseInt(splitMess[2]));
				
				retour=proto.reponse(mess);
			}
			else
			{
				throw new Exception ("releverMessages: le nombre de parametres de la requete est incorrect "+splitMess.length);
			}
		} 
		catch (Exception e) 
		{
			retour= proto.erreur("400",e.getMessage());
		}
		
		return retour;
	}
	
//MESSAGERIE INSTATANNEE --------------------------------------------------------------------------------------
	public String listerEcoutesMessagerie(String[] splitMess,int idS)
	{
		String retour= "lol";
		try 
		{	
			ArrayList<Utilisateur> users = gc.getUtilisateursEnEcoute();
			String mess = "";
			for (Utilisateur user : users) {
				mess = mess + user.getId() + ";" + user.getMail() + ";"+ gc.getIpUtilisateursEnEcoute(user)+";"+gc.getPortUtilisateursEnEcoute(user)+"|";
			}
			retour = proto.reponse(mess);
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		return retour;
	}
	
	private String passerEnEcoute (String[] splitMess,int idS)
	{
		String retour="default passerModeEcoute", ip="127.0.0.1", port_1="port";
		
		try 
		{	
			//0=COMMANDE
			//1=ID DEMANDEUR MAIS USELESS
			//2=PORT USELESS VU QUE CEST LE SERVEUR QUI DONNE
			int port=gc.getPortEcouteMessagerie();
			if(splitMess.length==3)
			{
				//Interdire aux anonymes
				if(gc.getIdProprietaireSocket(idS)==0)
				{
					throw new Exception ("SERVEUR: passerEnEcoute: Interdit aux anonymes");
				}
				
				gc.initialiseEcouteMessagerieInstantannee(gc.getIdProprietaireSocket(idS), "127.0.0.1", Integer.toString(port));
				retour="200|"+ip+";"+port;
			}
			else
			{
				throw new Exception ("passerEnEcoute: le nombre de parametres de la requete est incorrect "+splitMess.length);	
			}
		} 
		catch (Exception e) 
		{
			retour= proto.erreur("400",e.getMessage());
		}
		return retour;
	}
}
