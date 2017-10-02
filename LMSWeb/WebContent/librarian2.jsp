<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@include file="include.html"%>

<div class="container">
	<table>
		<tr>
			<td><a href="updatelibrary.jsp">Update Details Of The Library </a></td>
		</tr>
		"WebContent/librarian2.jsp"<tr>
			<td><a href="addcopies.jsp">Add Copies of Book To The Branch</a></td>
		</tr>
		 
	</table>
	<input type="hidden" name="branchId" value="<%=request.getParameter("branchIds")%>">
</div>
</head>
<body>

</body>
</html>