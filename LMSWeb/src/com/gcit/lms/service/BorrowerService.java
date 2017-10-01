package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;

public class BorrowerService {

	public static List<LibraryBranch> branches;
	public static List<Book> books;

	public Utilities util = new Utilities();

	public void saveBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);

			if (borrower.getCardNo() != null) {
				bdao.updateBorrower(borrower);
			} else {
				bdao.saveBorrower(borrower);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void deleteBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.deleteBorrower(borrower);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<Borrower> readBorrower() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			List<Borrower> list = bdao.readAllBorrowers();
			System.out.println("there3");
			return list;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public boolean isValid(int cardNo) throws SQLException {

		boolean valid = false;

		System.out.println("Arezoo");
		List<Borrower> list = readBorrower();
		System.out.println("arezoo2");
		for (int i = 0; i < list.size(); i++) {
			if (cardNo == list.get(i).getCardNo()) {
				valid = true;
				break;
			}
		}
		return valid;

	}

	// here we are inserting a new row to tbl_book_loeans
	public void checkOut(int cardNo, int branchId, int bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);

			bldao.checkout(cardNo, branchId, bookId);

			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public boolean isAlreadyCheckedOut(int cardNo, int branchId, int bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			BookLoans bl = new BookLoans();

			bl = bldao.readBookLoansByPK(cardNo, branchId, bookId).get(0);

			if (bl.getDateIn() != null)
				return true;
			else
				return false;

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return false;
	}

	// here we are updating a row in tbl_book_loans
	public void returnBook(BookLoans bl) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);

			bldao.updateBookLoans(bl);

			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void readBranches(int cardNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			List<LibraryBranch> branches = new ArrayList<>();

			branches = bdao.readBranch();
			BorrowerService.branches = branches;

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Borrower> readBorrowerByPK(int cardNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			List<Borrower> borrowers = new ArrayList<>();

			borrowers = bdao.readBorrowerByPK(cardNo);
			return borrowers;

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public List<BookLoans> readbookLoan(int cardNo, int branchId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bdao = new BookLoansDAO(conn);
			List<BookLoans> bookLoans = new ArrayList<>();

			bookLoans = bdao.readBookLoansByPK(cardNo, branchId);
			return bookLoans;

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void populateBooks(int cardNo, int branchId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			List<Book> books = new ArrayList<>();
			List<BookLoans> bookLoans = readbookLoan(cardNo, branchId);
			BookDAO bdao = new BookDAO(conn);

			for (int i = 0; i < bookLoans.size(); i++) {
				Book book = new Book();
				book = bdao.readAllBooksByPK(bookLoans.get(i).getBookId()).get(0);
				books.add(book);
			}
			BorrowerService.books = books;

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// these are the ones I need for updating book copies after check out
	public List<BookCopies> read(int branchId, int bookId) throws SQLException {
		Connection conn = null;
		try {

			conn = util.getConnection();
			BookCopiesDAO bdao = new BookCopiesDAO(conn);

			List<BookCopies> bookcopies = bdao.read(branchId, bookId);
			System.out.println("delaram12");
			return bookcopies;

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//
	public void update(int branchId, int bookId, int noOfCopies) throws SQLException {
		Connection conn = null;
		try {

			conn = util.getConnection();
			BookCopiesDAO bdao = new BookCopiesDAO(conn);

			bdao.update(branchId, bookId, noOfCopies);
			System.out.println("delaram13");
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn != null) {
			conn.rollback();
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	// this one is for updating tbl_book_copies after returning a book
	public void updateBookCopies1(int branchId, int bookId) throws SQLException {
		List<BookCopies> bookcopies = read(branchId, bookId);

		int noOfCopies = bookcopies.get(0).getNoOfCopies();
		update(branchId, bookId, noOfCopies + 1);

	}

	// this one is for updating tbl_book_copies after checking out a book
	public void updateBookCopies2(int branchId, int bookId) throws SQLException {
		List<BookCopies> bookcopies = read(branchId, bookId);
		System.out.println("delaram10");
		int noOfCopies = bookcopies.get(0).getNoOfCopies();
		update(branchId, bookId, noOfCopies - 1);
		System.out.println("delaram11");

	}

	public List<Book> readBookByBranch(int branchId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			List<Book> books = new ArrayList<>();

			books = bdao.readBooksBybranch(branchId);
			return books;

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public Integer readNoOfCOpies(int branchId, int bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bdao = new BookCopiesDAO(conn);

			return bdao.read(branchId, bookId).get(0).getNoOfCopies();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public void checkout1(int cardNo, int branchId, int bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);

			bldao.checkout1(cardNo, branchId, bookId);

			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
