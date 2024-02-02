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
    <link rel="stylesheet" type="text/css" href="./css/registrazione.css?v=<%=new Random().nextInt()%>">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body class="body">
<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<div class="wrapper_reg">
    <h1 class="title_reg">Registrazione </h1>
    <form action="RegistrazioneServlet" method="post">
        <input class="input_reg" type="email" placeholder="E-mail" name="email" id="email" required>
        <input class="input_reg" type="text" placeholder="Name" name="nome" id = "nome" required>
        <input class="input_reg" type="text" placeholder="Surname" name="cognome" id = "cognome" required>
        <input class="input_reg" type="password" placeholder="Password" name="password" id = "password" required>
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
