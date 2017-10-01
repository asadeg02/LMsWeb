

<%@include file="include.html"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
 
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>What do you wan to do?</title>
</head>
<body>
 <div class="container">
	 
	 <h2>Existing Number of Copies: <%out.print(LibrarianService.noOfCopies);%>
	 </h2>
	<form method="post" action="addcopies">
	<p>${statusMessage} </p>
		<br/>Enter New Number of Copies: <input type="text" name="noofcopies"><br />
		 
		<br/><button type="button"
					onclick="javascript:location.href='librarian.jsp?authorId=<%%>'"
					class="btn btn-primary btn-sm">BACK TO CHOOSING LIBRAY BRANCH</button><br />
		<input type="hidden" name="bookId" value="<%=request.getParameter("bookIds")%>">
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Add</button>
	</form>
</div>
</body>
</html>