package common;

public class Diplome {
	private String id;
	private String diplome;
	private String annee;
	public Diplome(String id, String diplome, String annee) {
		this.id = id;
		this.diplome = diplome;
		this.annee = annee;
	}
	public Diplome(String id, String diplome) {
		this.id = id;
		this.diplome = diplome;
		this.annee ="0";
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the diplome
	 */
	public String getDiplome() {
		return diplome;
	}
	/**
	 * @return the annee
	 */
	public String getAnnee() {
		return annee;
	}
}
