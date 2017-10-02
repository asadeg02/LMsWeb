<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
 
 
<div class="container">
	<h2>Pick The new overdue for the bookLoan:</h2>
	<form method="post" action=submitDue>
	${statusMessage}
		 
		 
		<input type="hidden" name="branchIds" value="<%=request.getParameter("branchIds")%>">
		<input type="hidden" name="bookIds" value="<%=request.getParameter("bookIds")%>">  
		<br/>Enter new duedate: <input type="text" name="dueDate"><br />
		 
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Submit DueDate</button>
	</form>
</div>