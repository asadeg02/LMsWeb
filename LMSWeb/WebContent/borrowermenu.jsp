<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
 
<div class="container">
	<h2>Pick the branch you want to check out the book from</h2>
	<form method="post" action="showbooks">
	${statusMessage}
		 
		Select branch from list: <br/>
		<select name="branchIds">
			<option value="">Select branch from list</option>
			<%for(LibraryBranch lb :BorrowerService.branches) {%>
			<option value=<%=lb.getBranchId()%>><%=lb.getBranchName() + " Address: " + lb.getBranchAddress() %></option>
			<%} %>
		</select>
		<input type="hidden" name="cardNo" value="<%=request.getParameter("cardNo")%>">
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Submit Library Branch</button>
	</form>
</div>