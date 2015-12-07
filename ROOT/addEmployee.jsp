<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page import="java.util.*,java.lang.StringBuffer,
    dbController.DatabaseController" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Results</title>
</head>
<body>
<div id="searchresult">
<%
  request.setCharacterEncoding("utf-8");
  response.setContentType("text/html;charset=utf-8");

  DatabaseController dbcontroller = new DatabaseController();
  // connect to backend database server via the databasecontroller, which
  // is a wrapper class providing necessary methods for this particular
  // application
  dbcontroller.Open();

  String fName = request.getParameter("First Name");
  String mName = request.getParameter("Middle Name");
  String lName = request.getParameter("Last Name");
  String salStr = request.getParameter("Salary");
  String phoneNum = request.getParameter("phoneNum");
  int salary = 0;
  try{
	salary = Integer.parseInt(salStr);
  } catch(Exception e){
	throw e;
  }

  if(!dbcontroller.addEmployee(fName, mName, lName, salary, phoneNum)){
	out.write("Error!");
	out.write("</br><a href=\"index.html\">Return to home</a>");
  } else{ 
  	response.sendRedirect("index.html");
  }
  

  // close the dbcontroller and relase all resources occupied by it.
  dbcontroller.Close();
%>
</div>
</body>
</html>
