  // stringbuffer to hold final content
  StringBuffer content = new StringBuffer();;
  content.append("<br/><table>");

  // asking dbcontroller to list the employee table
  Vector<String> vecResult = dbcontroller.FindAllEmployees();
   if (vecResult == null) {
     content.append("Query result is null!");
   }
   content.append("<tr><th>FNAME</th><th>MNAME</th><th>LNAME</th>" +
   "<th>SSN</th><th>BDATE</th><th>ADDRESS</th><th>SEX</th><th>SALARY</th>" + 
   "<th>SUPERSSN</th><th>DNO</th></tr>");
  if (vecResult != null && vecResult.size() > 0) {
    for (int i = 0; i < vecResult.size(); i++) {
      String row = vecResult.get(i);
      String[] detail = row.split("##");
      if (detail.length != 5) {
        //break;
      }
      content.append(
          "<tr id=\"tablerow_" + i + "\">");
      content.append(
          "<td class=\"postlist\"><a href=\"javascript:void(0)\" " +
          "\"><b>" + detail[0] + "</b></a></td>");
      content.append(
          "<td><a href=\"javascript:void(0)\" >" +
          "<b>" + detail[1] + "</b></a></td>");
      content.append("<td>" + detail[2] + "</td>" +
                     "<td>" + detail[3] + "</td>" +
                     "<td>" + detail[4] + "</td>" +
"<td>" + detail[5] + "</td>" +
"<td>" + detail[6] + "</td>" +
"<td>" + detail[7] + "</td>" +
"<td>" + detail[8] + "</td>" +
"<td>" + detail[9] + "</td>");
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