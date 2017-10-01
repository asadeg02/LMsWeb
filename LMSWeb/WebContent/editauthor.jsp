<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
Author author = service.readAuthorByPK(Integer.parseInt(request.getParameter("authorId")));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
</head>
<body>
	<h1>Welcome to GCIT Library Management System!</h1>
	<h2>Edit Author</h2>
	<form method="post" action="editAuthor">
		${statusMessage}
		<br/>Enter Author Name to Edit: <input type="text" name="authorName" value="<%=author.getAuthorName()%>"><br />
		<input type="hidden" name="authorId" value="<%=author.getAuthorId()%>">
		<button type="submit">Update Author</button>
	</form>

</body>
</html>