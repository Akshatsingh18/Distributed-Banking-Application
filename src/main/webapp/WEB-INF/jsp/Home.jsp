<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Bank Home Page</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/Home.css">
</head>
<body>

<div class="navbar">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <a href="${pageContext.request.contextPath}/transaction">Transaction</a>
    <a href="${pageContext.request.contextPath}/statement">Statement</a>
    <a href="${pageContext.request.contextPath}/custom-login">Login Page</a>
    <%@ page import="com.project.bank.model.LoginModel" %>
    <%
        LoginModel loggedUser = (LoginModel) session.getAttribute("loggedUser");
        if (loggedUser != null) {
    %>
        <h1 class="name">Welcome, <%= loggedUser.getUsername() %>!</h1>
        <div class="right">
            <form action="${pageContext.request.contextPath}/log-out" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>
    <%
        }
    %>
  </div>
    <h1>Bank Home Page</h1>
</body>
</html>
