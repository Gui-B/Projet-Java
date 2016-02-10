package serveur;

public class mainServeur {

	//Le serveur attend une connection d'un client creation d'une boucle true pour que le serveur reste ouvert
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
