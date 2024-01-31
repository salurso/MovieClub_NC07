<%@ page import="application.entity.Film" %>
<%@ page import="application.entity.Recensione" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="application.entity.Recensione" %><%--
  Created by IntelliJ IDEA.
  User: Costantino
  Date: 16/01/2024
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./css/infoFilm.css?v=<%=new Random().nextInt()%>"/>

    <%
        Film f = (Film) request.getAttribute("film");
        ArrayList<Recensione> arrayRecensioni = (ArrayList<Recensione>) request.getAttribute("recensioni");
    %>

    <title><%=f.getTitolo()%></title>
</head>
<body>

<%@ include file="/WEB-INF/navbar/navbar.jsp" %>


<div class="main-wrapper">
    <div class="container">
        <div class="film-div">
            <div class="film-div-left">
                <div class="img-container">
                    <img src="<%=f.getCopertina()%>">
                </div>
            </div>

            <div class="film-div-right">
                <span class="film-titolo"><%=f.getTitolo()%></span>
                <span class="film-genere"><%=f.getGenere()%></span>


                <p class="film-descrizione"> <%=f.getDescrizione()%></p>
                <p class="film-descrizione">Durata: <%=f.getDurata()%></p>
                <p class="film-descrizione">Data uscita: <%=f.getDataUscita()%></p>
                <p class="film-descrizione">Regista:  <%=f.getRegista()%></p>
            </div>
        </div>

        <div class="recensione-div">
            <span class="film-recensione">Recensioni</span>
            <%if(persona != null){%>
            <form action="RecensioneServlet?action=aggRecensione" method="POST">
                <input type="hidden" name="ID_Film" value="<%=f.getId()%>">
            <%}else{%>
            <form action="MainServlet?action=login" method="POST">
            <%}%>
                <input class="btn_add" type="submit" name="action" value="AGGIUNGI RECENSIONE">
            </form>
        </div>
        <div class="recensione-div-film">
            <%
            for(Recensione recensione : arrayRecensioni){
            %>
            <div class="email-user">
                <span class="grid-item"><%=recensione.getEmailPersona()%></span>
                <div class="valutazione">
                    <%
                    int valutazione = recensione.getValutazione(); // Supponiamo che la valutazione sia un numero intero compreso tra 1 e 5
                    for (int i = 1; i <= 5; i++) {
                    if (i <= valutazione) {
                    %>
                        <span class="stella piena"></span>
                    <% } else { %>
                        <span class="stella vuota"></span>
                    <% }
                        } %>

                    <!-- Puoi aggiungere o rimuovere stelle piene o vuote a seconda della valutazione -->
                <span class="grid-item descrizione"><%=recensione.getDescrizione()%></span>
                </div>
                <span class="grid-item">
                        <%=recensione.getDataInserimento()%>
                </span>
            </div>

            <%
            }
            %>
        </div>

        <div class="ratio ratio-16x9">
            <iframe src="<%=f.getTrailer()%>" title="YouTube video" allowfullscreen></iframe>
        </div>
    </div>
</div>
</body>
</html>
