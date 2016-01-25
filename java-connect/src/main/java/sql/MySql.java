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
	public static Connection connexion ()
	{
		String url="jdbc:mysql://kriissss.fr:3306/projJava";
		String username="projJava";
		String password="TNYVxtXuYAmvYeaC";
		Connection db=null;
		try 
		{
			db = DriverManager.getConnection(url, username, password);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return db;

	}

}
