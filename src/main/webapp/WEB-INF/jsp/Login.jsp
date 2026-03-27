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
       <h1 class="page-heading">LoginPage</h1>
       <form action="${pageContext.request.contextPath}/custom-login" method="post">
           <h1>Name</h1>
           <input type="text" name="Username"> <br>
           <h1>Password</h1>
           <input type="text" name="Password"> <br>
           <input type="submit" value="Submit">
			<%
			    String loginStatus = (String) session.getAttribute("LoginStatus");
			    if (loginStatus != null) {
			%>
			    <p style="color:red;"><%= loginStatus %></p>
			<%
			        session.removeAttribute("LoginStatus"); 
			    }
			%>
           <p>Dont have a account <a href="${pageContext.request.contextPath}/register">Register Here</a></p>           
      </form>
			    <a href="${pageContext.request.contextPath}/">Home Page</a>
  
</body>
</html>
