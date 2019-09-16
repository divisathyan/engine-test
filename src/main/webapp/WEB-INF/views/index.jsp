<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Engine Test - Great Portland Street Station Arrivals</title>
<link rel="stylesheet" href="/resources/css/style.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
	<h2>Trains arriving Great Portland Street Station</h2>
	<hr>
	<div>
	<form>
		<table>
		<c:choose>
		<c:when test="${not empty arrivals}">
		<c:forEach var="directionEntry" items="${arrivals}">
			<tr class="directiontr">
			<td>
			<c:out value="${directionEntry.key}"/>
			</td>			
			</tr>
			
			<!-- Value is a map by Platform -->
			<c:choose>
			<c:when test="${not empty directionEntry.value}">
			<c:forEach var="platformEntry" items="${directionEntry.value}">
				<tr class="platformtr">
				<td>
				<c:out value="${platformEntry.key}"/>
				</td>			
				</tr>
				
				<tr class="predictionth">
				<th align="center">Towards</th>
				<th align="center">Due in (mins)</th>
				<th align="center">Arrival Time</th>				
				</tr>
				
				<!-- Value is a List of Predictions -->
				<c:choose>
				<c:when test="${not empty platformEntry.value}">
				<c:forEach var="prediction" items="${platformEntry.value}">
					<tr class="predictiontr">
					<td>
					<c:out value="${prediction.towards}"/>
					</td>
					<td>
					<c:out value="${prediction.printTimeToStation}"/>
					</td>
					<td>
					<c:out value="${prediction.printEstimatedArrival}"/>
					</td>		
					</tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="predictiontr">
					<td colspan="3">
					There are no trains scheduled!
					</td>
					</tr>
				</c:otherwise>
				</c:choose>
				
			</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="platformtr">
				<td>
				There are no trains scheduled!
				</td>			
				</tr>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<tr class="directiontr">
			<td>
			There are no trains scheduled!
			</td>			
			</tr>
		</c:otherwise>
		</c:choose>
		</table>
	</form>
	</div>
</body>
</html>