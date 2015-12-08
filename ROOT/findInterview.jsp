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

  String empIDstr = request.getParameter("empID");
  int empID = -1;
  try {
	  empID = Integer.parseInt(empIDstr);
  } catch (Exception e) {
	  throw e;
  }
  
  DatabaseController dbcontroller = new DatabaseController();
  // connect to backend database server via the databasecontroller, which
  // is a wrapper class providing necessary methods for this particular
  // application
  dbcontroller.Open();

  // writing the content on output/response page
  out.write("<h2>Timestamps for Employee for the next week</h2>");

  // stringbuffer to hold final content
  StringBuffer content = new StringBuffer();;
  content.append("<br/><table>");

  // asking dbcontroller to list the employee table
  Vector<String> vecResult = dbcontroller.findInterview(empID);
   if (vecResult == null) {
     content.append("Query result is null!");
   }
   content.append("<tr><th>INTERVIEWER ID</th><th>TIMESTAMP</th><th>CLIENT ID</th></tr>");
  if (vecResult != null && vecResult.size() > 0) {
    for (int i = 0; i < vecResult.size(); i++) {
      String row = vecResult.get(i);
      String[] detail = row.split("##");
      if (detail.length != 3) {
        //break;
      }
      content.append(
          "<tr id=\"tablerow_" + i + "\">");
      content.append(
          "<td>" + detail[0] + "</td><td>" + detail[1] + "</td><td>" + detail[2] + "</td>");
      content.append("</tr>");
    }
  }
  out.write(content.toString());

  // close the dbcontroller and relase all resources occupied by it.
  dbcontroller.Close();
%>
</div>
</body>
</html>
