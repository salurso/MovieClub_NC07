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
<body style="background-color: #808080">

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
            <form action="RecensioneServlet" method="POST">
                <input type="hidden" name="ID_Film" value="<%=f.getId()%>">

                <%
                    boolean presente = false;
                    for(Recensione recensione : arrayRecensioni) {
                        if (recensione.getEmailPersona().equals(persona.getEmail())) {
                            presente = true;
                        }
                    }
                    if(presente){
                %>
                <!-- Non mostrare il form se l'utente ha già una recensione -->
                <%
                }else{
                %>
                <input class="btn_add" type="submit" name="action" value="AGGIUNGI RECENSIONE">
                <%
                    }
                %>
            </form>
            <%
            }else{
            %>
            <form action="MainServlet?action=login" method="POST">
                <input class="btn_add" type="submit" name="action" value="AGGIUNGI RECENSIONE">
            </form>
            <%
                }
            %>
        </div>
        <div class="recensione-div-film">
            <%
                for(Recensione recensione : arrayRecensioni){
            %>
            <div class="email-user">
                <span class="grid-item" style="font-weight: bold" ><%= recensione.getEmailPersona() %></span>

                <div class="valutazione">
                    <%
                        int valutazione = recensione.getValutazione();
                        for (int i = 1; i <= 5; i++) {
                            if (i <= valutazione) {
                    %>
                    <span class="stella piena"></span>
                    <%
                    } else {
                    %>
                    <span class="stella vuota"></span>
                    <%
                            }
                        }
                    %>
                </div>

                <span class="grid-item"><%=recensione.getDescrizione()%></span>
                <span class="grid-item"><%=recensione.getDataInserimento()%></span>

                <!-- Aggiungi i bottoni solo se l'utente corrente è l'autore della recensione -->
                <%
                    if (persona != null && recensione.getEmailPersona().equals(persona.getEmail())) {
                %>
                <form action="RecensioneServlet" method="POST">
                    <input type="hidden" name="ID_Film" value="<%=f.getId()%>">
                    <input type="hidden" name="Email_persona" value="<%=persona.getEmail()%>">
                    <input class="btn_modify" type="submit" name="action" value="MODIFICA">
                    <input class="btn_delete" type="submit" name="action" value="ELIMINA">
                </form>
                <%
                    }
                %>
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
