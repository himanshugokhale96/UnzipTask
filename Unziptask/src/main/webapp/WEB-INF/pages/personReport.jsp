<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Report </title>
</head>
<body bgcolor=pink>
 <table border="1"> 
<thead><tr>
<td>id</td>
<td>name</td>
<td>gender</td>
<td>addrs</td>
<td>imgLoc</td>
</tr></thead>
 <tbody>
<c:forEach items="${personList}" var="person">
<tr>
<td>${person.id}</td>
<td>${person.name}</td>
<td>${person.gender}</td>
<td>${person.addrs}</td>
<td>${person.imgLoc}</td>
</tr>
</c:forEach>
</tbody>

</table> 
<spring:url value="/downloadExcel?type=excel" var="xlsURL"></spring:url>
<button><a href="${xlsURL}">Download</a></button> 
<button><a href="${pageContext.request.contextPath }/downloadExcel">view page</a></button>
 <spring:url value="/download?type=pdf" var="pdfURL" />
 
 <a href="${pdfURL}">Download PDF</a>
  
<!-- <button class="btn">Download</button> -->

</body>
</html>