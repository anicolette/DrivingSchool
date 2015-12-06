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

  String year = request.getParameter("insDate");
  String month = request.getParameter("insMonth");
  String day = request.getParameter("insDay");
  String hour = request.getParameter("insHour");
  String minute = request.getParameter("insMinute");
  String inspector = request.getParameter("inspector");
  String carID = request.getParameter("carId");
  
  int inspectorID = -1;
  try {
	  inspectorID = Integer.parseInt(inspector);
  } catch (Exception e) {
	  throw e;
  }
  int car = -1;
  try {
	  car = Integer.parseInt(carID);
  } catch (Exception e) {
	  throw e;
  }
  
  if(!dbcontroller.addInspection(year, month, day, hour, minute, inspectorID, car)){
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
