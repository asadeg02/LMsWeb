package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Publisher;

public class BorrowerDAO extends BaseDAO<Borrower> {

	public BorrowerDAO(Connection conn) {
		super(conn);
	}

	public void saveBorrower(Borrower borrower) throws SQLException {

		if (borrower.getAddress() == null && borrower.getPhone() == null)
			save("INSERT INTO tbl_borrower (cardNo,name) VALUES (?)",
					new Object[] { borrower.getCardNo(), borrower.getName() });

		else if (borrower.getAddress() == null)
			save("INSERT INTO tbl_borrower ( cardNo,name, phone) VALUES (?,?)",
					new Object[] { borrower.getCardNo(), borrower.getName(), borrower.getPhone() });

		else if (borrower.getPhone() == null)
			save("INSERT INTO tbl_borrower ( cardNo,name, address) VALUES (?,?,?)",
					new Object[] { borrower.getCardNo(), borrower.getName(), borrower.getAddress() });

		else
			save("INSERT INTO tbl_borrower ( cardNo,name, address,phone) VALUES (?,?,?)", new Object[] {
					borrower.getCardNo(), borrower.getName(), borrower.getAddress(), borrower.getPhone() });
	}

	public void updateBorrower(Borrower borrower) throws SQLException {
		if (borrower.getName() != null) {

			if (borrower.getAddress() == null && borrower.getPhone() == null)
				save("UPDATE tbl_borrower SET name = ? WHERE cardNo = ?",
						new Object[] { borrower.getName(), borrower.getCardNo() });

			else if (borrower.getPhone() == null)
				save("UPDATE tbl_borrower SET name = ?, address = ?  WHERE cardNo = ?",
						new Object[] { borrower.getName(), borrower.getAddress(), borrower.getCardNo() });

			else if (borrower.getAddress() == null) {
				save("UPDATE tbl_borrower SET name = ?, phone = ?  WHERE cardNo = ?",
						new Object[] { borrower.getName(), borrower.getPhone(), borrower.getCardNo() });
				System.out.println("all");
			} else {
				save("UPDATE tbl_borrower SET phone = ?, name =?, address =?  WHERE cardNo = ?", new Object[] {
						borrower.getPhone(), borrower.getName(), borrower.getAddress(), borrower.getCardNo() });
				System.out.println("all");
			}
		}

		else {
			if (borrower.getAddress() == null && borrower.getPhone() == null) {
			}

			else if (borrower.getPhone() == null)
				save("UPDATE tbl_borrower address = ?  WHERE cardNo = ?",
						new Object[] { borrower.getAddress(), borrower.getCardNo() });

			else if (borrower.getAddress() == null)
				save("UPDATE tbl_borrower SET phone = ?  WHERE cardNo = ?",
						new Object[] { borrower.getPhone(), borrower.getCardNo() });

		}

	}

	public void deleteBorrower(Borrower borrower) throws SQLException {
		save("DELETE FROM tbl_borrower WHERE cardNo = ?", new Object[] { borrower.getCardNo() });
	}

	public List<Borrower> readAllBorrowers() throws SQLException {
		return readAll("SELECT * FROM tbl_borrower", null);
	}

	public List<Borrower> readBorrowersByName(String name) throws SQLException {
		name = "%" + name + "%";
		return readAll("SELECT * FROM tbl_borrower WHERE name like ?", new Object[] { name });
	}

	public List<Borrower> readBorrowersByAddress(String address) throws SQLException {

		return readAll("SELECT * FROM tbl_borrower WHERE address = ?", new Object[] { address });
	}

	public List<Borrower> readBorrowersByPhone(String phone) throws SQLException {

		return readAll("SELECT * FROM tbl_borrower WHERE phone =  ?", new Object[] { phone });
	}

	public List<Borrower> readBorrowerByPK(int cardNo) throws SQLException {

		return readAll("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[] { cardNo });
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		// BookLoansDAO bldao = new BookLoansDAO(conn);

		List<Borrower> borrowers = new ArrayList<>();
		while (rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			b.setPhone(rs.getString("phone"));
			b.setAddress(rs.getString("address"));

			/*
			 * b.setBookLoans(bldao.
			 * readAllFirstLevel("SELECT * FROM tbl_book_loans WHERE cardNo = ?)", new
			 * Object[] { b.getCardNo() }));
			 */

			borrowers.add(b);
		}
		return borrowers;
	}

	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException {

		List<Borrower> borrowers = new ArrayList<>();
		while (rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			b.setPhone(rs.getString("phone"));
			b.setAddress(rs.getString("address"));

			borrowers.add(b);

		}
		return borrowers;
	}

	public Integer getBorrowerCount() throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_borrower", null);
	}

	public List<Borrower> readBorrowers(String name, Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		if (name != null && !name.isEmpty()) {
			name = "%" + name + "%";
			return readAll("SELECT * FROM tbl_borrower WHERE name like ?", new Object[] { name });
		} else {
			return readAll("SELECT * FROM tbl_borrower", null);
		}

	}

	public Borrower readBorrowerByPK(Integer cardNo) throws SQLException {
		List<Borrower> borrowers = readAll("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[] { cardNo });
		if (borrowers != null) {
			return borrowers.get(0);
		}
		return null;
	}

}
