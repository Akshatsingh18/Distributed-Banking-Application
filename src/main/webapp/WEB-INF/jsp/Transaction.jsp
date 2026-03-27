<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Transaction</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/Transaction.css"></head>
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
    <h1>Account Transaction</h1>
    <form action="${pageContext.request.contextPath}/transaction" method="post">
        <label for="CName"><strong>Customer Name</strong></label>
        <input type="text" name="CName" id="CName">

        <label for="Amount"><strong>Amount</strong></label>
        <input type="text" name="Amount" id="Amount">

        <input type="submit" value="Submit">
    </form>
    
  <%
    Double balance = (Double) request.getAttribute("balance");
    String error = (String) request.getAttribute("error");
%>

<h2>
<% if (balance != null) { %>
    Balance: <%= balance %>
<% } %>
</h2>

<p style="color:red;">
<% if (error != null) { %>
    <%= error %>
<% } %>
</p>
</body>
</html>
