<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: Vittorio
  Date: 17/01/2024
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="./js/hideMessage.js"></script>
<script src="./js/registrazione.js"></script>
<head>
    <title>Registazione</title>
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=<%=new Random().nextInt()%>">
</head>
<body>

<div class="wrapper_reg">
    <h1>Registrati </h1>
    <form action="RegistrazioneServlet" method="post">
        <input type="email" placeholder="E-mail" name="email" id="email" required>
        <input type="text" placeholder="Name" name="nome" id = "nome" required>
        <input type="text" placeholder="Surname" name="cognome" id = "cognome" required>
        <input type="password" placeholder="Password" name="password" id = "password" required>
        <button class="btn_reg"  onclick="return(validaRegistrazione())" type="submit">Registrati</button>

        <%String loginFail = (String) request.getAttribute("LoginFail");
            if(loginFail != null) {
        %><p id="stato"><%=loginFail%></p>
        <%}%><!--Messaggio errore login -->

        <div class="user">
            Sei gia' registrato? <a href="MainServlet?action=login"> Login </a>
        </div>
    </form>
</div>

</body>
</html>
