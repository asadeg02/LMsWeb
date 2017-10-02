<%@include file="include.html"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
 <%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.service.AdminService"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%AdminService service = new AdminService();
LibraryBranch branch = service.readBranchByPK(Integer.parseInt(request.getParameter("branchId")));
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>What do you wan to do?</title>
</head>
<body>
 <div class="container">
	<h2> 
	<form method="post" action="editBranch">
	<p>${statusMessage} </p>
		<br/>Enter New Publisher Name: <input type="text" name="name" value="<%=branch.getBranchName()%>"><br />
		<br/>Enter New Branch Address: <input type="text" name="address" value="<%=branch.getBranchAddress()%>"><br />
		 
		<br/><button type="button"
					onclick="javascript:location.href='branch.jsp?branchId=<%%>'"
					class="btn btn-primary btn-sm">BACK TO Branch Services</button><br />
		<input type="hidden" name="branchId" value= "<%=branch.getBranchId()%>">
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Apply Changes</button>
	</form>
</div>
</body>
</html>