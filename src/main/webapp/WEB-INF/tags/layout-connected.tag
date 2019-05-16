<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="static/css/app.css">
    <link rel="shortcut icon" href="static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="static/favicon.ico" type="image/x-icon">
    <jsp:invoke fragment="head"/>

</head>
<body id="particles-js" style="background-color: #eceff1">

<div id="body" class="center-inside">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary" >
        <a class="navbar-brand" href="home">VISEP</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="home">Accueil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="association">Les Assos'</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="messenger">Messagerie</a>
                </li>
                <% if (session.getAttribute("isAdmin").equals(true)) { %>
                <li class="nav-item">
                    <a class="nav-link" href="admin">Admin</a>
                </li>
                <% }; %>
                <li class="nav-item">
                    <a class="nav-link" href="logout">Déconnexion</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="rounded bg-white" >
        <jsp:doBody/>

    </div>
</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/particles.js@2.0.0/particles.min.js"></script>
<script src="static/js/app.js"></script>
</html>