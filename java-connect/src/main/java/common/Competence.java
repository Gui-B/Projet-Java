package common;

public class Competence {
	private int id;
	private String competence;
	public Competence(int id, String competence) {
		super();
		this.id = id;
		this.competence = competence;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the competence
	 */
	public String getCompetence() {
		return competence;
	}
	
}
