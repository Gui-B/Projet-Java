package sql;


import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

import common.Competence;
import common.Diplome;
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
	
	public void testInsererCompetence()
	{
		Competence c= new Competence(1, "troll");
		Competence c2= new Competence(2, "comp 2");
		DBCompetence.insererCompetence(c);
		assertTrue(DBCompetence.insererCompetence(c2));
	}
	
	public void testModifierCompetence()
	{
		Competence c= new Competence(1, "lole");
		assertTrue(DBCompetence.modifierCompetence(c));
	}
	
	public void testLireCompetences() 
	{
		for (Competence c: DBCompetence.lireCompetences())
		{
			System.out.println(c.getCompetence());
		}
	}
	
	public void testAjoutCompetenceUtilisateur() 
	{
		Competence c= new Competence(1, "lole");
		Utilisateur u= new Utilisateur(1, "nom", "prenom", "mail", "motDePasse");
		assertTrue(DBCompetence.ajoutCompetenceUtilisateur(c, u));
	}
	
	public void testSupprimerCompetenceUtilisateur() 
	{
		Competence c= new Competence(1, "lole");
		Utilisateur u= new Utilisateur(1, "nom", "prenom", "mail", "motDePasse");
		assertTrue(DBCompetence.supprimerCompetenceUtilisateur(c, u));
	}
	
	public void testAjoutDiplome()
	{
		Diplome d= new Diplome(1, "bac");
		Diplome d2= new Diplome(2, "bac");
		DBDiplome.insererDiplome(d2);
		assertTrue(DBDiplome.insererDiplome(d));
	}
	
	public void testModifierDiplome()
	{
		Diplome d= new Diplome(1, "brevet");
		assertTrue(DBDiplome.modifierDiplome(d));
	}
	
	public void testLireDiplomes() 
	{
		for (Diplome d: DBDiplome.lireDiplomes())
		{
			System.out.println(d.getDiplome());
		}
	}
	
	public void testObtenirDiplome()
	{
		Diplome d= new Diplome (1, "brevet");
		Utilisateur u= new Utilisateur(1, "nom", "prenom", "mail", "motDePasse");
		
		assertTrue(DBDiplome.ajoutDiplomeUtilisateur(d, u, 2003));
	}
	
	public void testSupprimerDiplomeUtilisateur() 
	{
		Diplome d= new Diplome (1, "brevet");
		Utilisateur u= new Utilisateur(1, "nom", "prenom", "mail", "motDePasse");
		assertTrue(DBDiplome.supprimerDiplomeUtilisateur(d, u));
	}
	
}
