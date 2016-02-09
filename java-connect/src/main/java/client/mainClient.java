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
					System.out.println(proto.getListUserString() +" récuperer la liste des utilisateur");
					System.out.println(proto.getDetailUserString() + " récupérer les détail d'un utilisateur");
					System.out.println(proto.getCreerCompteString() + " créer un compte");
					System.out.println(proto.getModifInfoString() + " modifier vos information");
					System.out.println(proto.getAjoutDiplomeString() + " Ajouter un diplome");
					System.out.println(proto.getSuppDiplomeString() + " suprimer un diplome");
					System.out.println(proto.getAddCompString() + " Ajouter une compétence");
					System.out.println(proto.getDelCompString() + " Suprimer une compétence");
					System.out.println(proto.getConnectionString() + " Ce connecter");
					System.out.println(proto.getListCompString() + " lister toute les compétence disponible");
					System.out.println(proto.getListDipString() + " lister tout les diplome disponible");
					
				}else {
					gc.traiter(commande.toUpperCase());
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
