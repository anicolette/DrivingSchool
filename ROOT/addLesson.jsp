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

  String client = request.getParameter("client");
  String instructor = request.getParameter("instructor");
  String year = request.getParameter("year");
  String month = request.getParameter("month");
  String day = request.getParameter("day");
  String hour = request.getParameter("hour");
  String minute = request.getParameter("minute");
  String isBlocked = request.getParameter("isBlocked");
  String milesDriven = request.getParameter("milesDriven");
  
  int instructorID = -1;
  try {
	  instructorID = Integer.parseInt(instructor);
  } catch (Exception e) {
	  throw e;
  }
  int clientID = -1;
  try {
	  clientID = Integer.parseInt(client);
  } catch (Exception e) {
	  throw e;
  }
  boolean blocked = false;
  try {
	  blocked = Boolean.parseBoolean(isBlocked);
  } catch (Exception e) {
	  throw e;
  }
  int miles = 0;
  try {
	  miles = Integer.parseInt(milesDriven);
  } catch (Exception e) {
	  throw e;
  }
  
  if(!dbcontroller.addLesson(clientID, instructorID, year, month, day, hour, minute, blocked, miles)){
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
