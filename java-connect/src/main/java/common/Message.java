package common;

import java.util.Date;

public class Message {
	private Utilisateur envoyeur;
	private Utilisateur destinataire;
	private String message;
	private Date date;
	
	public Message(Utilisateur env, Utilisateur dest, String m, long d ) {
		// TODO Auto-generated constructor stub
		try {
			this.envoyeur = env;
			this.destinataire = dest;
			this.message = m;
			this.date = new Date (d);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	public Message(Utilisateur env, Utilisateur dest, String m) {
		// TODO Auto-generated constructor stub
		try {
			this.envoyeur = env;
			this.destinataire = dest;
			this.message = m;
			this.date = new Date() ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public Date getDate() {
		return date;
	}
	
	public Utilisateur getDestinataire() {
		return destinataire;
	}
	
	public Utilisateur getEnvoyeur() {
		return envoyeur;
	}
	
	public String getMessage() {
		return message;
	}
	
	
	
}
