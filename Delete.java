import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class Delete extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.println("<html><body>");
		int id=Integer.parseInt(req.getParameter("id"));
		try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","mca6");
			
			PreparedStatement ps=c.prepareStatement("delete from cbse where STUD_ID=?");
			ps.setInt(1,id);
			
			//int x=ps.executeUpdate();
			
			PreparedStatement ps1=c.prepareStatement("select * from cbse where STUD_ID=?");
			ps1.setInt(1,id);
			
			ResultSet rs=ps1.executeQuery();
			
			if(rs.next()){
				out.println("<h1>Record Deleted Successfully Student Roll Number:("+id+")</h1>");
				//while(rs.next()){
				
				out.println("Roll No: "+rs.getInt(1)+"<br>");
				out.println("Name: "+rs.getString(2)+"<br>");
				out.println("Maths: "+rs.getString(3)+"<br>");
				out.println("Science: "+rs.getString(4)+"<br>");
				out.println("English: "+rs.getString(5)+"<br>");
				out.println("Hindi: "+rs.getString(6)+"<br>");
				out.println("Arts: "+rs.getString(7)+"<br><br><br>");
				
				int x=ps.executeUpdate();
				
				
			}
			else{
				
				out.println("Record not found for "+id+"Roll Number");
			}
		}
		catch(Exception e){out.println(e);}
		out.println("<a href='index.html'>Home Page</a>");
		out.println("</body></html>");
		res.setHeader("Refresh","3;index.html");//Refresh every 4 sec and redirect to index

	}
}