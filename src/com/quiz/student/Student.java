package com.quiz.student;

import com.quiz.entity.TableDisplayData;
import com.quiz.helper.ConnectionProvider;

import java.sql.*;
import java.util.*;
public class Student {
	  private static Connection con;
	 
	  
		public static void getStudentInformation() throws SQLException
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("------>Please submit Your Information<------");
			
			System.out.println("Enter Your first Name And Last name");
			String name=sc.nextLine();
			System.out.println("Enter Your City");
			String city=sc.next();
			System.out.println("Enter Your Email-Id");
			String email=sc.next();
			
			Student.insertStudentRecord(name,city,email);
			sc.close();
			
		}
		public static void insertStudentRecord(String name,String city, String email) throws SQLException
		{
			 
			String query="insert into student(name,city,email) values(?,?,?)";
			con=ConnectionProvider.getConnection(con);
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, city);
			ps.setString(3, email);
			
			ps.executeUpdate();
			
			System.out.println("Record inserted Sucessfully....!");
			System.out.println("=================Your Exam Start Now==============");
			fetchQuestion(email);
		}
		public static void fetchQuestion(String email) throws SQLException
		{
			
			int count=0;
		   
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select question,optionA,optionB,optionC,optionD,Ans from questions ORDER BY RAND()");
			Scanner sc=new Scanner(System.in);
			
			int wrongAns=0;
			while(rs.next())
			{
				 System.out.println(rs.getString(1));
				 System.out.println(rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"   "+rs.getString(5));
				 System.out.println("Enter the Answer from type-[A/B/C/D]");
				 
				 
				 
					 char c=sc.next().charAt(0);
					 
					 int flag =Character.compare(c,rs.getString(6).charAt(0));
					 							 
					 if(flag==0)
					 {
						 count++;
					 }
					 else {
						 wrongAns++;
					 }
				
				
			}
			PreparedStatement ps=con.prepareStatement("update student set score=?,grade=? where email=?");
			ps.setInt(1,count);
			if(count>=8 && count<=10)
			{
				ps.setString(2,"A /Pass");
			}
			else if(count>=6 && count<=8)
			{
				ps.setString(2, "B/ Pass");
			}
			else if(count==5)
			{
				ps.setString(2, "C/ Pass");
			}
			else
			{
				ps.setString(2, "D / Fail");
			
			}
				
			ps.setString(3, email);
			
			ps.executeUpdate();
			System.out.println("==============================");
			System.out.println("Exam is Complted");
			System.out.println("Your total Score is   "+ count);
			System.out.println("Your wrong answer is  "+wrongAns);
			
			System.out.println("==============================");
			boolean flag = true;
			
			do {
				System.out.println("====if you want select the choice====");
				System.out.println("1.Start Exam Again  2.You check only your result 3.If you want student  all Record 4.Question Record  5.Exit" );
				System.out.println("Enter Your Choice-");
				int ch=sc.nextInt();
			switch(ch)
			{   
			
			
				case 1:
							fetchQuestion(email);
				
				case 2:
								
								System.out.println("Enter your Email if you want record");
								String email1=sc.next();
								
								 
								 		con=ConnectionProvider.getConnection(con);
								 		PreparedStatement ps1=con.prepareStatement("select id,name,city,email,score,grade from student where email=?");
								 		ps1.setString(1, email1);
								 		ResultSet rs1=ps1.executeQuery();
								 		
								 		
								 		  while(rs1.next())
								 		  {
								 			  	String s1=rs1.getString(4);
								 			  	 
								 			  	if(email1.equals(s1))
								 			  	{
								 				System.out.println("Your Exam ID is==>"+rs1.getInt(1));
								 				System.out.println("Your Name is==>"+rs1.getString(2));
								 				System.out.println("Your City is==>"+rs1.getString(3));
								 				System.out.println("Your Email is==>"+rs1.getString(4));
								 				System.out.println("Your Score is==>"+rs1.getInt(5));
								 				System.out.println("Your Grade is==>"+rs1.getString(6));
								 			  	}
								 			  	else
								 			  	{
								 			  		System.err.println("Enter Valid Email Input");
								 			  	}
								 		   }
								 			 
								 		 break;
								 			
								 		
								 		
								 			
								
				case 3:
						 
						TableDisplayData.getStudentRecord();
						break;
						
				case 4:
					
						TableDisplayData.getQuestionRecord();
						break;
				case 5:
					flag= false;
					System.out.println("Thank you for your response.");
						 break;
						
				default:
						System.out.println("Please Enter valid input");
						
				}
		}while(flag==true);
			
			
		
		
		}
		
	
}
