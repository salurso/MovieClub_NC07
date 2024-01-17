<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: Vittorio
  Date: 17/01/2024
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=<%=new Random().nextInt()%>">
</head>
<body>
<div class="wrapper">
  <h1> Login </h1>
  <form action="LoginServlet" method="post">
    <input type="text" placeholder="E-mail" name="email" id="email" required>
    <input type="password" placeholder="Password" name="password" id="password" required>
    <button class="btn_login" type="submit" onclick="return(validateLogin())"> Login </button>
  </form>

  <div class="user">
    Sei nuovo? <a href="MainServlet?action=registrazione"> Registrati Qui! </a>
  </div>
</div>

</body>
</html>
