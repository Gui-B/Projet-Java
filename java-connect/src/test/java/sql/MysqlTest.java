package sql;


import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

import common.Utilisateur;
import sql.MySql;
import sql.DBUtilisateur;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MysqlTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public MysqlTest( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( MysqlTest.class );
	}

	public void testConnexion ()
	{
		try
		{
			Connection db = MySql.connexion();
			assertTrue((db!=null));
			db.close();
			db=null;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			assertTrue(false);
		}

	}

	public void testDetruireBase()
	{
		assertTrue(MySql.detruireBase());
	}
	
	public void testCreerBase()
	{
		assertTrue(MySql.creerBase());
	}

	public void testInsertUtilisateur()
	{
		Utilisateur util= new Utilisateur(0, "dumbuldore","albus", "ad@sorcier.com", "baguette");
		Utilisateur util2= new Utilisateur(0, "potter","harry", "hp@sorcier.com", "grifondor");
		DBUtilisateur.insererUtilisateur(util);
		assertTrue(DBUtilisateur.insererUtilisateur(util2));

	}
	
	public void testModifierUtilisateur()
	{
		Utilisateur util= new Utilisateur(1, "dumbuldore","albus", "ad@baguette.com", "baguette");
		assertTrue(DBUtilisateur.modifierUtilisateur(util));

	}
	
	public void testLireUtilisateurs() 
	{
		for (Utilisateur util : DBUtilisateur.lireUtilisateurs())
		{
			System.out.println(util.getNom());
		}
	}
}
