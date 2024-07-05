import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class Add extends HttpServlet{

public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{

res.setContentType("text/html");
PrintWriter out=res.getWriter();
out.println("<html><body>");
int id=Integer.parseInt(req.getParameter("id"));
String name=req.getParameter("name");
int maths=Integer.parseInt(req.getParameter("maths"));
int science=Integer.parseInt(req.getParameter("science"));
int english=Integer.parseInt(req.getParameter("english"));
int hindi=Integer.parseInt(req.getParameter("hindi"));
int arts=Integer.parseInt(req.getParameter("arts"));


try{

Class.forName("oracle.jdbc.driver.OracleDriver");
Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","System","mca6");

/*
Statement s=c.createStatement();
String s1="insert into cbse values('"+id+"','"+name+"','"+maths+"','"+science+"','"+english+"','"+hindi+"','"+arts+"')";
int x=s.executeUpdate(s1);
*/

PreparedStatement ps=c.prepareStatement("insert into cbse values(?,?,?,?,?,?,?)");
ps.setInt(1,id);
ps.setString(2,name);
ps.setInt(3,maths);
ps.setInt(4,science);
ps.setInt(5,english);
ps.setInt(6,hindi);
ps.setInt(7,arts);

int x=ps.executeUpdate();

if(x>0){
out.println("<h1>Record Added Successfully</h1>");
out.println("Roll No: "+id+"<br>");
out.println("Name: "+name+"<br>");
out.println("Maths: "+maths+"<br>");
out.println("Science: "+science+"<br>");
out.println("English: "+english+"<br>");
out.println("Hindi: "+hindi+"<br>");
out.println("Arts: "+arts+"<br><br><br>");
}

}
catch(Exception e){out.println(e);}
out.println("<a href='index.html'>Home Page</a>");
out.println("</body></html>");
res.setHeader("Refresh","3;index.html");//Refresh every 4 sec and redirect to index

}
}