package com.quiz.entity;

import com.quiz.helper.ConnectionProvider;
import java.sql.*;
public class TableDisplayData {
	
	 private static Connection con; 
	public static void getStudentRecord() throws SQLException
	{
		con=ConnectionProvider.getConnection(con);
		
		PreparedStatement ps=con.prepareStatement("select * from student");
		ResultSet rs=ps.executeQuery();
		System.out.println("ID"+""    +"Name"+"         "+"City"+"        "+"EmailID"+"           "+"Score"+"              "+"Grade/Result"         );

		
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"         "+rs.getString(2)+"        "+rs.getString(3)+"           "+rs.getString(4)+"              "+rs.getInt(5)+"          "+rs.getString(6));
		}
		
	}
	public static void getQuestionRecord() throws SQLException
	{
		con=ConnectionProvider.getConnection(con);
		
		PreparedStatement ps=con.prepareStatement("select questionid,question,optionA,optionB,optionC,optionD from questions  ");
		ResultSet rs=ps.executeQuery();
		
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"           "+rs.getString(2)+"         "+rs.getString(3)+"           "+rs.getString(4)+"           "+rs.getString(5)+"         "+rs.getString(6));
		}
		
	}

}
