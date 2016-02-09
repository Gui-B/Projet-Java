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
	 * @param vuTel
	 * @param vuMail
	 * @param vuComp
	 * @param vuDip
	 */
	public Utilisateur(int id, String nom, String prenom, String motDePasse, String mail, int satuts,
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
	 * @param vuTel
	 * @param vuMail
	 * @param vuComp
	 * @param vuDip
	 */
	public Utilisateur(int id, String nom, String prenom, String motDePasse, String mail, int satuts) {
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
	 * @return the vuTel
	 */
	public int getVuTel() {
		return vuTel;
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
		return this.nom+" "+this.prenom+" "+this.mail+" "+this.satuts;
	}
}
