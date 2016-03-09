package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.Recommander;

public class DBRecommander extends MySql{
	
	public static boolean FaireRecommandation(Recommander util)
	{
		try
		{
			// Connexion a la base de donnees avec la classe MySQL
			Connection db= MySql.connexion();
			PreparedStatement pstmt = db.prepareStatement("INSERT INTO Recommandation (idR, likeC, idRecommandeur, idRecommande, likeC) VALUES (?,?,?,?,?);");

			// Parametres
			
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
