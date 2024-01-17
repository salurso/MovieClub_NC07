<%--
  Created by IntelliJ IDEA.
  User: Vittorio
  Date: 17/01/2024
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registazione</title>
    <link rel="stylesheet" type="text/css" href="./css/login.css">
</head>
<body>

<div class="wrapper_reg">
    <h1>Registrati </h1>
    <form action="process_registration" method="post">
        <input type="email" placeholder="E-mail" name="email" id="email" required>
        <input type="text" placeholder="Name" name="name" id = "name" required>
        <input type="text" placeholder="Surname" name="surname" id = "surname" required>
        <input type="password" placeholder="Password" name="password" id = "password" required>
        <button class="btn_reg"  type="submit">Registrati</button>

        <div class="user">
            Sei gia' registrato? <a href="login"> Login </a>
        </div>
    </form>
</div>

</body>
</html>
