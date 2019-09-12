package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	
	private StudentDBUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			studentDbUtil=new StudentDBUtil(dataSource); 
		}catch(Exception e) {
		  throw  new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String theCommand=request.getParameter("command");
			if (theCommand==null) {
				theCommand="LIST";
			}
			
			switch(theCommand) {
			case "LIST":
				listStudents(request,response);
			    break;
			case "ADD":
				addStudent(request,response);
				break;
			case "LOAD":
				loadStudent(request,response);
			    break;
			case "UPDATE":
				updateStudent(request,response);
			    break;
			case "DELETE":
				deleteStudent(request,response);
				break;
			default :
				listStudents(request,response);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		String theStudentId=request.getParameter("studentId");
		studentDbUtil.deleteStudent(theStudentId);
		listStudents(request,response);
		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id=Integer.parseInt(request.getParameter("studentId"));
		System.out.println(id);
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		Student theStudent=new Student(id,firstName,lastName,email);
		System.out.println(theStudent);
		studentDbUtil.updateStudent(theStudent);
		listStudents(request,response);
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String studentId=request.getParameter("studentId");
		System.out.println("Load"+studentId);
		Student theStudent=studentDbUtil.getStudent(studentId);
		request.setAttribute("STUDENT", theStudent);
		System.out.println(theStudent.getId());
		RequestDispatcher dispacther=request.getRequestDispatcher("/update-student-form.jsp");
		dispacther.forward(request, response);		
		
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		
		Student theStudent=new Student(firstName,lastName,email);
		
		studentDbUtil.addStudent(theStudent);
		
		listStudents(request,response);
	}


	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Student> students=studentDbUtil.getStudents();
		request.setAttribute("STUDENTS", students);
		RequestDispatcher dispacther=request.getRequestDispatcher("/list-students.jsp");
		dispacther.forward(request, response);
		
		
	}

}
