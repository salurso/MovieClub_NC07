<%--
  Created by IntelliJ IDEA.
  User: Costantino
  Date: 08/02/2024
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consigliati</title>
    <link rel="stylesheet" type="text/css" href="./css/film.css?v=<%=new Random().nextInt()%>"/>
    <link rel="stylesheet" type="text/css" href="./css/infoFilm.css?v=<%=new Random().nextInt()%>"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        .body {
            /*display: flex;*/
            justify-content: center;
            align-items: center;
            height: 100vh; /* Imposta l'altezza del body al 100% della viewport per centrare verticalmente */
            margin: 0; /* Rimuove i margini predefiniti del body */
        }
        .content {
            text-align: center; /* Allinea il contenuto al centro orizzontalmente */
        }
    </style>
</head>
<body class="body">
    <%@ include file="/WEB-INF/navbar/navbar.jsp" %>
    <div class="content">
        <h2 class="title_release">Non abbiamo abbastanza informazioni su di te</h2>
        <h2 class="title_release">Aggiungi qualche film alla tua watchlist</h2>

        <form action="MainServlet?action=film" method="POST">
            <input class="btn_add" type="submit" name="action" value="AGGIUNGI FILM" style="margin-top: 20px">
        </form>
    </div>

</body>
</html>
