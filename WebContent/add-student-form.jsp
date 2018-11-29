<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>student form</title>
</head>
<body>

	<form action="StudentControllerServlet" method="POST">
	<input type="hidden" value="ADD" name="command">
	Nama Depan : <input type="text" name="namadepan"> <br>
	Nama Belakang : <input type="text" name="namabelakang"> <br>
	Email : <input type="text" name="email"> <br>
	<input type="submit" value="Add Student">
	</form>

</body>
</html>