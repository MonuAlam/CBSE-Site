import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;


public class Total extends HttpServlet{

public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{

res.setContentType("text/html");
PrintWriter out=res.getWriter();

out.println("<html><body>");
try{

Class.forName("oracle.jdbc.driver.OracleDriver");
Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","mca6");

PreparedStatement ps=c.prepareStatement("SELECT STUD_ID,STUD_NAME,MATHS,SCIENCE,ENGLISH,HINDI,  ARTS,(MATHS + SCIENCE + ENGLISH + HINDI + ARTS) AS TOTAL,(MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 AS Percentage,CASE WHEN (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 < 33 THEN 'Fail'  WHEN (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 >= 33 AND (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 < 45 THEN '3rd Div' WHEN (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 >= 45 AND (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 < 60 THEN '2nd Div' WHEN (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 >= 60 THEN '1st Div'END AS Grade FROM cbse");
ResultSet rs=ps.executeQuery();

PreparedStatement ps1=c.prepareStatement("select count(Stud_id) from cbse");
ResultSet rs1=ps1.executeQuery();

ResultSetMetaData rsmd=rs.getMetaData();
out.println("<table border=2 width=1350>");
out.println("<tr>");
for(int i=1;i<=rsmd.getColumnCount();i++){

out.println("<th>"+rsmd.getColumnName(i)+"</th>");
}
while(rs.next()){
out.println("<tr>");
out.println("<td>"+rs.getInt(1)+"</td>");
out.println("<td>"+rs.getString(2)+"</td>");
out.println("<td>"+rs.getInt(3)+"</td>");
out.println("<td>"+rs.getInt(4)+"</td>");
out.println("<td>"+rs.getInt(5)+"</td>");
out.println("<td>"+rs.getInt(6)+"</td>");
out.println("<td>"+rs.getInt(7)+"</td>");
out.println("<td>"+rs.getInt(8)+"</td>");
out.println("<td>"+rs.getString(9)+" %</td>");

if(rs.getString(10).equals("Fail")){
out.println("<td style='background-color:red;'>"+rs.getString(10)+"</td>");

}
else{
out.println("<td>"+rs.getString(10)+"</td>");
}
out.println("</tr>");
}
out.println("<tr>"+"<td><b>Total_Number_Of_Students</b></td>");
if(rs1.next()){
out.println("<td colspan='9'><b>"+rs1.getInt(1)+"</b></td>");
out.println("</tr>");
}
}
catch(Exception e){out.println(e);}

out.println("</table>");
out.println("<br><br><br><a href='index.html'>Home Page</a>");
out.println("&nbsp;&nbsp;&nbsp;&nbsp<a href='excel'>Download in Excel</a>");

out.println("</body></html>");

res.setHeader("Refresh","3;index.html");//Refresh every 4 sec and redirect to index

}

}