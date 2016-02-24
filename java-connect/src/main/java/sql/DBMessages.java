package sql;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import common.Competence;
import common.Message;
import common.Utilisateur;

public class DBMessages {

	public static boolean insererMessage(Message message)
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
			pstmt.setInt(1, message.getEnvoyeur().getId());
			pstmt.setInt(2, message.getDestinataire().getId());
			pstmt.setLong(3, message.getDate().getTime());
			pstmt.setString(4, message.getMessage());
			
			
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
	
	
	
	public static boolean envoyerMessage(Utilisateur envoyeur, Utilisateur destinataire, String message)
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
	
	public static boolean recevoirMessage(Utilisateur envoyeur, Utilisateur destinataire, String message)
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
