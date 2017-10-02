package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> {

	public PublisherDAO(Connection conn) {
		super(conn);
	}

	// we always insert publisher with their names , so name cannot be null
	// in the UI implementation we always check for it not to be null
	public void savePublisher(Publisher publisher) throws SQLException {

		if (publisher.getPublisherAddress() == null && publisher.getPublisherPhone() == null)
			save("INSERT INTO tbl_publisher (publisherName) VALUES (?)", new Object[] { publisher.getPublisherName() });

		else if (publisher.getPublisherAddress() == null)
			save("INSERT INTO tbl_publisher (publisherName,publisherPhone) VALUES (?,?)",
					new Object[] { publisher.getPublisherName(), publisher.getPublisherPhone() });

		else if (publisher.getPublisherPhone() == null)
			save("INSERT INTO tbl_publisher (publisherName,publisherAddress) VALUES (?,?)",
					new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress() });

		else
			save("INSERT INTO tbl_publisher (publisherName,publisherAddress,publisherPhone) VALUES (?,?,?)",
					new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(),
							publisher.getPublisherPhone() });

	}

	public Integer savePublisherID(Publisher publisher) throws SQLException {

		if (publisher.getPublisherAddress() == null && publisher.getPublisherPhone() == null) {
			System.out.println("only name");
			return saveWithID("INSERT INTO tbl_publisher (publisherName) VALUES (?)",
					new Object[] { publisher.getPublisherName() });
			
			
		}

		else if (publisher.getPublisherAddress() == null) {
			System.out.println("only name phone");
			return saveWithID("INSERT INTO tbl_publisher (publisherName,publisherPhone) VALUES (?,?)",
					new Object[] { publisher.getPublisherName(), publisher.getPublisherPhone() });}

		else if (publisher.getPublisherPhone() == null) {
			System.out.println("only name address");
			return saveWithID("INSERT INTO tbl_publisher (publisherName,publisherAddress) VALUES (?,?)",
					new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress() });}

		else
			System.out.println("all");
			return saveWithID(
					"INSERT INTO tbl_publisher (publisherName,publisherAddress,publisherPhone) VALUES (?,?,?)",
					new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(),
							publisher.getPublisherPhone() });

	}

	public void updatePublisher(Publisher publisher) throws SQLException {
		if (publisher.getPublisherName() != null) {

			if (publisher.getPublisherPhone() == null && publisher.getPublisherAddress() == null)
				save("UPDATE tbl_publisher SET publisherName = ? WHERE publisherId = ?",
						new Object[] { publisher.getPublisherName(), publisher.getPublisherId() });

			else if (publisher.getPublisherPhone() == null)
				save("UPDATE tbl_publisher SET publisherName = ?, publisherAddress = ?  WHERE publisherId = ?",
						new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(),
								publisher.getPublisherId() });

			else if (publisher.getPublisherAddress() == null)
				save("UPDATE tbl_publisher SET publisherName = ?, publisherPhone = ?  WHERE publisherId = ?",
						new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(),
								publisher.getPublisherId() });
		}

		else {
			if (publisher.getPublisherPhone() == null && publisher.getPublisherAddress() == null) {
			}

			else if (publisher.getPublisherPhone() == null)
				save("UPDATE tbl_publisher publisherAddress = ?  WHERE publisherId = ?",
						new Object[] { publisher.getPublisherAddress(), publisher.getPublisherId() });

			else if (publisher.getPublisherAddress() == null)
				save("UPDATE tbl_publisher SET publisherPhone = ?  WHERE publisherId = ?",
						new Object[] { publisher.getPublisherPhone(), publisher.getPublisherId() });

		}

	}

	public void deletePublisher(Publisher publisher) throws SQLException {
		save("DELETE FROM tbl_publisher WHERE publisherId = ?", new Object[] { publisher.getPublisherId() });
	}

	public List<Publisher> readAllPublishers() throws SQLException {
		return readAll("SELECT * FROM tbl_publisher", null);
	}

	public List<Publisher> readPublishersByTitle(String publisherName) throws SQLException {
		publisherName = "%" + publisherName + "%";
		return readAll("SELECT * FROM tbl_publisher WHERE publisherName like ?", new Object[] { publisherName });
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		BookDAO bdao = new BookDAO(conn);

		List<Publisher> publishers = new ArrayList<>();
		while (rs.next()) {
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			p.setBooks(bdao.readAllFirstLevel(
					"SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book WHERE pubId = ?)",
					new Object[] { p.getPublisherId() }));

			publishers.add(p);
		}
		return publishers;
	}

	@Override
	public List<Publisher> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<>();
		while (rs.next()) {
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));

			publishers.add(p);
		}
		return publishers;
	}
	
	public Integer getPublisherCount() throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_publisher", null);
	}
	
	public List<Publisher> readPublishers(String publisherName, Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		if( publisherName !=null && !publisherName.isEmpty()){
			publisherName = "%"+publisherName+"%";
			return readAll("SELECT * FROM tbl_publisher WHERE publisherName like ?", new Object[]{publisherName});
		}else{
			return readAll("SELECT * FROM tbl_publisher", null);
		}
		
	}
	
	public Publisher readPublisherByPK(Integer publisherId) throws SQLException {
		List<Publisher> publishers = readAll("SELECT * FROM tbl_publisher WHERE publisherId = ?", new Object[]{publisherId});
		if(publishers!=null){
			return publishers.get(0);
		}
		return null;
	}
	

}
