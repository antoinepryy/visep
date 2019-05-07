

<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>VISEP</title>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="static/css/app.css">
    <link rel="stylesheet" href="static/css/index.css">
    <link rel="shortcut icon" href="static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="static/favicon.ico" type="image/x-icon">
</head>
<body  class="w-100 h-100" style="background-color: red">
<div id="particles-js">
    <div class="login-form">
        <div class="text-center m-auto">
            <div class="py-3">
                <h1>VISEP</h1>
                <h2>Le réseau social made in ISEP !</h2>
            </div>
            <a href="http://localhost:8080/visep_war_exploded/home">LINK</a>

            <form method="post" action="login">
                <div class="form-group w-50 mx-auto">
                    <input class="form-control" type="text" name="username"  placeholder="Identifiant">
                </div>
                <div class="form-group w-50 mx-auto">
                    <input  class="form-control" name="password" type="password" placeholder="Mot de passe">
                </div>

                <button class="btn btn-primary">Se connecter</button>



            </form>
        </div>
    </div>
</div>


</body>
<script src="https://cdn.jsdelivr.net/npm/particles.js@2.0.0/particles.min.js"></script>
<script src="static/js/app.js"></script>
</html>
