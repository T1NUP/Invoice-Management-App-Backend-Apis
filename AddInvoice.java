package com.higradius;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class AddInvoice
 */
@WebServlet("/AddInvoice")
public class AddInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddInvoice() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void addInvoice(InvoiceModel data)throws Exception
    {
    	String JDBC_DRIVER= "com.mysql.jdbc.Driver";
    	String URL= "jdbc:mysql://127.0.0.1:3306/h2h_internship?";
    	String USERNAME="root";
    	String PASSWORD="1806405";
    	Connection conn = null;
		Statement stmt = null;
    	
    	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		
		conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
		stmt= conn.createStatement();
		
		String sql = "insert into invoice_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
		DateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date jdt; 
		java.sql.Date sdt;
		System.out.print(data.getBusiness_code().replace("\"", ""));
		
		if((data.getBusiness_code().length())!=0)
		{
		ps.setString(1, data.getBusiness_code());
		}
		else
			ps.setNull(1, java.sql.Types.VARCHAR);
		
		if((data.getCust_number().length())!=0)
		{
		ps.setString(2, data.getCust_number());
		}
		else
			ps.setNull(2, java.sql.Types.VARCHAR);
		
		if((data.getName_customer().length())!=0)
		{
		ps.setString(3, data.getName_customer());
		}
		else
			ps.setNull(3, java.sql.Types.VARCHAR);
		
		if(!(data.getClear_date().equals("null")))
		{
		jdt = formatter1.parse(data.getClear_date());
		sdt = new java.sql.Date(jdt.getTime());
		ps.setDate(4, sdt);
		//ps.setDate(4, Date.valueOf(im.getClear_date()));
		}
		else
			ps.setNull(4, java.sql.Types.DATE);	
		
		if((data.getBuisness_year().length())!=0)
		{
		ps.setInt(5,Integer.parseInt(data.getBuisness_year().toString().substring(0,4)));
		}
		else
			ps.setNull(5, java.sql.Types.INTEGER);
//		jdt = formatter1.parse(data.getBuisness_year());
//		sdt = new java.sql.Date(jdt.getTime());
//		ps.setInt(5, jdt.getYear() );
		//ps.setInt(5, Math.round(Float.parseFloat(data.getBuisness_year())));
		
		ps.setLong(6, data.getDoc_id());
		
		if((data.getPosting_date().length())!=0)
		{
		jdt = formatter1.parse(data.getPosting_date());
		sdt = new java.sql.Date(jdt.getTime());
		ps.setDate(7, sdt);
		}
		else
			ps.setNull(7, java.sql.Types.DATE);
			
		if((data.getDocument_create_date().length())!=0)
		{
		jdt = formatter1.parse(data.getDocument_create_date());
		sdt = new java.sql.Date(jdt.getTime());
		ps.setDate(8, sdt);
		}
		else
			ps.setNull(8, java.sql.Types.DATE);
		
		if((data.getDocument_create_date_1().length())!=0)
		{
		jdt = formatter1.parse(data.getDocument_create_date_1());
		sdt = new java.sql.Date(jdt.getTime());
		ps.setDate(9, sdt);
		}
		else
			ps.setNull(9, java.sql.Types.DATE);
		
		if((data.getDue_in_date().length())!=0)
		{
		jdt = formatter1.parse(data.getDue_in_date());
		sdt = new java.sql.Date(jdt.getTime());
		ps.setDate(10, sdt);
		}
		else
			ps.setNull(10, java.sql.Types.DATE);
			
		
		if((data.getInvoice_currency().length())!=0)
		ps.setString(11, data.getInvoice_currency());
		else
			ps.setNull(11, java.sql.Types.VARCHAR);
		
		if((data.getDocument_type().length())!=0)
		ps.setString(12, data.getDocument_type());
		else
			ps.setNull(12, java.sql.Types.CHAR);
		
		if((data.getPosting_id())!=0)
		ps.setInt(13, data.getPosting_id());
		else
			ps.setNull(13, java.sql.Types.INTEGER);
		
		if(!(data.getArea_business().length()==0))
		{
		ps.setString(14, data.getArea_business());
		}
		else
			ps.setNull(14, java.sql.Types.VARCHAR);
		
		ps.setDouble(15, data.getTotal_open_amount());
		
		if((data.getBaseline_create_date()).length()!=0)
		{
		jdt = formatter1.parse(data.getBaseline_create_date());
		sdt = new java.sql.Date(jdt.getTime());
		ps.setDate(16, sdt);
		}
		else
			ps.setNull(16, java.sql.Types.DATE);
		
		if(data.getCust_payment_terms().length()!=0)
		ps.setString(17, data.getCust_payment_terms());
		else
			ps.setNull(17, java.sql.Types.VARCHAR);
		
		if(data.getInvoice_id()!=null)
		{
			ps.setLong(18, data.getInvoice_id());
		}
		else
			//ps.setLong(18, null);
			ps.setNull(18, java.sql.Types.BIGINT);
		
		ps.setInt(19, data.getIsOpen());
		
		if((data.getNotes()).length()!=0)
		{
			ps.setString(20, data.getNotes());
		}
		else
		 ps.setNull(20, java.sql.Types.VARCHAR);
		
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

		InputStream in = new BufferedInputStream(request.getInputStream());
        
		int bytesRead = 0;
		byte[] contents = new byte[1024];
		String strFileContents=""; 
		while((bytesRead = in.read(contents)) != -1) { 
		    strFileContents += new String(contents, 0, bytesRead);              
		}
		
		 
		 InvoiceModel model= new InvoiceModel();	
		 JsonObject jsonObject = new JsonParser().parse(strFileContents).getAsJsonObject();
		 
		 	 model.setBusiness_code(jsonObject.get("business_code").toString().replace("\"", ""));
			 model.setCust_number(jsonObject.get("cust_number").toString().replace("\"", ""));
			 model.setName_customer(jsonObject.get("name_customer").toString().replace("\"", ""));
			 model.setClear_date(jsonObject.get("clear_date").toString().replace("\"", ""));
			 model.setBuisness_year(jsonObject.get("buisness_year").toString().replace("\"", ""));
			 model.setDoc_id(Long.parseLong(jsonObject.get("doc_id").toString().replace("\"", "")));
			 model.setPosting_date(jsonObject.get("posting_date").toString().replace("\"", ""));
			 model.setDocument_create_date(jsonObject.get("document_create_date").toString().replace("\"", ""));
			 model.setDocument_create_date_1(jsonObject.get("document_create_date_1").toString().replace("\"", ""));
			 model.setDue_in_date(jsonObject.get("due_in_date").toString().replace("\"", ""));
			 model.setInvoice_currency(jsonObject.get("invoice_currency").toString().replace("\"", ""));
			 model.setDocument_type(jsonObject.get("document_type").toString().replace("\"", ""));
			 model.setPosting_id(Integer.parseInt((jsonObject.get("posting_id").toString().replace("\"", ""))));
			 model.setArea_business(jsonObject.get("area_business").toString().replace("\"", ""));
			 model.setTotal_open_amount(Double.parseDouble(jsonObject.get("total_open_amount").toString().replace("\"", "")));
			 model.setBaseline_create_date(jsonObject.get("baseline_create_date").toString().replace("\"", ""));
			 model.setCust_payment_terms(jsonObject.get("cust_payment_terms").toString().replace("\"", ""));
			 model.setInvoice_id(Long.parseLong(jsonObject.get("invoice_id").toString().replace("\"", "")));
			 model.setIsOpen(Integer.parseInt(jsonObject.get("isOpen").toString().replace("\"", "")));
			 model.setNotes(jsonObject.get("notes").toString().replace("\"", ""));
		 
		 
		 System.out.println(model.getClear_date()+""+model.getNotes());
		 try {
		 addInvoice(model);
		 }
		 catch(Exception e)
		 {
			 response.setStatus(400);
			 e.printStackTrace();
		 }
		 response.setStatus(200);
	}
	
	

}
