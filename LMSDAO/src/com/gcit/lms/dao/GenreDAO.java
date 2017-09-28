package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;

public class GenreDAO extends BaseDAO<Genre> {

	public GenreDAO(Connection conn) {
		super(conn);
	}

	public void saveGenre(Genre genre) throws SQLException {
		save("INSERT INTO tbl_genre (genre_name) VALUES (?)", new Object[] { genre.getGenreName() });
	}

	public Integer saveGenreWithID(Genre genre) throws SQLException {
		return saveWithID("INSERT INTO tbl_genre () VALUES (genre_name)", new Object[] { genre.getGenreName() });
	}

	public void saveBookGenre(Genre genre) throws SQLException {
		for (Book b : genre.getBooks()) {
			save("INSERT INTO tbl_book_genres VALUES (?, ?)", new Object[] { genre.getGenreId(), b.getBookId() });
		}
	}

	public void updateGenre(Genre genre) throws SQLException {
		save("UPDATE tbl_genre SET genre_name = ? WHERE genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void deleteGenre(Genre genre) throws SQLException {
		save("DELETE FROM tbl_genre WHERE genre_id = ?", new Object[] { genre.getGenreId() });
	}

	public List<Genre> readGenres(String GenreName) throws SQLException {
		if (GenreName != null && !GenreName.isEmpty()) {
			GenreName = "%" + GenreName + "%";
			return readAll("SELECT * FROM tbl_genre WHERE GenreName like ?", new Object[] { GenreName });
		} else {
			return readAll("SELECT * FROM tbl_genre", null);
		}

	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		BookDAO bdao = new BookDAO(conn);
		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre g = new Genre();
			g.setGenreId(rs.getInt("genre_id"));
			g.setGenreName(rs.getString("genre_name"));
			g.setBooks(bdao.readAllFirstLevel(
					"SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_genres WHERE genre_id = ?)",
					new Object[] { g.getGenreId() }));

			genres.add(g);
		}
		return genres;
	}

	@Override
	public List<Genre> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre g = new Genre();
			g.setGenreId(rs.getInt("genre_id"));
			g.setGenreName(rs.getString("genre_name"));
			genres.add(g);

		}
		return genres;
	}

}
