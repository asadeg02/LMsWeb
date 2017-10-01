package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.service.LibrarianService;

/**
 * Servlet implementation class LibrarianServlet
 */
@WebServlet({ "/validateinput", "/updatebranch", "/getnoofcopies", "/addcopies" })
public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LibrarianService service = new LibrarianService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibrarianServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String redirectUrl = null;
		switch (reqUrl) {

		case "/validateinput":

			// System.out.println("here!");
			redirectUrl = validateinput(request, response, redirectUrl);
			break;

		case "/updatebranch":

			// System.out.println("here!");
			redirectUrl = updatebranch(request, response, redirectUrl);
			break;

		case "/getnoofcopies":

			// System.out.println("here!");
			redirectUrl = getnoofcopies(request, response, redirectUrl);
			break;
			
		case "/addcopies":

			// System.out.println("here!");
			redirectUrl = addcopies(request, response, redirectUrl);
			break;


		default:
			break;
		}

		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
	}

	private String validateinput(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {

		String message = null;

		if (request.getParameter("branchIds") != null && !request.getParameter("branchIds").isEmpty()) {

			redirectUrl = "librarian2.jsp";
			try {

				service.readBranchByPK(Integer.parseInt(request.getParameter("branchIds")));
				message = "You Have Chosen To Update The Branch With branch Id: "
						+ LibrarianService.chosenBranch.getBranchId() + " And Branch Name: "
						+ LibrarianService.chosenBranch.getBranchName();
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {

			message = "You have to choose a branch";
			redirectUrl = "librarian.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}

	private String updatebranch(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {

		redirectUrl = "librarian2.jsp";
		String message = null;

		LibraryBranch lb = new LibraryBranch();
		lb.setBranchId(LibrarianService.chosenBranch.getBranchId());
		// System.out.println("salam 1");

		if (request.getParameter("branchName") != null && !request.getParameter("branchName").isEmpty()) {
			lb.setBranchName(request.getParameter("branchName"));
			// System.out.println("salam 2");
		}
		if (request.getParameter("branchAddress") != null && !request.getParameter("branchAddress").isEmpty()) {
			lb.setBranchAddress(request.getParameter("branchAddress"));
			// System.out.println("salam 3");
		}

		System.out.println(lb.getBranchName());
		System.out.println(lb.getBranchAddress());

		if ((request.getParameter("branchAddress") == null || request.getParameter("branchAddress").isEmpty())
				&& (request.getParameter("branchName") == null || request.getParameter("branchName").isEmpty())) {

			redirectUrl = "updatelibrary.jsp";
			message = "You have not entered anything";
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		try {

			service.updateBranch(lb);
			LibrarianService.chosenBranch = lb;

			System.out.println("here");
			redirectUrl = "librarian2.jsp";
			message = "Successfully Updated!";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}

	// next method here
	private String getnoofcopies(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {

		String message = null;
		redirectUrl = null;

		if (request.getParameter("bookIds") != null && !request.getParameter("bookIds").isEmpty()) {

			// redirectUrl = "librarian2.jsp";
			try {

				int branchId = LibrarianService.chosenBranch.getBranchId();
				int bookId = Integer.parseInt(request.getParameter("bookIds"));
				service.readAllBookCopies(branchId, bookId);
				redirectUrl = "librarian3.jsp";

			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {

			message = "You have to choose a book";
			redirectUrl = "addcopies.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	
	// next method here
		private String addcopies(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
				throws ServletException, IOException {

			String message = null;
			redirectUrl = null;

			if (request.getParameter("noofcopies") != null && !request.getParameter("noofcopies").isEmpty()) {

				// redirectUrl = "librarian2.jsp";
				try {

					System.out.println("existing: " +LibrarianService.noOfCopies);
					int noOfCopies = Integer.parseInt(request.getParameter("noofcopies"));
					System.out.println("noofcopies: "+noOfCopies);
					int branchId = LibrarianService.chosenBranch.getBranchId();
					System.out.println("branch id: " +branchId);
					int bookId = Integer.parseInt(request.getParameter("bookId"));
					System.out.println("book id : " + bookId);
					service.updateBookCopies(branchId, bookId, noOfCopies);
					redirectUrl = "addcopies.jsp";

				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else {

				message = "You have to choose a book";
				redirectUrl = "librarian3.jsp";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}


}
