<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Author> authors = service.readAuthors();
List<Publisher> publishers = service.readPublishers();
List<Genre> genres = service.readGenres();
%>
<div class="container">
	<h2>Add New Author</h2>
	<form method="post" action="addBook">
	${statusMessage}
		<br/>Enter Book Title: <input type="text" name="title"><br />
		 
		please Enter Additional Information(optional) : <br/>
		<br/><select multiple="multiple" size="20" name="authorId">
			<option value="">Select Author to associate</option>
			<%for(Author a: authors) {%>
			<option value=<%=a.getAuthorId()%>><%=a.getAuthorName() %></option>
			<%} %>
		</select><br />
		
		<br/><select multiple="multiple" size="20" name="genreId">
			<option value="">Select Genres to associate(Optional)</option>
			<%for(Genre g: genres) {%>
			<option value=<%=g.getGenreId()%>><%=g.getGenreName() %></option>
			<%} %>
		</select><br />
		
		<br/><select name="publisherId">
			<option value="">Select Publishers to associate(Optional)</option>
			<%for(Publisher p: publishers) {%>
			<option value=<%=p.getPublisherId()%>><%=p.getPublisherName() %></option>
			<%} %>
		</select><br />
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Save Book</button>
	</form>
</div>