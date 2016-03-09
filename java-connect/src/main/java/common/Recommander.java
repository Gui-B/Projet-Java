package common;

public class Recommander {
	private int id;
	private int idU_Recommandeur;
	private int idU_Recommande;
	private int idC;
	private boolean likeC;
	
	public Recommander (int id, int idU_Recommandeur, int idU_Recommande, int idC, boolean likeC){
		this.id = id;
		this.idU_Recommandeur= idU_Recommandeur;
		this.idU_Recommande = idU_Recommande;
		this.likeC = likeC;
		this.idC = idC;
	}

	public int getId() {
		return id;
	}

	public int getIdU_Recommandeur() {
		return idU_Recommandeur;
	}

	public int getIdU_Recommande() {
		return idU_Recommande;
	}

	public int getIdC() {
		return idC;
	}

	public boolean isLikeC() {
		return likeC;
	}
	
	

}
