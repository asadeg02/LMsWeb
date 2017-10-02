<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Book> books = service.readBooks();
%>
<div class="container">
	<h2>Add New Publisher</h2>
	<form method="post" action="addPublisher">
	${statusMessage}
		<br/>Enter Publisher Name: <input type="text" name="publisherName"><br />
		<br/>Enter Publisher Address: <input type="text" name="publisherAddress"><br />
		<br/>Enter Publisher Phone: <input type="text" name="publisherPhone"><br />
		Select Books from list Below: <br/>
		<select multiple="multiple" size="10" name="bookIds">
			<option value="">Select Book to associate</option>
			<%for(Book b: books) {%>
			<option value=<%=b.getBookId()%>><%=b.getTitle() %></option>
			<%} %>
		</select>
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Save Publisher</button>
	</form>
</div>