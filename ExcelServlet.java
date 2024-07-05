import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class ExcelServlet extends HttpServlet{
public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{

try{
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","mca6");
Statement stmt=c.createStatement();
ResultSet rs=stmt.executeQuery("SELECT STUD_ID,STUD_NAME,MATHS,SCIENCE,ENGLISH,HINDI,  ARTS,(MATHS + SCIENCE + ENGLISH + HINDI + ARTS) AS TOTAL,(MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 AS Percentage,CASE WHEN (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 < 33 THEN 'Fail'  WHEN (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 >= 33 AND (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 < 45 THEN '3rd Div' WHEN (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 >= 45 AND (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 < 60 THEN '2nd Div' WHEN (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 >= 60 THEN '1st Div'END AS Grade FROM cbse");

ResultSetMetaData rd=rs.getMetaData();
res.setContentType("application/vnd.ms-excel");
res.setHeader("Content-Disposition","attachment;filename=totalRec.xls");//Content-Disposition is header use when send data on excel

PrintWriter out=res.getWriter();
int count=rd.getColumnCount();
for(int i=1;i<=count;i++){

out.print(rd.getColumnName(i)+"\t");
}

out.println("");
int x=2;
while(rs.next()){

out.print(rs.getInt(1)+"\t");
out.print(rs.getString(2)+"\t");
out.print(rs.getInt(3)+"\t");
out.print(rs.getInt(4)+"\t");
out.print(rs.getInt(5)+"\t");
out.print(rs.getInt(6)+"\t");
out.print(rs.getInt(7)+"\t");
out.print(rs.getInt(8)+"\t");
out.print(rs.getString(9)+"\t");
out.print(rs.getString(10)+"\t");

out.println("");//next line
}
}
catch(Exception e){
e.printStackTrace();
}
}
}