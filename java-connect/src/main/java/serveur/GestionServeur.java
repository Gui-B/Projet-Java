package serveur;

import common.Competence;
import common.Diplome;
import common.Protocole;
import common.Utilisateur;

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
			Utilisateur u1 = new Utilisateur(1, "blbl", "blblp", "blbl@", "mdp");
			Utilisateur u2 = new Utilisateur(2, "2blbl", "2blblp", "2blbl@", "2mdp");
			Utilisateur[] users = { u1, u2}; //ici apelle a la bdd pour récupérer la liste des utilisateurs
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
				/// DONNEES DE DEBUG
				Utilisateur u1 = new Utilisateur(1, "blbl", "blblp", "blbl@", "mdp"); //Utilisateur dont on récupérer les détails
				Diplome d1 = new Diplome("1", "diplome1", "2015");
				Diplome d2 = new Diplome("2", "diplome2", "2016");
				Competence c1 = new Competence("1", "comp1");
				Competence c2 = new Competence("2", "comp2");
				//FIN DES DONNEES DE DEBUG
				Utilisateur user = u1; //Utilisateur dont on récupérer les détails
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
		}else if (splitMess[0].equals(proto.getAjoutDiplomeString())){
		}else if (splitMess[0].equals(proto.getSuppDiplomeString())){
		}else if (splitMess[0].equals(proto.getAddCompString())){
		}else if (splitMess[0].equals(proto.getDelCompString())){
		}else if (splitMess[0].equals(proto.getConnectionString())){
		} else {
			retour = "erreur message non reconnu";
		}
		return retour;
	}
}
