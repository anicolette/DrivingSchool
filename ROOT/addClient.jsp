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

  String fName = request.getParameter("firstName");
  String mName = request.getParameter("middleName");
  String lName = request.getParameter("lastName");
  String registered = request.getParameter("registered");
  String birthYear = request.getParameter("birthYear");
  String birthMonth = request.getParameter("birthMonth");
  String birthDay = request.getParameter("birthDay");
  String provisionNum = request.getParameter("provisionNum");
  String assignedInstructor = request.getParameter("assignedInstructor");
  String requestedInstructor = request.getParameter("requestedInstructor");
  char sex = request.getParameter("sex").charAt(0);

  int rgstrd = -1;
  try{
	rgstrd = Integer.parseInt(registered);
  } catch(Exception e){
	throw e;
  }
  int provNum = -1;
  try {
	  provNum = Integer.parseInt(provisionNum);
  } catch(Exception e) {
	  throw e;
  }
  int assndIns = -1;
  try {
	  assndIns = Integer.parseInt(assignedInstructor);
  } catch(Exception e) {
	  throw e;
  }
  int rqstIns = -1;
  try {
	  rqstIns = Integer.parseInt(requestedInstructor);
  }
  catch(Exception e) {
	  throw e;
  }

  if(!dbcontroller.addClient(fName, mName, lName, rgstrd, birthYear, birthMonth, birthDay, provNum, assndIns, rqstIns, sex)){
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
