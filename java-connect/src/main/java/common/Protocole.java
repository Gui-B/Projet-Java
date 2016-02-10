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
	private final String listComp = "LIST_COMP";
	private final String listDip = "LIST_DIP";
	
	/**
	 * @return the listDip
	 */
	public String getListDipString() {
		return listDip;
	}

	/**
	 * @return the listComp
	 */
	public String getListCompString() {
		return listComp;
	}

	public Protocole() {
	}
	
	/**
	 * @return the listUser string req
	 */
	public String getListUserString() {
		return listUser;
	}

	/**
	 * @return the detailUser string req
	 */
	public String getDetailUserString() {
		return detailUser;
	}

	/**
	 * @return the creerCompte string req
	 */
	public String getCreerCompteString() {
		return creerCompte;
	}

	/**
	 * @return the modifInfo string req
	 */
	public String getModifInfoString() {
		return modifInfo;
	}

	/**
	 * @return the ajoutDiplome string req
	 */
	public String getAjoutDiplomeString() {
		return ajoutDiplome;
	}

	/**
	 * @return the suppDiplome string req
	 */
	public String getSuppDiplomeString() {
		return suppDiplome;
	}

	/**
	 * @return the addComp string req
	 */
	public String getAddCompString() {
		return addComp;
	}

	/**
	 * @return the delComp string req
	 */
	public String getDelCompString() {
		return delComp;
	}

	/**
	 * @return the connection string req
	 */
	public String getConnectionString() {
		return connection;
	}

	/** 
	 * génére une requette pour la liste des utilisateur pour un utilisateur non connecté
	 * @return requette liste des utilisateurs
	 */
	public String reqListUsers(){
		return this.reqListUsers("0");
	}
	
	/** 
	 * génére une requette pour la liste des utilisateur pour un utilisateur connecté
	 * @param id id de l'utilisateur connecté
	 * @return requette liste des utilisateurs
	 */
	public String reqListUsers(String id){
		return this.listUser + "|" + id;
	}
	
	/** 
	 * fonction générant la requette pour récupérer les détail d'un utilisateur
	 * @param idd id de l'utilisateur connecté
	 * @param idc id de l'utilisateur cible
	 * @return la requete
	 */
	public String reqDetail(String idd, String idc){
		return detailUser + "|" + idd + "|" + idc;
	}
	
	/**
	 * fonction générant la requette pour récupérer les détail d'un utilisateur pour un utilisateur non connecté
	 * @param idc id de l'utilisateur cible
	 * @return la requette
	 */
	public String reqDetail(String idc){
		return this.reqDetail("0", idc);
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
	
	/**
	 * fonction générant la requet de modification de parametre
	 * @param id l'id de l'utilisateur
	 * @param para le parametre a modifier
	 * @param val la nouvelle valeur du paramettre
	 * @return la req
	 */
	public String reqModifInfo(String id, String mail, String mdp, String nom, String prenom, String vuMail, String vuComp, String vuDip){
		return modifInfo + "|" + id + "|" + mail + "|" + mdp + "|" + nom + "|" + prenom + "|" + vuMail + "|" + vuComp + "|" + vuDip;
	}
	
	/**
	 * génération de la requet pour ajouter un diplome
	 * @param id id de l'utilisateur
	 * @param idDip id du diplome
	 * @param annee année d'optention du diplome
	 * @return la req
	 */
	public String reqAjoutDiplome(String id, String idDip, String annee){
		return ajoutDiplome + "|" + id + "|" + idDip + "|" + annee;
	}

	/**
	 * génération de la req de suppression de diplome
	 * @param id id de l'utilisateur
	 * @param idDip id du diplome
	 * @return la req
	 */
	public String reqSuppDiplome(String id, String idDip){
		return suppDiplome + "|" + id + "|" + idDip;
	}
	
	/**
	 * génération de la req d'ajout de compétence
	 * @param id id de l'utilisateur
	 * @param idComp id de la compétence
	 * @return la req
	 */
	public String reqAddComp(String id, String idComp){
		return addComp + "|" + id + "|" + idComp;
	}
	
	/**
	 * génération de la requette de supression de compétence
	 * @param id id de l'utilisateur
	 * @param idComp id de la compétence 
	 * @return la req
	 */
	public String reqDelComp(String id, String idComp){
		return delComp + "|" + id + "|" + idComp;
	}
	
	/**
	 * generation de la req de connection
	 * @param id id de l'utilisateur ( toujours a 0 puisque c'est l'utilisateur annonyme qui ce connecte )
	 * @param mail addresse mail de l'utilisateur voulant ce connecter
	 * @param mdp mot de passe de l'utilisateur
	 * @return la req
	 */
	public String reqConnection(String id, String mail, String mdp){
		return connection + "|" + id + "|" + mail + "|" + mdp;
	}
	
	/**
	 * génération d'une requete de réponse du serveur sans erreur
	 * @param message message a envoyer au serveur
	 * @return
	 */
	public String reponse(String message){
		return "200|" + message;
	}
	
	/**
	 * fonction générant une requet reréponse d'erreur
	 * @param num le numéro de l'erreur
	 * @param message le message qui accompagne l'erreur
	 * @return
	 */
	public String erreur(String num, String message){
		 return num + "|" + message;
	}
	
	/** 
	 * fonction générant la requet pour récevoir la liste des compétence
	 * @param id id de l'utilisateur
	 * @return
	 */
	public String ReqListComp(String id){
		return listComp + "|" + id;
	}
	
	/** 
	 * fonction générant la requet pour récevoir la liste des diplomes
	 * @param id id de l'utilisateur
	 * @return
	 */
	public String ReqListDip(String id){
		return listDip + "|" + id;
	}
	
	/**
	 * fonction générant la requet pour récevoir la liste des compétence
	 * @return
	 */
	public String ReqListComp(){
		return ReqListComp("0");
	}

	/**
	 * fonction générant la requet pour récevoir la liste des diplomes
	 * @return
	 */
	public String ReqListDip(){
		return ReqListDip("0");
	}
}
