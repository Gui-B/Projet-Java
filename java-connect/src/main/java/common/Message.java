package common;

import java.util.Date;

public class Message {
	private int idMessage;
	private Utilisateur envoyeur;
	private Utilisateur destinataire;
	private String message;
	private Date date;
	private int lu;
	
	public Message(int id, Utilisateur env, Utilisateur dest, String m, long d, int lu) {
		// TODO Auto-generated constructor stub
		try {
			this.idMessage= id;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Id:"+this.idMessage+" Envoyeur:"+this.envoyeur.getId()+" Dest:"+this.getDestinataire().getId()+" Date:"+this.date.getTime()+" Message:"+this.getMessage();
	}
	
}
