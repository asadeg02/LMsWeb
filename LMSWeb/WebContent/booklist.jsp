<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
 
<div class="container">
	<h2>Pick the book you want to check out</h2>
	<form method="post" action="returnbook">
	${statusMessage}
		 
		Select Books from list Below: <br/>
		<select name="bookIds">
			<option value="">Select Books from list</option>
			<%for(Book b :BorrowerService.books) {%>
			<option value=<%=b.getBookId()%>><%= b.getTitle()  %></option>
			<%} %>
		</select>
		<input type="hidden" name="cardNo" value="<%=request.getParameter("cardNo")%>">
		<input type="hidden" name="branchId" value="<%=request.getParameter("branchIds")%>">
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Return the book</button>
	</form>
</div>