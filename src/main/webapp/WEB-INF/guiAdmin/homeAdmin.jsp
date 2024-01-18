<%--
  Created by IntelliJ IDEA.
  User: Costantino
  Date: 16/01/2024
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Random" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/admin/homeAdmin.css?v=<%=new Random().nextInt()%>"/>
    <title>MovieClub</title>

    <script>
        $(document).ready(function(){
            if ( window.history.replaceState ) { // lo stato precedente (con requisiti) viene resettato
                window.history.replaceState( null, null, window.location.href );
            }
        });
    </script>

</head>
<body>
    <%@ include file="/WEB-INF/navbar/navbarAdmin.jsp" %>

    <%if(request.getAttribute("result")!=null){%>
    <div class="alert" id="alert">
        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
        <%=request.getAttribute("result")%>
    </div>
    <%}%>

    <div class="container">
        <button class="item" id="add_film" value=" " onclick="location.href='MainServletAdmin?action=aggiungi_film'">
            <span class="item-text"></span>
        </button>
        <button class="item" id="manage_films" value=" " onclick="location.href='MainServletAdmin?action=gestisci_film'">
            <span class="item-text"></span>
        </button>
        <button class="item" id="users" value=" " onclick="location.href='MainServletAdmin?action=utenti'">
            <span class="item-text"></span>
        </button>
    </div>

</body>
</html>


