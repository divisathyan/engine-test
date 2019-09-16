<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Engine Test - Great Portland Street Station Train-arrivals</title>
<link rel="stylesheet" href="/resources/css/style.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
	<h2>Something went wrong while getting the data...</h2>
	<hr>
	<div>
	<form action="/arrivals" method="get">
		<table>
		<tr><td>${message}</td></tr>
		<tr><td><input type="submit" name="Refresh" value="Try Again"></td></tr>
		</table>
	</form>
	</div>
</body>
</html>