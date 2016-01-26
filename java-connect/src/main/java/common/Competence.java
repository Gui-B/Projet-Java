package common;

public class Competence {
	private String id;
	private String competence;
	public Competence(String id, String competence) {
		super();
		this.id = id;
		this.competence = competence;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the competence
	 */
	public String getCompetence() {
		return competence;
	}
	
}
