import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class Percentage extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.println("<html><body>");
		int id=Integer.parseInt(req.getParameter("id"));
		try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","mca6");
			
	PreparedStatement ps1=c.prepareStatement("SELECT STUD_ID,STUD_NAME,MATHS,SCIENCE,ENGLISH,HINDI,  ARTS,(MATHS + SCIENCE + ENGLISH + HINDI + ARTS) AS TOTAL,(MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 AS Percentage,CASE WHEN (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 < 33 THEN 'Fail'  WHEN (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 >= 33 AND (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 < 45 THEN '3rd Div' WHEN (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 >= 45 AND (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 < 60 THEN '2nd Div' WHEN (MATHS + SCIENCE + ENGLISH + HINDI + ARTS) * 0.2 >= 60 THEN '1st Div'END AS Grade FROM cbse where STUD_ID=?");

			ps1.setInt(1,id);
			
			ResultSet rs=ps1.executeQuery();
			
			if(rs.next()){
		    out.println("<!DOCTYPE html><html lang='en'><head>");
			out.println("<style> @import url(style.css);</style>");
			//out.println("<style>table{background-color:gray;}table>tr,th,td{border:2px solid black;}</style>");

			out.println("</head><body>");
	     //	out.println("<table width=1350 border=2");
				out.println("<table class='hello'>");

                out.println("<tr>");
                out.println("<th>Student ID</th>");
                out.println("<th>Student Name</th>");
                out.println("<th>Maths</th>");
                out.println("<th>Science</th>");
                out.println("<th>English</th>");
                out.println("<th>Hindi</th>");
                out.println("<th>Arts</th>");
                out.println("<th>Total Marks</th>");
                out.println("<th>Percentage</th>");
                out.println("<th>Grade</th>");
                out.println("</tr>");
				
				
                out.println("<tr>");
                out.println("<td>"+rs.getInt(1)+"</td>");
                out.println("<td>"+rs.getString(2)+"</td>");
                out.println("<td>"+rs.getInt(3)+"</td>");
                out.println("<td>"+rs.getInt(4)+"</td>");
                out.println("<td>"+rs.getInt(5)+"</td>");
                out.println("<td>"+rs.getInt(6)+"</td>");
                out.println("<td>"+rs.getInt(7)+"</td>");
                out.println("<td>"+rs.getInt(8)+"</td>");
                out.println("<td>"+rs.getInt(9)+"%</td>");
				
				if( rs.getString(10).equals("Fail") ){
                out.println("<td style='background-color:red;'>" +rs.getString(10)+ "</td>");
				}
				else{
				   out.println("<td>"+ rs.getString(10) +"</td>");

				}
                out.println("</tr>");
				out.println("</table>");
			}
			else{
				
				out.println("Record not found for Roll Number "+id);
			}
		}
		catch(Exception e){out.println(e);}
		
		out.println("</table>");
out.println("<br><br><br><a href='index.html'>Home Page</a>");

out.println("</body></html>");
res.setHeader("Refresh","3;index.html");//Refresh every 4 sec and redirect to index

	}
}