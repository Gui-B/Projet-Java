package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.Competence;
import common.Message;
import common.Recommander;
import common.Utilisateur;

public class DBRecommander extends MySql{
	
	/**
	 * Ajoute la recommandation d'un utilisateur pour un autre.
	 * @param conseilleur
	 * @param recommande
	 * @param competence
	 * @return
	 */
	public static boolean recommander (Utilisateur conseilleur, Utilisateur recommande, Competence competence)
	{
		try
		{
			// Connexion a la base de donnees avec la classe MySQL
			Connection db= MySql.connexion();
			PreparedStatement pstmt = db.prepareStatement("INSERT INTO Recommander (idCompetence, idConseilleur, idRecommande) VALUES (?,?,?);");

			// Parametre
			pstmt.setInt(1, competence.getId());
			pstmt.setInt(2, conseilleur.getId());
			pstmt.setInt(3, recommande.getId());
			
			pstmt.executeUpdate();
			db.close();
			
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * Supprime la recommandation d'un utilisateur pour un autre, en focntion d'une competence
	 * @param conseilleur
	 * @param recommande
	 * @param competence
	 * @return
	 */
	public static boolean supprimerRecommandation (Utilisateur conseilleur, Utilisateur recommande, Competence competence)
	{
		try
		{
			// Connexion a la base de donnees avec la classe MySQL
			Connection db= MySql.connexion();
			PreparedStatement pstmt = db.prepareStatement("DELETE FROM Recommander WHERE idCompetence=? AND idConseilleur=? AND idRecommande=?;");

			// Parametre
			pstmt.setInt(1, competence.getId());
			pstmt.setInt(2, conseilleur.getId());
			pstmt.setInt(3, recommande.getId());
			pstmt.executeUpdate();
			db.close();
			
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * En fonction d'une compétence et d'un utilisateur, retourne la liste des utilisateurs qui le recommandent
	 * @param recommande
	 * @param competence
	 * @return
	 */
	public static ArrayList<Utilisateur> getRecommandeurs (Utilisateur recommande, Competence competence)
	{
		ArrayList<Utilisateur> conseilleurs= new ArrayList<Utilisateur>();
		
		try
		{
			Connection db= connexion();
			Statement s = null;
		    ResultSet r = null; 
			/* Création de l'objet gérant les requêtes */
	        s = db.createStatement();
	        
	        /* Exécution d'une requête de lecture */
	        PreparedStatement pstmt =db.prepareStatement( "SELECT idConseilleur FROM Recommander WHERE idRecommande=? AND idCompetence=? ");
	        pstmt.setInt(1, recommande.getId());
	        pstmt.setInt(2, competence.getId());
	        
	        r=pstmt.executeQuery();
	        
	        /* Récupération des données du résultat de la requête de lecture */
	        while ( r.next() ) 
	        {
	        	Utilisateur conseilleur= DBUtilisateur.lireUtilisateur(r.getInt("idConseilleur"));
	        	conseilleurs.add(conseilleur);
	        	
	        }
	        r.close();
	        s.close();
	        db.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conseilleurs;
	}
	
	
}
