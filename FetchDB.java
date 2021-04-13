package com.higradius;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FetchDB {
	
	public List<InvoiceModel> getFromDB()throws Exception
	{
		String JDBC_DRIVER= "com.mysql.jdbc.Driver";
		String URL= "jdbc:mysql://127.0.0.1:3306/h2h_internship?rewriteBatchedStatements=true";
		String USERNAME="root";
		String PASSWORD="1806405";
		
		Connection conn = null;
		Statement stmt = null;
		
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
		stmt= conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("select * from invoice_details");
		conn.setAutoCommit(false);
		//System.out.println("Connection Established!!");
		
		List<InvoiceModel> data= new ArrayList<>();
		
		while(rs.next())
		{
			
			InvoiceModel obj= new InvoiceModel();
			
			//Adding "" after each data to handle null exceptions
			
			
			obj.setBusiness_code(rs.getString("business_code"));
			obj.setCust_number(rs.getString("cust_number"));
			obj.setName_customer(rs.getString("name_customer"));		
			obj.setClear_date((rs.getDate("clear_date")+"").toString());
			obj.setBuisness_year(rs.getString("business_year"));
			obj.setDoc_id(rs.getLong("doc_id"));
			obj.setPosting_date((rs.getDate("posting_date")+"").toString());
			obj.setDocument_create_date((rs.getDate("document_create_date")+"").toString());
			obj.setDocument_create_date_1((rs.getDate("document_create_date_1")+"").toString());
			obj.setDue_in_date((rs.getDate("due_in_date")+"").toString());
			obj.setInvoice_currency(rs.getString("invoice_currency"));
			obj.setDocument_type(rs.getString("document_type"));
			obj.setPosting_id(rs.getInt("posting_id"));
			obj.setArea_business(rs.getString("area_business")+"");
			obj.setTotal_open_amount(rs.getDouble("total_open_amount"));
			obj.setBaseline_create_date((rs.getDate("baseline_create_date")+"").toString());
			obj.setCust_payment_terms(rs.getString("cust_payment_terms"));
			obj.setInvoice_id(rs.getLong("invoice_id"));
			obj.setIsOpen(rs.getInt("isOpen"));
			obj.setNotes(rs.getString("notes")+"");
		
			
			data.add(obj);
			
		}
		
		return data;
		
	}

}
