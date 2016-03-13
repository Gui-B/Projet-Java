package sql;


import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

import common.Competence;
import common.Diplome;
import common.Message;
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
		Utilisateur util= new Utilisateur(0, "dumbuldore","albus","ad@sorcier.com","baguette", 1,1,1,1);
		Utilisateur util2= new Utilisateur(0, "potter","harry", "hp@sorcier.com","grifondor", 0,0,0,0);
		DBUtilisateur.insererUtilisateur(util);
		assertTrue(DBUtilisateur.insererUtilisateur(util2));

	}
	
	public void testModifierUtilisateur()
	{
		Utilisateur util= new Utilisateur(1, "dumbuldore","albus", "ad@baguette.com", "baguette",1,0,1,0);
		assertTrue(DBUtilisateur.modifierUtilisateur(util));

	}
	
	public void testLireUtilisateurs() 
	{
		for (Utilisateur util : DBUtilisateur.lireUtilisateurs())
		{
			System.out.println(util);
		}
	}
	
	public void testLireUtilisateur()
	{
		Utilisateur u= DBUtilisateur.lireUtilisateur(1);
		if (u==null)
		{
			assertTrue(false);
		}
		
		System.out.println(u);
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
		Utilisateur u= new Utilisateur(1, "nom", "prenom", "mail", "motDePasse",1);
		assertTrue(DBCompetence.ajoutCompetenceUtilisateur(c, u));
	}
	
	public void testSupprimerCompetenceUtilisateur() 
	{
		Competence c= new Competence(1, "lole");
		Utilisateur u= new Utilisateur(1, "nom", "prenom", "mail", "motDePasse",1);
		assertTrue(DBCompetence.supprimerCompetenceUtilisateur(c, u));
	}
	
	public void testLireCompetenceUtilisateur()
	{
		Competence c1= new Competence(1, "lole");
		Competence c2= new Competence(2, "lala");
		Utilisateur u= new Utilisateur(1, "nom", "prenom", "mail", "motDePasse",1);
		
		DBCompetence.ajoutCompetenceUtilisateur(c1, u);
		DBCompetence.ajoutCompetenceUtilisateur(c2, u);
		
		ArrayList<Competence> competences= DBCompetence.lireCompetencesUtilisateur(u);
		
		for(Competence c: competences)
		{
			System.out.println("COMPETENCE DE:"+u.getNom()+" "+c.getCompetence());
		}
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
		Utilisateur u= new Utilisateur(1, "nom", "prenom", "mail", "motDePasse",1);
		
		assertTrue(DBDiplome.ajoutDiplomeUtilisateur(d, u, 2003));
	}
	
	public void testSupprimerDiplomeUtilisateur() 
	{
		Diplome d= new Diplome (1, "brevet");
		Utilisateur u= new Utilisateur(1, "nom", "prenom", "mail", "motDePasse",1);
		assertTrue(DBDiplome.supprimerDiplomeUtilisateur(d, u));
	}
	
	public void testDiplomesUtilisateur()
	{
		Diplome d1= new Diplome (1, "brevet");
		Diplome d2= new Diplome(2, "bac");
		Utilisateur u= new Utilisateur(1, "nom", "prenom", "mail", "motDePasse",1);
		
		DBDiplome.ajoutDiplomeUtilisateur(d1,u, 1992);
		DBDiplome.ajoutDiplomeUtilisateur(d2, u, 2013);
		
		ArrayList<Diplome>diplomes= DBDiplome.lireDiplomesUtilisateur(u);
		
		for (Diplome d: diplomes)
		{
			System.out.println("DIPLOME DE "+u.getNom()+" "+d.getDiplome()+" "+d.getAnnee());
		}
	}
	
	public void testInsererMessage()
	{
		Utilisateur u1= new Utilisateur (1, "nom", "prenom", "mail", "motDePasse",1);
		Utilisateur u2= new Utilisateur (2, "nom", "prenom", "mail", "motDePasse",1);
		
		Message message= new Message(u1, u2, "lol");
		assertTrue(DBMessages.insererMessage(message));
	}
	
	public void testLireMessagesNonLus()
	{
		Utilisateur u2= new Utilisateur (2, "nom", "prenom", "mail", "motDePasse",1);
		
		for (Message message: DBMessages.lireMessagesNonLus(u2))
		{
			System.out.println(message);
		}
		assertTrue(true);
	}
	
	public void testLireMessage()
	{
		System.out.println(DBMessages.consulterMessage(2));
		assertTrue(true);
	}
	
	public void testRecommander()
	{
		Utilisateur u1= new Utilisateur (1, "nom", "prenom", "mail", "motDePasse",1);
		Utilisateur u2= new Utilisateur (2, "nom", "prenom", "mail", "motDePasse",1);
		Competence c= new Competence(1, "blblbl");
		Competence c2= new Competence(2, "blblbl");
		DBRecommander.recommander(u2, u1, c2);
		assertTrue(DBRecommander.recommander(u2, u1, c));
	}
	
//	public void testSupprimeRecommandation()
//	{
//		Utilisateur u1= new Utilisateur (1, "nom", "prenom", "mail", "motDePasse",1);
//		Utilisateur u2= new Utilisateur (2, "nom", "prenom", "mail", "motDePasse",1);
//		Competence c= new Competence(1, "blblbl");
//		assertTrue(DBRecommander.supprimerRecommandation(u2, u1, c));
//	}
	
	public void testGetConseilleurs()
	{
		Utilisateur u1= new Utilisateur (1, "nom", "prenom", "mail", "motDePasse",1);
		Competence c= new Competence(1, "blblbl");
		ArrayList<Utilisateur> conseilleurs = DBRecommander.getRecommandeurs(u1, c);
		for (Utilisateur u: conseilleurs)
		{
			System.out.println(u);
		}
		
		assertTrue(true);
	}
	
}
