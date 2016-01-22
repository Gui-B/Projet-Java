package common;

/**
 * Classe permettant de générer toute les requette de communication entre le client et le serveur.
 * @author kriss
 * @version 1.0
 */
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
	
	/** génére une requette pour la liste des utilisateur pour un utilisateur non connecté
	 * @return requette liste des utilisateurs
	 */
	public String reqListUsers(){
		return this.reqListUsers(0);
	}
	
	/** génére une requette pour la liste des utilisateur pour un utilisateur connecté
	 * @param id id de l'utilisateur connecté
	 * @return requette liste des utilisateurs
	 */
	public String reqListUsers(int id){
		return this.listUser + "|" + id;
	}
	
	/** fonction générant la requette pour récupérer les détail d'un utilisateur
	 * @param idd id de l'utilisateur connecté
	 * @param idc id de l'utilisateur cible
	 * @return la requete
	 */
	public String reqDetail(int idd, int idc){
		return detailUser + "|" + idd + "|" + idc
	}
	
	/**
	 * fonction générant la requette pour récupérer les détail d'un utilisateur pour un utilisateur non connecté
	 * @param idc id de l'utilisateur cible
	 * @return la requette
	 */
	public String reqDetail(int idc){
		return this.reqDetail(0, idc);
	}
	
	/**
	 * fonction de génération de la requette pour créer un compte
	 * @param nom nom de l'utilisateur
	 * @param prenom prénom de l'utilisateur
	 * @param mdp mot de passe de l'utilisateur
	 * @return la requette
	 */
	public String reqCreerComp(String nom, String prenom, String mdp){
		return addComp + "|0|" + nom + "|" + prenom + "|" + mdp; 
	}
}
