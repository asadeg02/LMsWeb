package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.LibraryBranch;

public class BranchDAO extends BaseDAO<LibraryBranch> {

	public BranchDAO(Connection conn) {
		super(conn);
	}

	public void saveBranch(LibraryBranch branch) throws SQLException {

		if (branch.getBranchAddress() == null)
			save("INSERT INTO tbl_library_branch (branchName) VALUES (?)", new Object[] { branch.getBranchName() });

		else if (branch.getBranchAddress() != null)
			save("INSERT INTO tbl_library_branch (branchName, branchAddress) VALUES (?,?)",
					new Object[] { branch.getBranchName(), branch.getBranchAddress() });

	}

	public int saveBranchID(LibraryBranch branch) throws SQLException {

		if (branch.getBranchAddress() == null)
			return saveWithID("INSERT INTO tbl_library_branch (branchName) VALUES (?)",
					new Object[] { branch.getBranchName() });

		else
			return saveWithID("INSERT INTO tbl_library_branch (branchName, branchAddress) VALUES (?,?)",
					new Object[] { branch.getBranchName(), branch.getBranchAddress() });

	}

	public void updateBranch(LibraryBranch branch) throws SQLException {
		if (branch.getBranchName() != null) {

			if (branch.getBranchAddress() == null)
				save("UPDATE tbl_library_branch SET branchName = ? WHERE branchId = ?",
						new Object[] { branch.getBranchName(), branch.getBranchId() });

			else if (branch.getBranchAddress() != null)
				save("UPDATE tbl_library_branch SET branchName = ?, branchAddress = ?  WHERE branchId = ?",
						new Object[] { branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId() });

		}

		else {
			if (branch.getBranchAddress() == null) {
			}

			else if (branch.getBranchAddress() == null)
				save("UPDATE tbl_library_branch branchAddress = ?  WHERE branchId = ?",
						new Object[] { branch.getBranchAddress(), branch.getBranchId() });

		}

	}

	public void deleteBranch(LibraryBranch branch) throws SQLException {
		save("DELETE FROM tbl_library_branch WHERE branchId = ?", new Object[] { branch.getBranchId() });
	}

	public List<LibraryBranch> readBranchesByAddress(String address) throws SQLException {

		return readAll("SELECT * FROM tbl_library_branch WHERE branchAddress = ?", new Object[] { address });
	}

	public List<LibraryBranch> readBranchesByName(String name) throws SQLException {

		name = "%" + name + "%";
		return readAll("SELECT * FROM tbl_library_branch WHERE branchName like ?", new Object[] { name });
	}

	@Override
	public List<LibraryBranch> extractData(ResultSet rs) throws SQLException {
		BookDAO bdao = new BookDAO(conn);

		List<LibraryBranch> branches = new ArrayList<>();
		while (rs.next()) {
			LibraryBranch l = new LibraryBranch();
			l.setBranchId(rs.getInt("branchId"));
			l.setBranchName(rs.getString("branchName"));
			l.setBranchAddress(rs.getString("branchAddress"));

			l.setBooks(bdao.readAllFirstLevel(
					"SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId from tbl_book_copies WHERE branchId = ?)",
					new Object[] { l.getBranchId() }));

			branches.add(l);
		}
		return branches;
	}

	@Override
	public List<LibraryBranch> extractDataFirstLevel(ResultSet rs) throws SQLException {

		List<LibraryBranch> branches = new ArrayList<>();
		while (rs.next()) {
			LibraryBranch l = new LibraryBranch();
			l.setBranchId(rs.getInt("branchId"));
			l.setBranchName(rs.getString("branchName"));
			l.setBranchAddress(rs.getString("branchAddress"));

			branches.add(l);

		}

		return branches;

	}

}
