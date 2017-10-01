<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%LibrarianService service = new LibrarianService();
List<LibraryBranch> branches = service.readAllBranches();
%>
 
<div class="container">
	<h2>Pick The Book You want to Update Its Number Of Copies</h2>
	<form method="post" action="getnoofcopies">
	${statusMessage}
		 
		Select book from list: <br/>
		<select name="bookIds">
			<option value="">Select book from list</option>
			<%for(Book b :LibrarianService.chosenBranch.getBooks()) {%>
			<option value=<%=b.getBookId()%>><%=b.getTitle() + " Publisher: " + b.getPublisher() %></option>
			<%} %>
		</select>
		<input type="hidden" name="branchId" value="<%=request.getParameter("branchIds")%>"> 
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Submit book</button>
	</form>
</div>