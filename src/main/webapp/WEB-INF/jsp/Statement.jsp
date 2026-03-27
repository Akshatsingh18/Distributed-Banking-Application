<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.project.bank.model.LoginModel" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Insert title here</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/Statement.css">
</head>
<body>
  <div class="navbar">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <a href="${pageContext.request.contextPath}/transaction">Transaction</a>
    <a href="${pageContext.request.contextPath}/statement">Statement</a>
    <a href="${pageContext.request.contextPath}/custom-login">Login Page</a>
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

  <h1>Generate Statement</h1>
  <form action="${pageContext.request.contextPath}/statement" method="post">
      <button type="submit">Generate</button>
  </form>
  
  <div>
    <c:if test="${not empty statements}">
      <h2>Transaction Statement</h2>
      <table border="1">
        <tr>
          <th>Date</th>
          <th>Amount</th>
          <th>Reference No</th>
          <th>From</th>
          <th>To</th>
          <th>Status</th>
        </tr>
        <c:forEach var="s" items="${statements}">
          <tr>
            <td>${s.date}</td>
            <td>${s.amount}</td>
            <td>${s.contract_ref_no}</td>
            <td>${s.name}</td>
            <td>${s.custname}</td> <!-- fixed lowercase -->
            <td>${s.status}</td>
          </tr>
        </c:forEach>
      </table>
    </c:if>
  </div>
</body>
</html>
