import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class EamcetServlet extends HttpServlet
{
	Connection con;
	Statement st;
	ResultSet rs;
	
	public void init()
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","oracle");
			
			st=con.createStatement();
			
		}//try
		catch(Exception e)
		{
			System.out.println(e);
		}//catch
	}//init
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		try{
			String ss=null;
			String s=req.getParameter("htno");
			String s2="select * from students where rno="+Integer.parseInt(s);
			rs=st.executeQuery(s2);
			
			PrintWriter out=res.getWriter();
			while(rs.next())
			{
				out.println(rs.getInt(1));
				out.println(rs.getString(2));
				out.println(rs.getInt(3));
				
				//ss=rs.getInt(1)+"---"+rs.getString(2)+"---"+rs.getInt(3);
				
			}
			//PrintWriter out=res.getWriter();
			//out.println(ss);
		}//try2
		catch(Exception e1)
		{
			System.out.println(e1);
		}//catch2
	}//doGet
	public void destroy()
	{
		try{
			con.close();
		}
		catch(Exception e2)
		{
			System.out.println(e2);
		}
	}
}