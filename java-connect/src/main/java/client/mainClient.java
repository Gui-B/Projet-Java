package client;

import java.util.Scanner;

import common.Protocole;

public class mainClient {

	public static void main(String[] args) 
	{
		GestionClient gc= new GestionClient();
		Scanner sc= new Scanner(System.in);
		Protocole proto = new Protocole();
		String commande;
		String retour;
		
		try 
		{
			do
			{
				System.out.println("faites ? pour de l'aide");
				System.out.print("Commande: ");
				commande= sc.nextLine();
				
				if (commande.equals("q")) break;
				
				if (commande.equals("?")){
					System.out.println(proto.getListUserString() +" récuperer la liste des utilisateurs");
					System.out.println(proto.getDetailUserString() + " récupérer les détails d'un utilisateur");
					System.out.println(proto.getCreerCompteString() + " créer un compte");
					System.out.println(proto.getModifInfoString() + " modifier vos informations");
					System.out.println(proto.getAjoutDiplomeString() + " Ajouter un diplome");
					System.out.println(proto.getSuppDiplomeString() + " suprimer un diplome");
					System.out.println(proto.getAddCompString() + " Ajouter une compétence");
					System.out.println(proto.getDelCompString() + " Suprimer une compétence");
					System.out.println(proto.getConnectionString() + " Se connecter");
					System.out.println(proto.getListCompString() + " lister toutes les compétences dispo");
					System.out.println(proto.getListDipString() + " lister l'ensemble des diplomes dispo");
					System.out.println(proto.getEcrireMail() + " Envoyer un mail à un utilisateur");
					System.out.println(proto.getReleverMessages() + " Relever les messages non lus");
					System.out.println(proto.getLireMessage() + " Lire un message en detail");
					System.out.println(proto.getListUserCo() + " Lister les utilisateurs qui sont connectés");
					System.out.println(proto.getPasserEnEcoute()+ " Passer en mode ecoute");
					System.out.println(proto.getParler() + " Parler avec un utilisateur connecté");
					
					
				}else {
					//met la commande en majuscule
//					gc.traiter(commande.toUpperCase());
					System.out.println(gc.traiter(commande.toUpperCase()));
				}
			}while(true);
			
		} 
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}

	}

}
