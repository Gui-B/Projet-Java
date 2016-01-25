package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.Utilisateur;

public class DBUtilisateur extends MySql
{
	public static void ecrireLocal(Connection db, Utilisateur util)
	{
		try
		{
			PreparedStatement pstmt = db.prepareStatement("INSERT INTO local (id, nom, lieulocal) VALUES (?,?,?);");

			// Parametres
			pstmt.setInt(1,local.getIdLocal());
			pstmt.setString(2, local.getNomLocal());
			pstmt.setString(3, local.getLieuLocal());
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
}
