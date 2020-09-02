<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
	<form action="/blog/login" method="post">
	ID : <input type="text" name="userid">
	PW : <input type="password" name="userpw">
	<input type="submit" value="로그인">
</form>
</body>
</html>