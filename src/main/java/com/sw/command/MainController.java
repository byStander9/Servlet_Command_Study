package com.sw.command;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainController
 */
@WebServlet("*.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request, response);
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String com = uri.substring(path.length());
		
		String name = null;
		String category = null;
		String price = null;
		MemberDto mdto = new MemberDto();
		
		Service service = null;
		String resultPage = null;
		int returnVal = 0;
		ResultSet resultSet = null;
		HttpSession session = request.getSession();
		
		if(com.equals("/insert.do")) {
			name = request.getParameter("name");
			category = request.getParameter("category");
			price = request.getParameter("price");
			mdto.setName(name);
			mdto.setCategory(category);
			mdto.setPrice(price);
			
			service = new InsertProdImpl();
			returnVal = service.insertProd(mdto);
			
			resultPage = "insertResult.jsp";
			if(returnVal == 1) {
				session.setAttribute("insertResult", "Insert Success");
			} else {
				session.setAttribute("insertResult", "Insert Fail");
			}
		} else if(com.equals("/searchName.do")) {
			name = request.getParameter("name");
			
			service = new SearchProdImpl();
			resultSet = service.searchProd("name", name);
			
			resultPage = "searchResult.jsp";
			session.setAttribute("searchedData", resultSet);
			
		} else if(com.equals("/searchCategory.do")) {
			category = request.getParameter("category");
			
			service = new SearchProdImpl();
			resultSet = service.searchProd("category", name);
			
			resultPage = "searchResult.jsp";
			session.setAttribute("searchedData", resultSet);
			
		} else if(com.equals("/changePrise.do")) {
			
		}
		
		response.sendRedirect(resultPage);
	}
}
