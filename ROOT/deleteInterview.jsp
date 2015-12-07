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

  String year = request.getParameter("year");
  String month = request.getParameter("month");
  String day = request.getParameter("day");
  String hour = request.getParameter("hour");
  String minute = request.getParameter("minute");
  String intIDstr = request.getParameter("intID");
  
  int intID = -1;
  try {
	  intID = Integer.parseInt(intIDstr);
  } catch(Exception e) {
	  throw e;
  }

  if(!dbcontroller.deleteInterview(year,month,day,hour,minute,intID)){
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
