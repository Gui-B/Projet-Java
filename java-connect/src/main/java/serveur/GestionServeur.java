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
		String retour;
		String[] splitMess = message.split("\\|");
		if (splitMess[0].equals(proto.getListUserString())){
			String id = "0"; //contient l'id de l'utilisateur qui demande la liste des utilisateurs
			if ( splitMess.length > 1){
				id = splitMess[1];
			}
			Utilisateur[] users; //ici apelle a la bdd pour récupérer la liste des utilisateurs
			String mess = "";
			/*for (Utilisateur user : users) {
				mess = user.getId()
			}*/
			retour ="LIST_USERS";
		}else if (splitMess[0].equals(proto.getDetailUserString())){
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
