package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.print.attribute.SetOfIntegerSyntax;

import common.Competence;
import common.Utilisateur;
import common.Competence;

public class DBCompetence extends MySql
{
	public static boolean insererCompetence(Competence c)
	{
		try
		{
			Connection db= MySql.connexion();
			PreparedStatement pstmt = db.prepareStatement("INSERT INTO Competences (NomC) VALUES(?);");

			// Parametres
			pstmt.setString(1,c.getCompetence());
			
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
	
	public static boolean modifierCompetence(Competence c)
	{
		try
		{
			Connection db= MySql.connexion();
			PreparedStatement pstmt = db.prepareStatement("UPDATE Competences SET NomC=? WHERE idC=?;");

			// Parametres
			pstmt.setString(1, c.getCompetence());
			pstmt.setInt(2, c.getId());
			
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
	
	public static ArrayList<Competence> lireCompetences()
	{
		ArrayList<Competence> Competences= new ArrayList<Competence>();
		
		try
		{
			Connection db= connexion();
			Statement s = null;
		    ResultSet r = null; 
			/* Création de l'objet gérant les requêtes */
	        s = db.createStatement();
	        /* Exécution d'une requête de lecture */
	        r = s.executeQuery( "SELECT * FROM Competences;");
	 
	        /* Récupération des données du résultat de la requête de lecture */
	        while ( r.next() ) 
	        {
	        	Competences.add(new Competence(r.getInt("idC"), r.getString("NomC")));
	        } 
	        r.close();
	        s.close();
	        db.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return Competences;
	}
	
	public static boolean ajoutCompetenceUtilisateur (Competence c, Utilisateur u)
	{
		try
		{
			Connection db= MySql.connexion();
			PreparedStatement pstmt = db.prepareStatement("INSERT INTO Avoir (idC, idU )VALUES (?,?);");

			// Parametres
			pstmt.setInt(1, c.getId());
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
	
	public static boolean supprimerCompetenceUtilisateur (Competence c, Utilisateur u)
	{
		try
		{
			Connection db= MySql.connexion();
			PreparedStatement pstmt = db.prepareStatement("DELETE FROM Avoir WHERE idC=? AND idU=?;");

			// Parametres
			pstmt.setInt(1, c.getId());
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
