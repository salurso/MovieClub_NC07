<%@ page import="application.entity.Film" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: Costantino
  Date: 15/01/2024
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Film</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="./css/film.css?v=<%=new Random().nextInt()%>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script type="text/javascript">

        $(document).ready(function(){
            $('.list').click(function(){
                const value = $(this).attr('data-filter');
                if(value == 'tutti'){
                    $('.product-container').show('1000');
                    $('.title').show('1000');
                }
                else{
                    $('.title').not('.'+value).hide('1000');
                    $('.product-container').not('.'+value).hide('1000');
                    $('.product-container').filter('.'+value).show('1000');
                }
            })
            $('.list').click(function (){
                $(this).addClass('active').siblings().removeClass('active');
            })

            if ( window.history.replaceState ) { // lo stato precedente (con requisiti) viene resettato
                window.history.replaceState( null, null, window.location.href );
            }
        })


    </script>

</head>
<body>
    <%@ include file="/WEB-INF/navbar/navbar.jsp" %>

    <%
        ArrayList<Film> films = (ArrayList<Film>) request.getAttribute("films");
        ArrayList<String> generi = (ArrayList<String>) request.getAttribute("generi");
    %>
    <div>

        <section>
            <ul>
                <li class="list active" data-filter="tutti">Tutti</li>
                <%
                    for(int i=0; i<generi.size(); i++){
                %>
                <li class="list" data-filter="<%=generi.get(i)%>"><%=generi.get(i)%></li>
                <%}%>
            </ul>
        </section>


        <%
            for(String g : generi){
        %>
        <h3 class="title" align="center"><%=g%></h3>

        <div class="row row-cols-1 row-cols-md-4 g-4">

            <%
                for(Film f : films){
                    if(f.getGenere().equals(g)){

            %>

            <div class="col">
                <div class="card h-100">
                    <a href="#" class="btn btn-primary">+</a>
                    <img src="<%=f.getCopertina()%>" class="card-img-top" alt="immagine non disponibile">
                    <div class="card-body">
                        <h5 class="card-title"><%=f.getTitolo()%></h5>
                        <p class="card-text">Genere: <%=f.getGenere()%></p>
                        <p class="card-text">Regista: <%=f.getRegista()%></p>
                        <p class="card-text">Durata: <%=f.getDurata()%></p>
                        <a href="FilmServlet?id=<%=f.getId()%>" class="btn btn-primary">Dettagli</a>
                        <a href="#" class="btn btn-primary">Lista +</a>
                    </div>
                </div>
            </div>

            <%
                    }
                }
            %>
        </div>
        <%
            }
        %>

    </div>
</body>
</html>
