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
		String[] splitMess = message.split("\\|");
		
		return "lol";
	}
}
