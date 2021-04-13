package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public Integer count=0;
    public List<InvoiceModel> find(Long key)throws Exception
    {
    	
		String URL= "jdbc:mysql://127.0.0.1:3306/h2h_internship";
		String USERNAME="root";
		String PASSWORD="1806405";
		
		Connection conn = null;
		Statement stmt = null;
		
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
		stmt= conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("select * from invoice_details where doc_id  like '"+key+"%'");
		conn.setAutoCommit(false);
		
		count=0;
		List<InvoiceModel> data= new ArrayList<>();
		while(rs.next())
		{
			count++;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out= response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String lim= request.getParameter("limit");
		Integer limit= Integer.parseInt(lim);
		
		String s= request.getParameter("doc_Id");
		Long search= Long.parseLong(s);
		
		//
		response.setStatus(200);
		
		
		try {
			
			List<InvoiceModel> records= null;
			
			records= find(search);
			String data="";
			
			
			if(count==0)
				records= null;
			else if(count>limit)
				records= records.subList(limit-10, limit);
				
			
			
			Gson gson = new Gson();
			data = gson.toJson(records);
			out.print(data);
			//System.out.println(data);
			response.setStatus(200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(400);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
