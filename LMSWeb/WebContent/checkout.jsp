<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.BookLoans"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%BorrowerService service = new BorrowerService();
 
%>

<div class="container">
	<h2>Add New Author</h2>
	<form method="post" action="checkOut">
	${statusMessage}
		<br/>Enter Card Number: <input type="text" name="cardNo"><br />
		 
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Submit card Number</button>
	</form>
</div>