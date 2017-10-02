<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
 
 
<div class="container">
	<h2>Pick The Book Involved in the BookLoan</h2>
	<form method="post" action=overRide2>
	${statusMessage}
		 
		Select book from list: <br/>
		<select name="bookIds">
			<option value="">Select book from list</option>
			<%for(Book b :AdminService.books) {%>
			<option value=<%=b.getBookId()%>><%=b.getTitle() %></option>
			<%} %>
		</select>
		<input type="hidden" name="branchIds" value="<%=request.getParameter("branchIds")%>"> 
		 
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Submit book</button>
	</form>
</div>