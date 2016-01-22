package common;

public class Protocole {

	private final String listUser = "LIST_USERS";
	private final String detailUser = "DETAIL_USER";
	private final String creerCompte = "CREER_COMPTE";
	private final String modifInfo = "MODIF_INFO";
	private final String ajoutDiplome = "AJOUT_DIPLOME";
	private final String suppDiplome = "SUPP_DIPLOME";
	private final String addComp = "AJOUT_COMP";
	private final String delComp = "DEL_COMP";
	private final String connection = "CONNECTION";
	
	public Protocole() {
	}
	
	public String reqListUsers(){
		return this.reqListUsers(0);
	}
	
	public String reqListUsers(int id){
		return this.listUser + "|" + id;
	}
	
	public String reqDetail(int idd, int idc){
		
	}
}
