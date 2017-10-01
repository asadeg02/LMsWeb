 package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Author;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/addAuthor", "/deleteAuthor", "/editAuthor", "/pageAuthors"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String redirectUrl = "/viewauthors.jsp";
		
		
		switch (reqUrl) {
		case "/deleteAuthor":
			redirectUrl = deleteAuthor(request, response, redirectUrl);
			break;
		case "/pageAuthors":
			redirectUrl = pageAuthors(request, response, redirectUrl);
			break;

		default:
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String redirectUrl = "/viewauthors.jsp";
		switch (reqUrl) {
		case "/addAuthor":
			redirectUrl = addAuthor(request, redirectUrl, false);
			break;
		case "/editAuthor":
			redirectUrl = addAuthor(request, redirectUrl, true);
			break;

		default:
			break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
		
	}

	private String addAuthor(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Author author = new Author();
		String message = "Author added Sucessfully";
		
		if (request.getParameter("authorName") != null && !request.getParameter("authorName").isEmpty()) {
			if(request.getParameter("authorName").length() > 45){
				message = "Author Name cannot be more than 45 chars";
				redirectUrl = "/addauthor.jsp";
			}else{
				author.setAuthorName(request.getParameter("authorName"));
//				String[] bookIds = request.getParameterValues("bookIds");
				if(editMode){
					author.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
				}
				try {
					adminService.saveAuthor(author);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else{
			message = "Author Name cannot be Empty";
			redirectUrl = "/addauthor.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	private String deleteAuthor(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "Author deleted Sucessfully";
		if(request.getParameter("authorId")!=null){
			Integer authorId = Integer.parseInt(request.getParameter("authorId"));
			Author author = new Author();
			author.setAuthorId(authorId);
			try {
				adminService.deleteAuthor(author);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Author deleted failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}
	
	private String pageAuthors(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("authors", adminService.readAuthors(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		
		return null;
	}
}
