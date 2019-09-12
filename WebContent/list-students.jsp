<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student tracker app</title>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>


<body>
<div id="wrapper">
   <div id="header">
     <h2>FooBar University</h2>
   </div>
</div>

<div id="container">
  <div id="content">
  <input type="button" value="AddStudent" onclick="window.location.href='add-student-form.jsp';return false;" class="add-student-button"/>
  <table>
   <tr>
   <th>FirstName</th>
   <th>LastName</th>
   <th>Email</th>
   <th>Action</th>
   </tr>
   <c:forEach var="student" items="${STUDENTS}">
   <c:url var="tempLink" value="StudentController">
     <c:param name="command" value="LOAD"/>
     <c:param name="studentId" value="${student.id}"/>
   </c:url>
   
   <c:url var="deletelink" value="StudentController">
      <c:param name="command" value="DELETE"/>
     <c:param name="studentId" value="${student.id}"/>
   </c:url>
	  <tr>
	  <td>${student.firstName}</td>
	  <td>${student.lastName}</td>
	  <td>${student.email}</td>
	  <td><a href="${tempLink}">Update</a>
	  |
	  <a href="${deletelink }">Delete</a>
	  </td>
	  </tr>
   </c:forEach>
  
  </table>
  </div>
</div>
</body>
</html>