package serveur;

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
				Utilisateur user; //Utilisateur dont on récupérer les détails
				String[][] dip; // tableau de string contenant les diplome de l'utilisateur
				String[] comp; //tableau de compétence de l'utilisateur
				mess = user.getId() + ";" + user.getNom() + ";" + user.getPrenom() + ";" + user.getMail() + "|";
				for (int i = 0; i < dip.length; i++) {
					if (i > 0 ) {
						mess = mess  + ";";
					}
					mess = mess + dip[i][0] + "/" + dip[i][1];
				}
				mess = mess  + "|";
				for (int j = 0; j < comp.length; j++) {
					if (j > 0 ) {
						mess = mess  + ";";
					}
					mess = mess + comp[j];
				}
				retour = proto.reponse(mess);
			}else{
				String mess ="Erreur nombre de parametre invalide";
				retour = proto.erreur("400", mess)
			}
		}else if (splitMess[0].equals(proto.getCreerCompteString())){
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
