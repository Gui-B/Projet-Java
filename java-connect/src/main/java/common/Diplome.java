package common;

public class Diplome {
	private int id;
	private String diplome;
	private int annee;
	public Diplome(int id, String diplome, int annee) {
		this.id = id;
		this.diplome = diplome;
		this.annee = annee;
	}
	public Diplome(int id, String diplome) {
		this.id = id;
		this.diplome = diplome;
		this.annee =0;
	}
	/**
	 * @return the id
	 */
	public int getId() {
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
	public int getAnnee() {
		return annee;
	}
}
