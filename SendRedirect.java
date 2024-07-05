import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class SendRedirect extends HttpServlet{
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

/*
PreparedStatement ps=c.prepareStatement("select * from login where id=? and pass=?");
ps.setString(1,name);
ps.setString(2,pass);
ResultSet rs=ps.executeQuery();
*/
Statement s=c.createStatement();
ResultSet rs=s.executeQuery("select * from login where id='"+name+"' and pass='"+pass+"'");


if(rs.next()){
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