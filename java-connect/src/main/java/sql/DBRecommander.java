package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.Competence;
import common.Recommander;
import common.Utilisateur;

public class DBRecommander extends MySql{
	
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
	
	
}
