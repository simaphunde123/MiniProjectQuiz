package com.quiz.helper;
import java.sql.*;
public class ConnectionProvider {
	//private static Connection con;
	
	
	public static Connection getConnection(Connection con)  
	{
		  
		try {
			
				
			if(con==null)
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz","root","MySQL1");
			}	
			 
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		
		return con;
		
	}
	
	
	
}
