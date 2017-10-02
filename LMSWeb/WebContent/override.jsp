<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%LibrarianService service = new LibrarianService();
List<LibraryBranch> branches = service.readAllBranches();
%>
 
<div class="container">
	<h2>Pick The Branch Involved in the BookLoan</h2>
	<form method="post" action=overRide>
	${statusMessage}
		 
		Select branch from list: <br/>
		<select name="branchIds">
			<option value="">Select branch from list</option>
			<%for(LibraryBranch lb :branches) {%>
			<option value=<%=lb.getBranchId()%>><%=lb.getBranchName() + " Address: " + lb.getBranchAddress() %></option>
			<%} %>
		</select>
		 
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Submit Library Branch</button>
	</form>
</div>