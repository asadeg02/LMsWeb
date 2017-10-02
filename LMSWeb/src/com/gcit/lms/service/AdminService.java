package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.entity.Borrower;

public class AdminService {

	public Utilities util = new Utilities();

	public void saveAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			if (author.getAuthorId() != null) {
				adao.updateAuthor(author);
			} else {
				adao.saveAuthor(author);
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

	public void deleteAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.deleteAuthor(author);
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

	public List<Author> readAuthors(String searchString, Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthors(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public List<Book> readBooks() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBooks();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public Author readAuthorByPK(Integer authorId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthorByPK(authorId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public Publisher readPublisherByPK(Integer publisherId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readPublisherByPK(publisherId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public Integer getAuthorsCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.getAuthorsCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public Integer getPublisherCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.getPublisherCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public List<Publisher> readPublishers(String searchString, Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readPublishers(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		// System.out.println("arezoo 20");

		return null;
	}

	public void updatePublisher(Publisher p) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.updatePublisher(p);
			;
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

	public void deletePublisher(Publisher p) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.deletePublisher(p);
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

	public void savePublisher(Publisher p) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			if (p.getPublisherId() != null) {
				pdao.updatePublisher(p);
			} else {
				pdao.savePublisher(p);
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

	// next method
	public void saveBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			if (book.getBookId() != null) {
				bdao.updateBook(book);
			} else {
				bdao.saveBook(book);
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

	public void savePubId(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);

			bdao.updatePubId(book);

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

	public Integer saveBookId(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);

			Integer branchId = bdao.saveBookID(book);

			conn.commit();
			return branchId;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public Integer savePublisherId(Publisher p) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);

			Integer pubId = pdao.savePublisherID(p);
			conn.commit();
			return pubId;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public void updatePubId(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);

			bdao.updatePubId(book);

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

	public Book readBookByPK(Integer bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			List<Book> books = bdao.readAllBooksByPK(bookId);
			return books.get(0);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public void updateBook1(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);

			bdao.updateBook1(book);

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

	public Borrower readBorrowerByPK(Integer cardNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.readBorrowerByPK(cardNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public Integer getBorrowersCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.getBorrowerCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public List<Borrower> readBorrowers(String searchString, Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.readBorrowers(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		// System.out.println("arezoo 20");

		return null;
	}

	public void updateBorrower(Borrower b) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.updateBorrower(b);
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

	public void deleteBorrower(Borrower b) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.deleteBorrower(b);
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

	public void saveBorrower(Borrower b) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			if (b.getCardNo() != null) {
				bdao.updateBorrower(b);
			} else {
				bdao.saveBorrower(b);
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
	
	public Integer getBranchesCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			return bdao.getBranchCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}
	
	public List<LibraryBranch> readBranches(String searchString, Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			return bdao.readBranches(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		// System.out.println("arezoo 20");

		return null;
	}
	
	public LibraryBranch readBranchByPK(Integer branchId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			return bdao.readBranchByPK(branchId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
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
	
	public void deleteBranch(LibraryBranch lb) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			bdao.deleteBranch(lb);
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
	
	public void saveBranch(LibraryBranch lb) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			if (lb.getBranchId() != null) {
				bdao.updateBranch(lb);
			} else {
				bdao.saveBranch(lb);;
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
	
	public Integer getBooksCount() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.getBookCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}
	
	public List<Book> readBooks(String searchString, Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readBooks(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		// System.out.println("arezoo 20");

		return null;
	}
	
	public Book readBookByPK1(Integer bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn); 
			return bdao.readBookByPK(bookId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}
	
	public void deleteBook(Book lb) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.deleteBook(lb);
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
