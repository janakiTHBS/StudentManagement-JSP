<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Student</title>
<link  type="text/css" rel="stylesheet" href="css/style.css"/>
<link  type="text/css" rel="stylesheet" href="css/add-student-style.css"/>
</head>
<body>
<div id="wrapper">
   <div id="header">
     <h2>FooBar University</h2>
   </div>
</div>

<div id="container">
<h3>Update Student</h3>
<form action="StudentController" method="GET">

   <input type="hidden" name="command" value="UPDATE"/>
   <input type="hidden" name="studentId" value="${STUDENT.id}"/>
   <table>
   <tbody>
     <tr>
     <td><label>First Name:</label></td>
     <td><input type="text" name="firstName" value="${STUDENT.firstName}"/></td>
     </tr>
     <tr>
     <td><label>Last Name:</label></td>
     <td><input type="text" name="lastName" value="${STUDENT.lastName}"/></td>
     </tr>
     <tr>
     <td><label>Email:</label></td>
     <td><input type="text" name="email" value="${STUDENT.email}"/></td>
     </tr>
     <tr>
     <td><label></label></td>
     <td><input type="submit" value="Update" class="save"/></td>
     </tr>
   </tbody>
   </table>
</form>
<div style="clear:both;"></div>
<p>
<a href="StudentController">Back to List</a>
</p>
</div>
</body>
</html>