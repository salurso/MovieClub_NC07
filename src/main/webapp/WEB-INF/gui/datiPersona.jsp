<%--
  Created by IntelliJ IDEA.
  User: Vittorio
  Date: 18/01/2024
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./css/datiPersona.css?v=<%=new Random().nextInt()%>"/>
    <title>Informazioni</title>
</head>
<body class="body">
<%@ include file="/WEB-INF/navbar/navbar.jsp" %>
<div class="container">
    <h2 class="dati_title" style="text-align: center;">Informazioni di <%=persona.getNome()%></h2>
    <div class="form-group">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="<%=persona.getNome()%>" disabled>
    </div>
    <div class="form-group">
        <label for="cognome">Cognome:</label>
        <input type="text" id="cognome" name="cognome" value="<%=persona.getCognome()%>" disabled>
    </div>
    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email"  value="<%=persona.getEmail()%>" disabled>
    </div>
    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="********" disabled>
    </div>
</div>
</body>
