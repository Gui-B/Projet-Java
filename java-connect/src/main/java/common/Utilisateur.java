package common;

public class Utilisateur {
	private String nom;
	private String prenom;
	private String motDePasse;
	private String mail;
	private int id;
	
	public Utilisateur(int id, String nom, String prenom, String mail, String motDePasse) {
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.mail = mail;
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
}
