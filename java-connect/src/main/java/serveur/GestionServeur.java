package serveur;

import common.Protocole;

public class GestionServeur
{	
	private Protocole proto;
	
	public GestionServeur()
	{
		// TODO Auto-generated constructor stub
		proto = new Protocole();
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
		switch (splitMess[0]) {
		case proto.getListUserString():
			break;
		case proto.getDetailUserString():
			break;
		case proto.getCreerCompteString():
			break;
		case proto.getModifInfoString():
			break;
		case proto.getAjoutDiplomeString():
			break;
		case proto.getSuppDiplomeString():
			break;
		case proto.getAddCompString():
			break;
		case proto.getDelCompString():
			break;
		case proto.getConnectionString():
			break;
		default:
			//erreur message non valide
			retour = "erreur message non reconnu"
			break;
		}
		return "lol";
	}
}
