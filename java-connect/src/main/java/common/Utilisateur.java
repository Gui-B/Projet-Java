package common;

public class Utilisateur {
	private String nom;
	private String prenom;
	private String motDePasse;
	private String mail;
	private int id;
	private int satuts;
	private int vuMail;
	private int vuComp;
	private int vuDip;
	

	/**
	 * @param nom
	 * @param prenom
	 * @param motDePasse
	 * @param mail
	 * @param id
	 * @param satuts
	 * @param vuMail
	 * @param vuComp
	 * @param vuDip
	 */
	public Utilisateur(int id, String nom, String prenom, String mail, String motDePasse, int satuts,
			int vuMail, int vuComp, int vuDip) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.mail = mail;
		this.id = id;
		this.satuts = satuts;
		this.vuMail = vuMail;
		this.vuComp = vuComp;
		this.vuDip = vuDip;
	}

	/**
	 * @param nom
	 * @param prenom
	 * @param motDePasse
	 * @param mail
	 * @param id
	 * @param satuts
	 * @param vuMail
	 * @param vuComp
	 * @param vuDip
	 */
	public Utilisateur(int id, String nom, String prenom, String mail, String motDePasse,  int satuts) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.mail = mail;
		this.id = id;
		this.satuts = satuts;
		this.vuMail = 0;
		this.vuComp = 0;
		this.vuDip = 0;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param satuts the satuts to set
	 */
	public void setSatuts(int satuts) {
		this.satuts = satuts;
	}

	public int getSatuts() {
		return satuts;
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

	/**
	 * @return the vuMail
	 */
	public int getVuMail() {
		return vuMail;
	}

	/**
	 * @return the vuComp
	 */
	public int getVuComp() {
		return vuComp;
	}

	/**
	 * @return the vuDip
	 */
	public int getVuDip() {
		return vuDip;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Id: "+this.id+" Nom:"+this.nom+" Prenom:"+this.prenom+" Mail:"+this.mail+" Statut:"+this.satuts+" vuComp:"+this.vuComp+" vuDip:"+this.vuDip+" vuMail:"+this.vuMail;
	}
}
