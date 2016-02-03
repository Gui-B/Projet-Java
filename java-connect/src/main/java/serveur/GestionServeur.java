package serveur;

import common.Competence;
import common.Diplome;
import common.Protocole;
import common.Utilisateur;
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
				Diplome[] dips = {d1, d2}; // tableau de string contenant les diplome de l'utilisateur
				Competence[] comps = { c1, c2}; //tableau de compétence de l'utilisateur
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
				//EXECUTION DE LA REQUET D'AJOUT UTILISATEUR
				if (true){ //replacer la condition par une vérification de la bonne execution de l'ajout user
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
				if (true){ //retour de la bdd
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
				if (true){ //retour de la bdd
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
				if (true){ //retour de la bdd
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
				if (true){ //retour de la bdd
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
				String id = "0";
				if(true){
					mess = id;
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
				/// DONNEES DE DEBUG
				Competence c1 = new Competence("1", "comp1");
				Competence c2 = new Competence("2", "comp2");
				//FIN DES DONNEES DE DEBUG
				Competence[] comps = { c1, c2}; //tableau de compétence de l'utilisateur
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
