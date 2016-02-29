package common;


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

public class GestionConnexionsTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public GestionConnexionsTest( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( GestionConnexionsTest.class );
	}

	public void testInitialiseEcouteMessagerieInstantannee ()
	{
		GestionConnexions gc = new GestionConnexions();
		try {
			gc.initialiseEcouteMessagerieInstantannee(2, "127.0.0.1", "12345");
			assertTrue(gc.ecouteMessagerieInstantannee(new Utilisateur(2, "", "", "", "", 0)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
	
	public void testInitialiseEcouteMessagerieInstantanneeDejaCOnnecte ()
	{
		GestionConnexions gc = new GestionConnexions();
		try {
			gc.initialiseEcouteMessagerieInstantannee(2, "127.0.0.1", "12345");
			gc.initialiseEcouteMessagerieInstantannee(2, "127.0.0.1", "12345");
			assertTrue(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(true);
		}
		
	}
	
	public void testDetruireEcouteMessagerieInstantannee ()
	{
		GestionConnexions gc = new GestionConnexions();
		try 
		{
			gc.initialiseEcouteMessagerieInstantannee(2, "127.0.0.1", "12345");
			gc.detruireEcouteMessagerieInstantannee(2);
			assertFalse(gc.ecouteMessagerieInstantannee(new Utilisateur(2, "", "", "", "", 0)));
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testGetUtilisateursEnEcoute ()
	{
		GestionConnexions gc = new GestionConnexions();
		try 
		{
			gc.initialiseEcouteMessagerieInstantannee(2, "127.0.0.1", "12345");
			gc.initialiseEcouteMessagerieInstantannee(3, "127.0.0.1", "12345");
			gc.initialiseEcouteMessagerieInstantannee(4, "127.0.0.1", "12345");
			
			Set<Integer> liste= gc.getUtilisateursEnEcoute();
			for (int i : liste)
			{
				System.out.println(i);
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testGetIpUtilisateur ()
	{
		GestionConnexions gc = new GestionConnexions();
		try 
		{
			gc.initialiseEcouteMessagerieInstantannee(2, "127.0.0.1", "12345");
			gc.initialiseEcouteMessagerieInstantannee(3, "127.0.0.1", "12345");
			gc.initialiseEcouteMessagerieInstantannee(4, "127.0.0.1", "12345");
			
			Utilisateur u= new Utilisateur(2, "", "", "", "", 0);
			
			System.out.println(gc.getIpUtilisateursEnEcoute(u));
			assertTrue(true);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testGetPortUtilisateur ()
	{
		GestionConnexions gc = new GestionConnexions();
		try 
		{
			gc.initialiseEcouteMessagerieInstantannee(2, "127.0.0.1", "12345");
			gc.initialiseEcouteMessagerieInstantannee(3, "127.0.0.1", "12345");
			gc.initialiseEcouteMessagerieInstantannee(4, "127.0.0.1", "12345");
			
			Utilisateur u= new Utilisateur(2, "", "", "", "", 0);
			
			System.out.println(gc.getPortUtilisateursEnEcoute(u));
			assertTrue(true);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}


	
}
