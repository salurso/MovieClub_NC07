<%--
  Created by IntelliJ IDEA.
  User: Vittorio
  Date: 16/01/2024
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrazione</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="./css/admin/registrazione.css">
</head>
<body>

<div class="mb-3 row">
    <label for="inputNome" class="col-sm-2 col-form-label">Nome</label>
    <div class="col-sm-10">
        <input type="text" class="form-control" id="inputNome" placeholder="Inserisci il tuo nome" required>
    </div>
</div>
<div class="mb-3 row">
    <label for="inputCognome" class="col-sm-2 col-form-label">Cognome</label>
    <div class="col-sm-10">
        <input type="text" class="form-control" id="inputCognome" placeholder="Inserisci il tuo cognome" required>
    </div>
</div>
<div class="mb-3 row">
    <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
    <div class="col-sm-10">
        <input type="text" class="form-control" id="inputEmail" value="email@example.com">
    </div>
</div>

<div class="mb-3 row">
    <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-10">
        <input type="password" class="form-control" id="inputPassword" required>
        <div class="invalid-feedback">
            Inserisci una password.
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
