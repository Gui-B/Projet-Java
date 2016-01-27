package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.Diplome;
import common.Utilisateur;

public class DBDiplome extends MySql 
{
	public static boolean insererDiplome(Diplome d)
	{
		try
		{
			Connection db= MySql.connexion();
			PreparedStatement pstmt = db.prepareStatement("INSERT INTO Diplome (NomD) VALUES(?);");

			// Parametres
			pstmt.setString(1,d.getDiplome());
			
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
	
	public static boolean modifierDiplome(Diplome d)
	{
		try
		{
			Connection db= MySql.connexion();
			PreparedStatement pstmt = db.prepareStatement("UPDATE Diplome SET NomD=? WHERE idD=?;");

			// Parametres
			pstmt.setString(1, d.getDiplome());
			pstmt.setInt(2, d.getId());
			
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
	
	public static ArrayList<Diplome> lireDiplomes()
	{
		ArrayList<Diplome> Diplomes= new ArrayList<Diplome>();
		
		try
		{
			Connection db= connexion();
			Statement s = null;
		    ResultSet r = null; 
			/* Création de l'objet gérant les requêtes */
	        s = db.createStatement();
	        /* Exécution d'une requête de lecture */
	        r = s.executeQuery( "SELECT * FROM Diplome;");
	 
	        /* Récupération des données du résultat de la requête de lecture */
	        while ( r.next() ) 
	        {
	        	Diplomes.add(new Diplome(r.getInt("idD"), r.getString("NomD")));
	        } 
	        r.close();
	        s.close();
	        db.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return Diplomes;
	}
	
	public static boolean ajoutDiplomeUtilisateur (Diplome d, Utilisateur u, int annee)
	{
		try
		{
			Connection db= MySql.connexion();
			PreparedStatement pstmt = db.prepareStatement("INSERT INTO Obtenir (idD, idU, Annee )VALUES (?,?,?);");

			// Parametres
			pstmt.setInt(1, d.getId());
			pstmt.setInt(2, u.getId());
			pstmt.setInt(3, annee);
			
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
	
	public static boolean supprimerDiplomeUtilisateur (Diplome d, Utilisateur u)
	{
		try
		{
			Connection db= MySql.connexion();
			PreparedStatement pstmt = db.prepareStatement("DELETE FROM Obtenir WHERE idD=? AND idU=?;");

			// Parametres
			pstmt.setInt(1, d.getId());
			pstmt.setInt(2, u.getId());
			
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
}
