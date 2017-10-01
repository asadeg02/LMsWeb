package com.gcit.lms.web;

import java.util.List;
 
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.service.BorrowerService;

/**
 * Servlet implementation class BorrowerServlet
 */
@WebServlet({ "/returnbook", "/isvalid", "/showbooks" , "/isvalid1","/showbooks2", "/checkout" })
public class BorrowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BorrowerService borrowerService = new BorrowerService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrowerServlet() {
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
		String redirectUrl = "/borrower.jsp";
		switch (reqUrl) {
		case "/returnbook":
			redirectUrl = returnbook(request, response, redirectUrl);
			break;
			
		case "/isvalid":

			// System.out.println("here!");
			redirectUrl = isvalid(request, response, redirectUrl);
			break;

		case "/showbooks":

			// System.out.println("here!");
			redirectUrl = showbooks(request, response, redirectUrl);
			break;
			
		case "/isvalid1":

			// System.out.println("here!");
			redirectUrl = isvalid1(request, response, redirectUrl);
			break;
			
		case "/showbooks2":

			// System.out.println("here!");
			redirectUrl = showbooks2(request, response, redirectUrl);
			break;
			
		case "/checkout":

			// System.out.println("here!");
			redirectUrl = checkout(request, response, redirectUrl);
			break;


		default:
			break;
		}

		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);

	}

	private String isvalid(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {

		String message = "you card number is valid";

		if (request.getParameter("cardNo") != null && !request.getParameter("cardNo").isEmpty()) {
			try {

				if (!borrowerService.isValid(Integer.parseInt(request.getParameter("cardNo")))) {

					message = "Card number does not exist";
					redirectUrl = "/borrower.jsp";
				} else {

					message = "you card number is valid";
					borrowerService.readBranches(Integer.parseInt(request.getParameter("cardNo")));
					redirectUrl = "/borrowermenu.jsp";
					// System.out.println("here2");
				}
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.print("salam");
			message = "Card number can not be empty";
			redirectUrl = "/borrower.jsp";
		}

		//request.setAttribute("cardNo", request.getParameter("cardNo"));
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}

	private String showbooks(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {

		String message = null;

		if (request.getParameter("branchIds") != null && !request.getParameter("branchIds").isEmpty()) {
			try {

				int cardNo = Integer.parseInt(request.getParameter("cardNo"));
				int branchId = Integer.parseInt(request.getParameter("branchIds"));
				System.out.println("sepideh");
				List<Borrower> borrowers = borrowerService.readBorrowerByPK(cardNo);
				message = "Hello dear " + borrowers.get(0).getName();
				borrowerService.populateBooks(cardNo, branchId);
				System.out.println("sepideh");

				redirectUrl = "booklist.jsp";

			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.print("salam");
			message = "You have to choose a library branch";
			redirectUrl = "/borrowermenu.jsp";
		}

		// request.setAttribute("cardNo", request.getParameter("cardNo"));
		request.setAttribute("statusMessage", message);
		return redirectUrl;

	}
	
	

	private String returnbook(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {

		String message = null;

		if (request.getParameter("bookIds") != null && !request.getParameter("bookIds").isEmpty()) {
			int cardNo = Integer.parseInt(request.getParameter("cardNo"));
			int bookId = Integer.parseInt(request.getParameter("bookIds"));
			int branchId = Integer.parseInt(request.getParameter("branchId"));
			
			
			try {
				if(borrowerService.isAlreadyCheckedOut(cardNo, branchId, bookId)) {
					message = "You have already returned the book!";
					redirectUrl = "/borrowermenu.jsp";
					System.out.println("Delaram1");
				}
				 
				else {
				System.out.println("delaram");
				borrowerService.checkOut(cardNo, branchId, bookId);
				borrowerService.updateBookCopies1(branchId, bookId);
				message = "You have Successfully Returned The book";

				redirectUrl = "/borrowermenu.jsp";
				// System.out.println("here2");
				}

			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			message = "You have to pick a book in order to successfully check out";
			redirectUrl = "booklist.jsp";
		}

		request.setAttribute("statusMessage", message);
		return redirectUrl;

	}
	
	//this is for returning book 
	private String isvalid1(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {

		System.out.println("khodafezi");
		String message = "you card number is valid";

		if (request.getParameter("cardNo") != null && !request.getParameter("cardNo").isEmpty()) {
			try {

				if (!borrowerService.isValid(Integer.parseInt(request.getParameter("cardNo")))) {

					message = "Card number does not exist";
					redirectUrl = "/return.jsp";
				} else {

					message = "you card number is valid";
					borrowerService.readBranches(Integer.parseInt(request.getParameter("cardNo")));
					redirectUrl = "/branchmenu.jsp";
					// System.out.println("here2");
				}
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.print("salam");
			message = "Card number can not be empty";
			redirectUrl = "/return.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	
	private String showbooks2(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {

		String message = null;

		if (request.getParameter("branchIds") != null && !request.getParameter("branchIds").isEmpty()) {
			try {

				int cardNo = Integer.parseInt(request.getParameter("cardNo"));
				int branchId = Integer.parseInt(request.getParameter("branchIds"));
				 
				List<Book> books = borrowerService.readBookByBranch(branchId);
				List<Borrower> borrowers = borrowerService.readBorrowerByPK(cardNo);
				message = "Hello dear " + borrowers.get(0).getName();
				BorrowerService.books = books;
				 

				redirectUrl = "bookmenu.jsp";

			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			//System.out.print("salam");
			message = "You have to choose a library branch";
			redirectUrl = "/branchmenu.jsp";
		}

		// request.setAttribute("cardNo", request.getParameter("cardNo"));
		request.setAttribute("statusMessage", message);
		return redirectUrl;

	}
	
	private String checkout(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {

		String message = null;

		if (request.getParameter("bookIds") != null && !request.getParameter("bookIds").isEmpty()) {
			int cardNo = Integer.parseInt(request.getParameter("cardNo"));
			int bookId = Integer.parseInt(request.getParameter("bookIds"));
			int branchId = Integer.parseInt(request.getParameter("branchIds"));
			 
			
			
			try {
				 
				 
				 
				borrowerService.checkout1(cardNo, branchId, bookId);
				System.out.println("delaram3");
				borrowerService.updateBookCopies2(branchId, bookId);
				System.out.println("delaram4");
				message = "You have Successfully checked out the book";
				redirectUrl = "/branchmenu.jsp";
				// System.out.println("here2");
				 

			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			message = "You have to pick a book in order to successfully check out";
			redirectUrl = "bookmenue.jsp";
		}

		request.setAttribute("statusMessage", message);
		return redirectUrl;

	}
	
	
	
	

}
