

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout-disconnected>
    <jsp:body>
        <div>
            <div class="py-3">
                <h1>VISEP</h1>
                <h2>Le réseau social made in ISEP !</h2>
            </div>

            <form method="post" action="login">
                <div class="form-group w-50 mx-auto">
                    <input class="form-control" type="number" name="username" placeholder="Identifiant" required>
                </div>
                <div class="form-group w-50 mx-auto">
                    <input  class="form-control" name="password" type="password" placeholder="Mot de passe">
                </div>

                <a href="http://localhost:8080/visep_war_exploded/register">Pas de compte ? Inscrivez vous !</a>


                <button class="btn btn-primary">Se connecter</button>



            </form>
        </div>
    </jsp:body>
</t:layout-disconnected>
