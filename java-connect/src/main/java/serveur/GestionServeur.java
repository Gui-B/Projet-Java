package serveur;

import common.Competence;
import common.Diplome;
import common.Protocole;
import common.Utilisateur;
import sql.DBCompetence;
import sql.DBDiplome;
import sql.DBUtilisateur;

public class GestionServeur
{	
	private Protocole proto;
	//private static final String listUserString;
	
	public GestionServeur()
	{
		// TODO Auto-generated constructor stub
		proto = new Protocole();
		final String listUserString = proto.getListUserString();
		
	}
	
	/**
	 * fonction de traitement du message reçu par le serveur
	 * @param message le message reçu par le serveur
	 * @return la réponse que le serveur envoie au client
	 */
	public String traiter (String message)
	{
		String retour ="";
		String[] splitMess = message.split("\\|");
		if (splitMess[0].equals(proto.getListUserString())){
			String id = "0"; //contient l'id de l'utilisateur qui demande la liste des utilisateurs
			if ( splitMess.length > 1){
				id = splitMess[1];
			}
			Utilisateur[] users = (Utilisateur[]) DBUtilisateur.lireUtilisateurs().toArray(); //ici apelle a la bdd pour récupérer la liste des utilisateurs
			//ajout de la gestion d'erreur.
			String mess = "";
			for (Utilisateur user : users) {
				mess = mess + user.getId() + ";" + user.getNom() + ";" + user.getPrenom() + ";" + user.getMail() + "|";
			}
			retour = proto.reponse(mess);
		}else if (splitMess[0].equals(proto.getDetailUserString())){
			String id = "0"; //contient l'id de l'utilisateur qui demande la liste des utilisateurs
			String idc; // id de l'utilisateur dont on récupére les détails
			String mess = "";
			if ( splitMess.length > 2){
				id = splitMess[1];
				idc = splitMess[2];

				Utilisateur user = DBUtilisateur.lireUtilisateur(Integer.parseInt(idc)); //Utilisateur dont on récupérer les détails
				Utilisateur demandeur = DBUtilisateur.lireUtilisateur(Integer.parseInt(id));
				Diplome[] dips = (Diplome[]) DBDiplome.lireDiplomesUtilisateur(user).toArray(); // tableau de string contenant les diplome de l'utilisateur
				Competence[] comps = (Competence[]) DBCompetence.lireCompetencesUtilisateur(user).toArray(); //tableau de compétence de l'utilisateur
				mess = user.getId() + ";" + user.getNom() + ";" + user.getPrenom() + ";" + user.getMail() + "|";
				for (Diplome dip : dips) {
					mess = mess + dip.getId() + ";" + dip.getDiplome() + ";" + dip.getAnnee() + ";" + "/";
				}
				mess = mess  + "|";
				for (Competence comp : comps) {
					mess = mess + comp.getId() + ";" + comp.getCompetence() + "/";
				}
				retour = proto.reponse(mess);
			}else{
				mess ="Erreur nombre de parametre invalide";
				retour = proto.erreur("400", mess);
			}
		}else if (splitMess[0].equals(proto.getCreerCompteString())){
			String mess;
			if ( splitMess.length > 4){
				String nom = splitMess[2];
				String prenom = splitMess[3];
				String mdp = splitMess[4];
				String email = splitMess[5];
				if (DBUtilisateur.insererUtilisateur(new Utilisateur(0, nom, prenom, email, mdp, 0))){ //replacer la condition par une vérification de la bonne execution de l'ajout user
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
		}else if (splitMess[0].equals(proto.getModifInfoString())){
			// pour cette partie voir comment on gére les différent paramatre a mettre a jour.
		}else if (splitMess[0].equals(proto.getAjoutDiplomeString())){
			String mess;
			if ( splitMess.length > 3){
				String id = splitMess[1];
				String idd = splitMess[2];
				String annee = splitMess[3];
				//requet d'ajout du diplome
				if (DBDiplome.ajoutDiplomeUtilisateur(new Diplome(Integer.parseInt(idd), ""), new Utilisateur(Integer.parseInt(id), "", "", "", "", 0), Integer.parseInt(annee))){ //retour de la bdd
					mess = "ok";
					retour = proto.reponse(mess);
				}else{
					mess = "erreur pendant l'ajout du diplome";
					retour = proto.erreur("400", mess);
				}
			}else{
				mess ="Erreur nombre de parametre invalide";
				retour = proto.erreur("400", mess);
			}
		}else if (splitMess[0].equals(proto.getSuppDiplomeString())){
			String mess;
			if ( splitMess.length > 2){
				String id = splitMess[1];
				String idd = splitMess[2];
				if (DBDiplome.supprimerDiplomeUtilisateur(new Diplome(Integer.parseInt(idd), ""), new Utilisateur(Integer.parseInt(id), "", "", "", "", 0))){ //retour de la bdd
					mess = "ok";
					retour = proto.reponse(mess);
				}else{
					mess = "erreur pendant la supression du diplome";
					retour = proto.erreur("400", mess);
				}
			}else{
				mess ="Erreur nombre de parametre invalide";
				retour = proto.erreur("400", mess);
			}
		}else if (splitMess[0].equals(proto.getAddCompString())){
			String mess;
			if ( splitMess.length > 2){
				String id = splitMess[1];
				String idc = splitMess[2];
				if (DBCompetence.ajoutCompetenceUtilisateur(new Competence(Integer.parseInt(idc), ""), new Utilisateur(Integer.parseInt(id), "", "", "", "", 0))){ //retour de la bdd
					mess = "ok";
					retour = proto.reponse(mess);
				}else{
					mess = "erreur pendant l'ajout de compétence";
					retour = proto.erreur("400", mess);
				}
			}else{
				mess ="Erreur nombre de parametre invalide";
				retour = proto.erreur("400", mess);
			}
		}else if (splitMess[0].equals(proto.getDelCompString())){
			String mess;
			if ( splitMess.length > 2){
				String id = splitMess[1];
				String idc = splitMess[2];
				if (DBCompetence.supprimerCompetenceUtilisateur(new Competence(Integer.parseInt(idc), ""), new Utilisateur(Integer.parseInt(id), "", "", "", "", 0))){ //retour de la bdd
					mess = "ok";
					retour = proto.reponse(mess);
				}else{
					mess = "erreur pendant la supression de compétence";
					retour = proto.erreur("400", mess);
				}
			}else{
				mess ="Erreur nombre de parametre invalide";
				retour = proto.erreur("400", mess);
			}
		}else if (splitMess[0].equals(proto.getConnectionString())){
			String mess;
			if ( splitMess.length > 3){
				String pseudo = splitMess[1];
				String mdp = splitMess[2];
				//requet de connection retournant l'id de l'utilisateur
				Utilisateur user = DBUtilisateur.checkConnexion(new Utilisateur(0, "", "", pseudo, mdp, 0)); 
				if( user != null){ //TO DO
					mess = Double.toString(user.getId());
					retour = proto.reponse(mess);
				} else {
					mess = "Erreur de connexion";
					retour = proto.erreur("400", mess);
				}
			}else if (splitMess[0].equals(proto.getListComp())){
				String mess ="";
				String id = "0"; //contient l'id de l'utilisateur qui demande la liste des utilisateurs
				if ( splitMess.length > 1){
					id = splitMess[1];
				}
				Competence[] comps = (Competence[]) DBCompetence.lireCompetences().toArray(); //tableau de compétence de l'utilisateur
				for (Competence comp : comps) {
					mess = mess + comp.getId() + ";" + comp.getCompetence() + "|";
				}
				retour = proto.reponse(mess);
			}else{
				mess ="Erreur nombre de parametre invalide";
				retour = proto.erreur("400", mess);
			}
		} else {
			retour = "erreur message non reconnu";
		}
		return retour;
	}
}
