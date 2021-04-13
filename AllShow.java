package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class AllShow
 */
@WebServlet("/AllShow")
public class AllShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllShow() {
        super();
        // TODO Auto-generated constructor stub
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
		Integer first= Integer.parseInt(request.getParameter("top"));
		Integer last= Integer.parseInt(request.getParameter("down"));
	
//		response.setStatus(200);
		
		
		try {
			FetchDB ob= new FetchDB();
			List<InvoiceModel> records= null;
			records= ob.getFromDB().subList(first, last);
			String data="";
					
			
			Gson gson = new Gson();
			data = gson.toJson(records);
			out.print(data);
			//System.out.println("ALIVE");
			//System.out.println(.get(0).getNotes()+"mm");
			response.setStatus(200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
