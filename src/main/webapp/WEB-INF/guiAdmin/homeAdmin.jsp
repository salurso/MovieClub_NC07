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
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>--%>
<%--    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>--%>
<%--    <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>--%>
    <link rel="stylesheet" type="text/css" href="./css/admin/homeAdmin.css?v=<%=new Random().nextInt()%>"/>
    <title>Forneria Del Cilento</title>

<%--    <script>--%>
<%--        $(document).ready(function(){--%>
<%--            if ( window.history.replaceState ) { // lo stato precedente (con requisiti) viene resettato--%>
<%--                window.history.replaceState( null, null, window.location.href );--%>
<%--            }--%>
<%--        });--%>
<%--    </script>--%>

</head>
<body>

<div>
        <%if(request.getAttribute("result")!=null){%>
    <div class="alert" id="alert">
        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
        <%=request.getAttribute("result")%>
    </div>
        <%}%>
    <div class="container">
        <button class="item" id="add_product" value=" " onclick="location.href='HomeServletAdministrator?action=add_product'">
            <span class="item-text"></span>
        </button>
        <button class="item" id="manage_product" value=" " onclick="location.href='HomeServletAdministrator?action=manage_product'">
            <span class="item-text"></span>
        </button>
        <button class="item" id="add_category" value=" " onclick="location.href='HomeServletAdministrator?action=add_category'">
            <span class="item-text"></span>
        </button>
        <button class="item" id="manage_category" value=" " onclick="location.href='HomeServletAdministrator?action=manage_category'">
            <span class="item-text"></span>
        </button>
        <button class="item" id="orders" value=" " onclick="location.href='HomeServletAdministrator?action=orders'">
            <span class="item-text"></span>
        </button>
        <button class="item" id="users" value=" " onclick="location.href='HomeServletAdministrator?action=users'">
            <span class="item-text"></span>
        </button>
    </div>
</body>
</html>



