<%@ page import="application.entity.Lista" %>
<%@ page import="application.entity.Film" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 16/01/2024
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%
    Lista l = (Lista) request.getAttribute("lists");
    ArrayList<Film> films = (ArrayList<Film>) request.getAttribute("films");
  %>

  <title>Informazioni Lista: <%= l.getNome()%></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="./css/liste.css?v=<%=new Random().nextInt()%>"/>
  <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<%@ include file="/WEB-INF/navbar/navbar.jsp" %>

<%
  for (Film f: films){
%>
<form action="" method="post">
<%--  <input type="hidden" name="id" value="<%=l.getId()%>"/>--%>
  Nome film: <%=f.getTitolo()%>
</form>
<%
  }
%>


</body>
</html>
