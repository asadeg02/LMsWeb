<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
 
%>
<div class="container">
	<h2>Add New Library Branch</h2>
	<form method="post" action="addBranch">
	${statusMessage}
		<br/>Enter Branch name: <input type="text" name="name"><br />
		<br/>Enter Branch Address: <input type="text" name="address"><br />
		 
		 
		<br/><button type="submit" class="btn btn-primary btn-md">Save Library Branch</button><br />
	</form>
</div>