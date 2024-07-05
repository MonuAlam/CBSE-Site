import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class Modify extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.println("<html><body>");
		int id=Integer.parseInt(req.getParameter("id"));
		try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","mca6");
						
			PreparedStatement ps1=c.prepareStatement("select * from cbse where STUD_ID=?");
			ps1.setInt(1,id);
			
			ResultSet rs=ps1.executeQuery();
			
			if(rs.next()){
				out.println("<h1>Record of Student Roll Number:("+id+")</h1>");
		       out.println("<form action='modify1' method='post'>");
	             out.println("Roll No:<input type='number' name='id' value="+rs.getInt(1)+"><br>");
				out.println("Name: <input type='text' name='name' value="+rs.getString(2)+"><br>");
				out.println("Maths: <input type='number' name='maths' value="+rs.getInt(3)+"><br>");
				out.println("Science: <input type='number' name='science' value="+rs.getInt(4)+"><br>");
                out.println("English: <input type='number' name='english' value="+rs.getInt(5)+"><br>");
                 out.println("Hindi: <input type='number' name='hindi' value="+rs.getInt(6)+"><br>");
				 out.println("Arts: <input type='number' name='arts' value="+rs.getInt(7)+"></br>");

				out.println("<br><br><input type='submit' value='submit'><br><br>");


	 			/*
				out.println("Roll No: "+rs.getInt(1)+"<br>");
				out.println("Maths: "+rs.getString(3)+"<br>");
				out.println("Science: "+rs.getString(4)+"<br>");
				out.println("English: "+rs.getString(5)+"<br>");
				out.println("Hindi: "+rs.getString(6)+"<br>");
				out.println("Arts: "+rs.getString(7)+"<br><br><br>");				
				*/
				
			}
			else{
				
				out.println("Record not found for "+id+"Roll Number");
			}
		}
		catch(Exception e){out.println(e);}
		out.println("<a href='index.html'>Home Page</a>	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;");
		//out.println("<a href='modify1.html'>Modify Record</a>");

		out.println("</body></html>");
	}
}