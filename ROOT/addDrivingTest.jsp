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

  String instrID = request.getParameter("Instructor ID");
  String clientID = request.getParameter("clientID");
  String year = request.getParameter("year");
  String month = request.getParameter("month");
  String day = request.getParameter("day");
  String hour = request.getParameter("hour");
  String minute = request.getParameter("minute");
  String testType = request.getParameter("testType");
  String pass = request.getParameter("pass");
  
  int instr = -1;
  try {
	  instr = Integer.parseInt(instrID);
  } catch(Exception e) {
	  throw e;
  }
  int client = -1;
  try {
	  client = Integer.parseInt(clientID);
  } catch(Exception e) {
	  throw e;
  }
  char testTypeChar = testType.charAt(0);
  boolean hasPassed = false;
  try {
	  hasPassed = Boolean.parseBoolean(pass);
  } catch(Exception e) {
	  throw e;
  }

  if(!dbcontroller.addDrivingTest(instr, client, year, month, day, hour, minute, testTypeChar, hasPassed)){
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
