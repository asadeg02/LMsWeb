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
Publisher publisher = service.readPublisherByPK(Integer.parseInt(request.getParameter("publisherId")));
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>What do you wan to do?</title>
</head>
<body>
 <div class="container">
	<h2> 
	<form method="post" action="editPublisher">
	<p>${statusMessage} </p>
		<br/>Enter New Publisher Name: <input type="text" name="publisherName" value="<%=publisher.getPublisherName()%>"><br />
		<br/>Enter New Branch Address: <input type="text" name="publisherAddress" value="<%=publisher.getPublisherAddress()%>"><br />
		<br/>Enter New Branch Phone: <input type="text" name="publisherPhone" value="<%=publisher.getPublisherPhone()%>"><br />
		<br/><button type="button"
					onclick="javascript:location.href='publisher.jsp?authorId=<%%>'"
					class="btn btn-primary btn-sm">BACK TO Publisher Services</button><br />
		<input type="hidden" name="publisherId" value= "<%=publisher.getPublisherId()%>">
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Apply Changes</button>
	</form>
</div>
</body>
</html>