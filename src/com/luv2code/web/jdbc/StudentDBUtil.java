package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDBUtil {
	
	private DataSource dataSource;
	
	public StudentDBUtil(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	public List<Student> getStudents() throws Exception{
		
		List<Student> students=new ArrayList<>();
		
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			con=dataSource.getConnection();
			
			String sql="select * from student order by last_name";
			
			stmt=con.createStatement();
			
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id=rs.getInt("id");
				String firstName=rs.getString("first_name");
				String lastName=rs.getString("last_name");
				String email=rs.getString("email");
				
				Student s=new Student(id,firstName,lastName,email);
				
				students.add(s);
			
			}
			
		    return students;
		}
		finally
		{
			
			close(con,stmt,rs);
		}
			
	}

	private void close(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (rs!=null){
				rs.close();
			}
			if (stmt!=null) {
				stmt.close();
			}
			if (con!=null) {
				con.close();
			}
		}catch(Exception e) {
			
		}
		
	}

	
	public void addStudent(Student student) throws SQLException {
		
		Connection con=null;
		PreparedStatement stmt=null;
		
		try {
			con=dataSource.getConnection();
			String sql ="insert into student"
					    +"(first_name,last_name,email)"
					    +"values(?,?,?)";
			stmt=con.prepareStatement(sql);
			stmt.setString(1,student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setString(3, student.getEmail());
			stmt.execute();	
			
		}
		finally{
			close(con,stmt,null);
		}
	}

	public Student getStudent(String studentId) throws Exception {
		
		Student theStudent=null;
		
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int theStudentId;
		
		try {
			theStudentId=Integer.parseInt(studentId);
			System.out.println(theStudentId);
     		con=dataSource.getConnection();
			
			String sql="select * from student where id=?";
			
			stmt=con.prepareStatement(sql);
			stmt.setInt(1,theStudentId);
			
			rs=stmt.executeQuery();
			
			if (rs.next()){
				String firstName=rs.getString("first_name");
				String lastName=rs.getString("last_name");
				String email=rs.getString("email");
				int id=rs.getInt("id");
				theStudent=new Student(id,firstName,lastName,email);
				
			}
			else {
				throw new Exception("Could not find student id"+theStudentId);
			}
	    System.out.println(theStudent);
	    return theStudent;
		}finally {
			close(con,stmt,rs);
		}
			
	}

	public void updateStudent(Student theStudent) throws SQLException {
		
		Connection con=null;
		PreparedStatement stmt=null;
		
		try {
		  con=dataSource.getConnection();
		  
		  String sql="update student"
				     +" set first_name=?,last_name=?,email=?"
				     +" where id=?";
		  stmt=con.prepareStatement(sql);
		  stmt.setString(1, theStudent.getFirstName());
		  stmt.setString(2, theStudent.getLastName());
		  stmt.setString(3, theStudent.getEmail());
		  stmt.setInt(4, theStudent.getId());
		  System.out.println(theStudent.getId());
		  int a=stmt.executeUpdate();
		  System.out.println(stmt);
		  System.out.println(a);
		  
		  	}
		finally {
			close(con,stmt,null);
		}
	}

	public void deleteStudent(String theStudentId) throws Exception {
	Connection con=null;
	PreparedStatement stmt=null;
	
	try {
		int studentId=Integer.parseInt(theStudentId);
	con=dataSource.getConnection();
	
	String sql="delete from student where id=?";
	
	stmt=con.prepareStatement(sql);
	
	stmt.setInt(1, studentId);
	
	stmt.execute();
		
	}
	finally {
		close(con,stmt,null);
	}
		
	}
	
}
