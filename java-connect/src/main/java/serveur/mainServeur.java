package serveur;

public class mainServeur {

	
	public static void main(String[] args) 
	{
		GestionServeur gs= new GestionServeur();
		Serveur serveur= new Serveur();
		try 
		{
			while (true)
			{	
				serveur.connectClient(gs);
			}
		} 
		catch (Exception e) 
		{
			System.err.println(e);
			e.printStackTrace();
		}
	}

}
