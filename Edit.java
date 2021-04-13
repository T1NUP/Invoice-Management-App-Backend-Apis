package com.higradius;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void editInvoice(Long docId, Double amt, String notes)throws Exception 
    {
    	
    	String JDBC_DRIVER= "com.mysql.jdbc.Driver";
    	String URL= "jdbc:mysql://127.0.0.1:3306/h2h_internship";
    	String USERNAME="root";
    	String PASSWORD="1806405";
    	Connection conn = null;
		Statement stmt = null;
    	
    	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		
		conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
		//stmt= conn.createStatement();
		
		String sql = "update invoice_details set total_open_amount=?,"
				+ "notes=? where doc_id="+docId+";";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setDouble(1, amt);
		
		if(notes.length()!=0)
		ps.setString(2, notes);
		else
			ps.setNull(2, java.sql.Types.VARCHAR);
		
		ps.execute();
		
		ps.close();
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
		//doGet(request, response);
		
		InputStream in = new BufferedInputStream(request.getInputStream());
        
		int bytesRead = 0;
		byte[] contents = new byte[1024];
		String strFileContents=""; 
		while((bytesRead = in.read(contents)) != -1) { 
		    strFileContents += new String(contents, 0, bytesRead);              
		}
		
		 
		 InvoiceModel model= new InvoiceModel();	
		 JsonObject jsonObject = new JsonParser().parse(strFileContents).getAsJsonObject();
		 
		 String notes= jsonObject.get("notes").toString().replace("\"", "");
		 Double amt= Double.parseDouble(jsonObject.get("total_open_amount").toString().replace("\"", ""));
		 Long docId= Long.parseLong(jsonObject.get("doc_id").toString().replace("\"", ""));
		 
		 try {
			 editInvoice(docId,amt,notes);
		 }catch(Exception e) {
			 response.setStatus(400);
			 e.printStackTrace();
		 }
	}

}
