/**
 * 
 */
package sql;
import java.sql.*;

/**
 * @author robin
 *
 */
public class MySql 
{
	/**
	 * Retourne une connexion à la base de donnees;
	 * Les id de connexion sont a faire dans la fonction
	 * 
	 * @return Connection
	 */
	public static Connection connexion ()
	{
		 
		String url="jdbc:mysql://kriissss.fr:3306/projJava";
		String username="projJava";
		String password="TNYVxtXuYAmvYeaC";
		Connection db=null;
		
		
		try 
		{
			Class.forName( "com.mysql.jdbc.Driver" );
			db = DriverManager.getConnection(url, username, password);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch ( ClassNotFoundException e ) 
		{
		   e.printStackTrace();
		}

		return db;

	}
	
	/**
	 *Cree une base de donnees 
	 *
	 * @return
	 */
	public static boolean creerBase()
	{
		try
		{
			Connection db = connexion();
			
			//Detruire base
			detruireBase();
			
			//Table utilisateur
			db.createStatement().execute("CREATE TABLE IF NOT EXISTS Utilisateur (IdU int(6) NOT NULL PRIMARY KEY AUTO_INCREMENT, Mail varchar(128) NOT NULL, Nom varchar(256) NOT NULL, Prenom varchar(256) NOT NULL, Mdp varchar(256) NOT NULL, Admin tinyint(1) NOT NULL, vuMail int(6) DEFAULT 0, vuComp int(6) DEFAULT 0, vuDip int(6) DEFAULT 0 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			db.createStatement().execute("ALTER TABLE Utilisateur ADD CONSTRAINT UK_UTILISATEUR UNIQUE KEY Mail (Mail);");
			
			//Table Competences
			db.createStatement().execute("CREATE TABLE IF NOT EXISTS Competences (idC int(6) NOT NULL PRIMARY KEY AUTO_INCREMENT, NomC varchar(256) DEFAULT NULL ) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			
			//Table Diplome
			db.createStatement().execute("CREATE TABLE IF NOT EXISTS Diplome (idD int(6) NOT NULL PRIMARY KEY AUTO_INCREMENT, NomD varchar(256) NOT NULL ) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			
			//Table Avoir
			db.createStatement().execute("CREATE TABLE IF NOT EXISTS Avoir (idU int(6) NOT NULL, idC int(6) NOT NULL ) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			db.createStatement().execute("ALTER TABLE Avoir ADD CONSTRAINT PK_AVOIR PRIMARY KEY (IdU,idC), ADD KEY FK_Avoir_idC (idC), ADD CONSTRAINT FK_Avoir_idC FOREIGN KEY (idC) REFERENCES Competences (idC), ADD CONSTRAINT FK_Avoir_IdU FOREIGN KEY (IdU) REFERENCES Utilisateur (IdU); ");
			
			//Table Obtenir
			db.createStatement().execute("CREATE TABLE IF NOT EXISTS Obtenir (Annee int(6) NOT NULL, IdU int(6) NOT NULL, idD int(6) NOT NULL ) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			db.createStatement().execute("ALTER TABLE Obtenir ADD PRIMARY KEY (IdU,idD), ADD CONSTRAINT FK_Obtenir_idD FOREIGN KEY (idD) REFERENCES Diplome (idD), ADD CONSTRAINT FK_Obtenir_IdU FOREIGN KEY (IdU) REFERENCES Utilisateur (IdU);");
			
			//Table Message
			db.createStatement().execute("CREATE TABLE IF NOT EXISTS Messages(idM int(6) NOT NULL PRIMARY KEY AUTO_INCREMENT, idEnvoyeur int(6) NOT NULL, idDestinataire int(6) NOT NULL, dateM bigint NOT NULL, message varchar(1024) NOT NULL, lu int(6) DEFAULT 0 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			db.createStatement().execute("ALTER TABLE Messages ADD CONSTRAINT FK_messages_u1 FOREIGN KEY (idEnvoyeur) REFERENCES Utilisateur(IdU), ADD CONSTRAINT FK_messages_u2 FOREIGN KEY (IdDestinataire) REFERENCES Utilisateur(IdU);");
			
			
			db.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}


		return true;
	}
	
	/**
	 * Detruit la base de donnees
	 * @return
	 */
	public static boolean detruireBase()
	{
		try
		{
			Connection db = connexion();
			db.createStatement().execute("DROP TABLE IF EXISTS Messages;");
			db.createStatement().execute("DROP TABLE IF EXISTS Obtenir;");
			db.createStatement().execute("DROP TABLE IF EXISTS Avoir;");
			db.createStatement().execute("DROP TABLE IF EXISTS Diplome;");
			db.createStatement().execute("DROP TABLE IF EXISTS Competences;");
			db.createStatement().execute("DROP TABLE IF EXISTS Utilisateur;");
			db.close();

		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}


		return true;
	}

}
