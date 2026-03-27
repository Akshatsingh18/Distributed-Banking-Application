<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/Login.css">
</head>
<body>
      <h1 class="page-heading">RegisterPage</h1>
<form action="${pageContext.request.contextPath}/register" method="post">
           <h1>Full Name</h1>
           <input type="text" name="Fullname"> <br>
           <h1>UserName</h1>
           <input type="text" name="username"> <br>
           <h1>Password</h1>
           <input type="text" name="password"> <br>
           <input type="Submit" name="sumbit">
      </form>
  
</body>
</html>
