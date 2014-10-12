<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Login Page</title>
</head>
<body>
<center>
<h3>Login Page</h3>
<br/>
<c:if test="not empty error">
    Wrong login or password
</c:if>
<form method="POST" action="<c:url value='/j_spring_security_check'/>">
Username:<input name="j_username"/></br>
Password:<input type="password" name="j_password"/></br>
<input type="checkbox" name="_spring_security_remember_me"> Remember me
<input type="submit" value="Login"/>
</form>
</center>
</body>
</html>