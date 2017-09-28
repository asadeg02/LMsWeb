package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.BookCopies;
 

public class BookCopiesDAO extends BaseDAO<BookCopies> {

	public BookCopiesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void saveBookCopies(BookCopies bc) throws SQLException {
		save("INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) VALUES (?,?,?)",
				new Object[] { bc.getBookId(), bc.getBranchId(), bc.getNoOfCopies() });
	}

	public void updateBookCopies(BookCopies bc) throws SQLException {
		save("UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?",
				new Object[] { bc.getNoOfCopies(), bc.getBookId(), bc.getBranchId() });
	}

	public void deleteBookCopies(BookCopies bc) throws SQLException {
		save("DELETE FROM tbl_book_copies WHERE bookId = ? AND branchId = ?",
				new Object[] { bc.getBookId(), bc.getBranchId() });
	}

	public List<BookCopies> readAllBookCopies() throws SQLException {
		return readAll("SELECT * FROM tbl_book_copies", null);
	}

	@Override
	public List<BookCopies> extractData(ResultSet rs) throws SQLException {

		List<BookCopies> bookCopies = new ArrayList<>();
		while (rs.next()) {
			BookCopies bc = new BookCopies();
			bc.setBookId(rs.getInt("bookId"));
			bc.setBranchId(rs.getInt("branchId"));
			bc.setNoOfCopies(rs.getInt("noOfCopies"));

			bookCopies.add(bc);
		}
		return bookCopies;
	}

	@Override
	public List<BookCopies> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<BookCopies> bookCopies = new ArrayList<>();
		while (rs.next()) {
			BookCopies bc = new BookCopies();
			bc.setBookId(rs.getInt("bookId"));
			bc.setBranchId(rs.getInt("branchId"));
			bc.setNoOfCopies(rs.getInt("noOfCopies"));

			bookCopies.add(bc);
		}
		return bookCopies;
	}

}
