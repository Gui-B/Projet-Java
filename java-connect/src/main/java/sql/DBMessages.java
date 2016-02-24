package sql;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import common.Competence;
import common.Utilisateur;

public class DBMessages {

	public static boolean insererMessage(Utilisateur envoyeur, Utilisateur destinataire, String message)
	{
		try
		{
			//Date
			Date date= new Date();
			
			// Connexion a la base de donnees avec la classe MySQL
			// Insertion d'une competence requete SQL 
			Connection db= MySql.connexion();
			PreparedStatement pstmt = db.prepareStatement("INSERT INTO Messages (idEnvoyeur, idDestinataire, dateM, message) VALUES (?,?,?,?);");

			// Parametres
			pstmt.setInt(1, envoyeur.getId());
			pstmt.setInt(2, destinataire.getId());
			pstmt.setLong(3, date.getTime());
			pstmt.setString(4, message);
			
			
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
