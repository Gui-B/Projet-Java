package sql;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import common.Competence;
import common.Message;
import common.Utilisateur;
import sql.MySql;

public class DBMessages extends MySql{

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
	
//	public static boolean recevoirMessage(Utilisateur envoyeur, Utilisateur destinataire, String message)
//	{
//		try
//		{
//			//Date
//			Date date= new Date();
//			
//			// Connexion a la base de donnees avec la classe MySQL
//			// Insertion d'une competence requete SQL 
//			Connection db= MySql.connexion();
//			PreparedStatement pstmt = db.prepareStatement("INSERT INTO Messages (idEnvoyeur, idDestinataire, dateM, message) VALUES (?,?,?,?);");
//
//			// Parametres
//			pstmt.setInt(1, envoyeur.getId());
//			pstmt.setInt(2, destinataire.getId());
//			pstmt.setLong(3, date.getTime());
//			pstmt.setString(4, message);
//			
//			
//			pstmt.executeUpdate();
//			db.close();
//			
//			return true;
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//			return false;
//		}
//		
//	}
	
	public static ArrayList<Message> lireMessagesNonLus(Utilisateur destinataire)
	{
		ArrayList<Message> messages= new ArrayList<Message>();
		
		try
		{
			Connection db= connexion();
			Statement s = null;
		    ResultSet r = null; 
			/* Création de l'objet gérant les requêtes */
	        s = db.createStatement();
	        /* Exécution d'une requête de lecture */
	        
	        PreparedStatement pstmt =db.prepareStatement( "SELECT * FROM Messages WHERE idDestinataire=? AND lu=0;");
	        pstmt.setInt(1, destinataire.getId());
	        r=pstmt.executeQuery();
	        
	        /* Récupération des données du résultat de la requête de lecture */
	        while ( r.next() ) 
	        {
	        	Utilisateur env= DBUtilisateur.lireUtilisateur(r.getInt("idEnvoyeur"));
	        	Utilisateur dest= DBUtilisateur.lireUtilisateur(r.getInt("idDestinataire"));
	        	messages.add(new Message(r.getInt("idM"), env, dest, r.getString("message"), r.getLong("dateM"), r.getInt("lu")));
	        } 
	        r.close();
	        s.close();
	        db.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return messages;
	}
}
