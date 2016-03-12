package common;

public class Competence {
	private int id;
	private String competence;
	private int like;
	
	public Competence(int id, String competence) {
		super();
		this.id = id;
		this.competence = competence;
		this.like = 0;
	}
	
	public Competence(int id, String competence, int like) {
		super();
		this.id = id;
		this.competence = competence;
		this.like = like;
	}
	/**
	 * @return the like
	 */
	public int getLike() {
		return like;
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
