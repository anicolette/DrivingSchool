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

  String empIDstr = request.getParameter("empID");
  String role = request.getParameter("role");
  String officeNoStr = request.getParameter("officeNo");
  
  int empID = -1;
  try {
	  empID = Integer.parseInt(empIDstr);
  } catch(Exception e) {
	  throw e;
  }
  int officeNo = -1;
  try {
	  officeNo = Integer.parseInt(officeNoStr);
  } catch(Exception e) {
	  throw e;
  }

  if(!dbcontroller.deleteRole(empID,role,officeNo)){
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
