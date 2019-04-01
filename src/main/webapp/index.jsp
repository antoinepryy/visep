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
    <script src="https://cdn.jsdelivr.net/npm/particles.js@2.0.0/particles.min.js"></script>
</head>
<body id="particles-js" class="w-100 h-100">
<div class="w-100 h-100 box-center">
    <div class="text-center m-auto">
        <h1>VISEP</h1>
        <h2>Le réseau social made in ISEP !</h2>
        <form method="post" action="Authentification">
            <div>
                <input type="text">
            </div>
            <div>
                <input type="password">
            </div>
            <div>
                <input type="submit">
            </div>


        </form>
    </div>
</div>

</body>
<script src="static/js/app.js"></script>
<script>
    particlesJS.load('particles-js', 'static/particles.json', function() {
        console.log('callback - particles.js config loaded');
    });
</script>
</html>
