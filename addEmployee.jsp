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
  String officeStr = request.getParameter("Office No");
  int salary = 0;
  int officeNo = -1;
  try{
	salary = Integer.parseInt(salStr);
  } catch(Exception e){
	throw e;
  }

  try{
	officeNo = Integer.parseInt(officeStr);
  } catch(Exception e){
	officeNo = -1;
  }

  dbcontroller.addEmployee(fName, mName, lName, salary, officeNo);  
  response.sendRedirect("index.html");
  

  // close the dbcontroller and relase all resources occupied by it.
  dbcontroller.Close();
%>
</div>
</body>
</html>
