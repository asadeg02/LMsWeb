<%@include file="include.html"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>What do you wan to do?</title>
</head>
<body>
 <div class="container">
	<h2>Enter Card Number</h2>
	<form method="post" action="isvalid">
	<p>${statusMessage} </p>
		<br/>Enter Card Number: <input type="text" name="cardNo"><br />
		 
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Submit Card Number</button>
	</form>
</div>
</body>
</html>