<%@include file="include.html"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
 <%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.service.AdminService"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%AdminService service = new AdminService();
Borrower borrower = service.readBorrowerByPK(Integer.parseInt(request.getParameter("cardNo")));
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>What do you wan to do?</title>
</head>
<body>
 <div class="container">
	<h2> 
	<form method="post" action="editBorrower">
	<p>${statusMessage} </p>
	 
		<br/>Enter New Borrower Name: <input type="text" name="name" value="<%=borrower.getName()%>"><br />
		<br/>Enter New Borrower Address: <input type="text" name="address" value="<%=borrower.getAddress()%>"><br />
		<br/>Enter New Borrower Phone: <input type="text" name="phone" value="<%=borrower.getPhone()%>"><br />
		<br/><button type="button"
					onclick="javascript:location.href='adminborrower.jsp?authorId=<%%>'"
					class="btn btn-primary btn-sm">BACK TO Borrower Services</button><br />
		<input type="hidden" name="cardNo" value= "<%=borrower.getCardNo()%>">
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Apply Changes</button>
	</form>
</div>
</body>
</html>