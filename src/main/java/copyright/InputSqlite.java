package copyright;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InputSqlite {
	public void clear() 
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			Connection connection=DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"\\db\\r.db");
			Statement statement=connection.createStatement();
            statement.execute("delete from result");
            statement.close();
            connection.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void insert(String[] sql) 
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			Connection connection=DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"\\db\\r.db");
			Statement statement=connection.createStatement();
			for(int i=0;i<sql.length;i++) 
			{
				statement.execute(sql[i]);
			}
			statement.close();
            connection.close();
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
}
