<%--
  Created by IntelliJ IDEA.
  User: Costantino
  Date: 01/02/2024
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Random" %>

<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="./css/admin/visualizzaUtenti.css?v=<%=new Random().nextInt()%>"/>
  <title>Utenti</title>
</head>
<body>
  <%@ include file="/WEB-INF/navbar/navbarAdmin.jsp" %>

  <div>
    <div class="home_ord">
      <h3> UTENTI </h3>
    </div>
<%--    <h1 class="main-title"> Ecco la lista di tutti gli utenti  </h1>--%>
    <div class="tabular--wrapper">
      <div class="table-container">
        <%
          ArrayList<Persona> persone = (ArrayList<Persona>) request.getAttribute("persone");
        %>
        <div style="overflow-x:auto;">
          <table>
            <thead>
            <tr>
              <th>Nome</th>
              <th>Cognome</th>
              <th>Email</th>
              <th>Admin</th>
            </tr>
            </thead>
              <%
               for(Persona p : persone){
              %>
              <tbody>
              <tr>
                <td><%=p.getNome()%></td>
                <td><%=p.getCognome()%></td>
                <td class="emailUser" id="<%=p.getEmail()%>"><%=p.getEmail()%></td>
                <td class="isAdmin"><%=p.isAdmin()%></td>
              </tr>

              </tbody>
              <%
                }
              %>
          </table>
        </div>
      </div>
    </div>
  </div>

</body>
</html>

