package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.dao.BookCopiesDAO;

import com.gcit.lms.dao.BranchDAO;

import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.LibraryBranch;

public class LibrarianService {

	public Utilities util = new Utilities();
	public static LibraryBranch chosenBranch;
	public static int noOfCopies;

	public List<LibraryBranch> readAllBranches() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			List<LibraryBranch> branches = new ArrayList<>();

			branches = bdao.readAllBranches();
			return branches;

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public void readBranchByPK(int branchId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			List<LibraryBranch> branches = new ArrayList<>();

			branches = bdao.readBranchesByPK(branchId);
			LibrarianService.chosenBranch = branches.get(0);

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateBranch(LibraryBranch lb) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			bdao.updateBranch(lb);
			conn.commit();
			// System.out.println("after commit");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void readAllBookCopies(int branchId, int bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			List<BookCopies> bookCopies = new ArrayList<>();

			bookCopies = bdao.read(branchId, bookId);
			LibrarianService.noOfCopies = bookCopies.get(0).getNoOfCopies();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<BookCopies> read(int branchId, int bookId) throws SQLException {
		Connection conn = null;
		try {

			conn = util.getConnection();
			BookCopiesDAO bdao = new BookCopiesDAO(conn);

			List<BookCopies> bookcopies = bdao.read(branchId, bookId);
			// System.out.println("delaram12");
			return bookcopies;

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

			e.printStackTrace();
		}
		return null;
	}

	public void update(int branchId, int bookId, int noOfCopies) throws SQLException {
		Connection conn = null;
		try {

			conn = util.getConnection();
			BookCopiesDAO bdao = new BookCopiesDAO(conn);

			bdao.update(branchId, bookId, noOfCopies);
			conn.commit();
			// System.out.println("delaram13");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	public void updateBookCopies(int branchId, int bookId, int copies) throws SQLException {
		List<BookCopies> bookcopies = read(branchId, bookId);

		int existing = bookcopies.get(0).getNoOfCopies();
		update(branchId, bookId, copies + existing);

	}
}
