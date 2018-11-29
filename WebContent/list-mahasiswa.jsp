<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List mahasiswa</title>
</head>
<body>
	
	<input type="button" value="Add Student"
	onclick="window.location.href='add-student-form.jsp'"
	class="add-student-form">
<table>
	<tr>
		<th>Nama Depan</th>
		<th>Nama Belakang</th>
		<th>Email</th>
		<th>Aksi</th>
	</tr>
		
		
		
	<c:forEach var="mahasiswa" items="${list_mahasiswa}">
		<c:url var="link" value="StudentControllerServlet">
			<c:param name="command" value="LOAD"></c:param>
			<c:param name="id" value="${mahasiswa.getId()}"></c:param>
		</c:url>
		
		<c:url var="delete" value="StudentControllerServlet">
			<c:param name="command" value="DELETE"></c:param>
			<c:param name="id" value="${mahasiswa.getId()}"></c:param>
		</c:url>
		
		<tr>
			<td>${mahasiswa.getNamadepan()}</td>
			<td>${mahasiswa.getNamabelakang()}</td>
			<td>${mahasiswa.getEmail()}</td>
			<td><a href="${link}">update</a> || <a href="${delete}">delete</a> </td>
		</tr>
	</c:forEach>
	
</table>
</body>
</html>