import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class SendRedirectSignIn extends HttpServlet{
public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{


res.setContentType("text/html");

PrintWriter out=res.getWriter();
out.println("<html><body>");
out.println("before");
String name=req.getParameter("name");
String pass=req.getParameter("pass");

try{
Class.forName("oracle.jdbc.driver.OracleDriver");

Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","mca6");


PreparedStatement ps=c.prepareStatement("insert into login values(?,?)");
ps.setString(1,name);
ps.setString(2,pass);
int x=ps.executeUpdate();
/*
Statement s=c.createStatement();
ResultSet rs=s.executeQuery("insert into login values(id='"+name+"', pass='"+pass+"')");
 
*/
if(x>0){
res.sendRedirect("index.html");

}
else{

res.sendRedirect("error.html");
}

}
catch(Exception e){out.println(e);}
out.println("</body></html>");
}
}


//http://localhost:7001/s3/login.html