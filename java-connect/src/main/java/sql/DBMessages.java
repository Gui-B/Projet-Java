package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.Competence;

public class DBMessages {

	public static boolean insererCompetence(Competence c)
	{
		try
		{
			// Connexion a la base de donnees avec la classe MySQL
			// Insertion d'une competence requete SQL 
			Connection db= MySql.connexion();
			PreparedStatement pstmt = db.prepareStatement("INSERT INTO Messages (idEnvoyeur, idDestinataire, dateM, message) VALUES (?,?,?,?);");

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
}
