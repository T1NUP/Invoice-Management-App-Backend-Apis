package com.higradius;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void delete(String arrDid[])throws Exception
    {
    	
    	String JDBC_DRIVER= "com.mysql.jdbc.Driver";
    	String URL= "jdbc:mysql://127.0.0.1:3306/h2h_internship";
    	String USERNAME="root";
    	String PASSWORD="1806405";
    	Connection conn = null;
		Statement stmt = null;
		Integer i;
    	
    	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		
		conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
		stmt= conn.createStatement();
		
		System.out.println(arrDid.length);
		
		String sql = "delete from invoice_details where doc_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		for(i=0;i<arrDid.length;i++)
		{
			ps.setLong(1, Long.parseLong(arrDid[i]) );
			
			ps.addBatch();
		}
		
		ps.executeBatch();
		
		//stmt.executeUpdate(sql);
    	
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		InputStream in = new BufferedInputStream(request.getInputStream());
        
		int bytesRead = 0;
		byte[] contents = new byte[1024];
		String strFileContents=""; 
		while((bytesRead = in.read(contents)) != -1) { 
		    strFileContents += new String(contents, 0, bytesRead);              
		}
		
		 
		 //InvoiceModel model= new InvoiceModel();	
		   JsonObject jsonObject = new JsonParser().parse(strFileContents).getAsJsonObject();
	 	   String arr= jsonObject.get("docIds").toString().replace("[", "").replace("]", "");
	 	   //System.out.println(arr);
	 	   String b[]= arr.split(",");
	 	   
	 	   try {
	 	   delete(b);
	 	   }
	 	   catch(Exception e)
	 	   {
	 		   e.printStackTrace();
	 	   }
//		 String b[]= strFileContents.split("\"");
//		 for(int i=0;i<b.length;i++)
//		 System.out.print(b[i]);
	
	}

}
