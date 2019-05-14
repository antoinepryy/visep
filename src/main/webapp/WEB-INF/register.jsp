
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout-disconnected>
    <jsp:attribute name="header">
            <link rel="stylesheet" href="static/css/register.css">
    </jsp:attribute>
    <jsp:body>
        <div>
            <div class="text-center m-auto">
                <div class="py-3">
                    <h1>VISEP</h1>
                    <h2>Le réseau social made in ISEP !</h2>
                </div>

                <div class="container">
                    <form method="post" action="register">
                        <div class="row">
                            <div class="col">
                                <div class="form-group w-50 mx-auto">
                                    <input class="form-control" type="text" name="fName"  required placeholder="Prénom">
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group w-50 mx-auto">
                                    <input class="form-control" type="text" name="lName" required placeholder="Nom">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group w-50 mx-auto">
                                    <input  class="form-control" name="password" type="password" required placeholder="Mot de passe">
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group w-50 mx-auto">
                                    <input  class="form-control" name="confPassword" type="password" required placeholder="Confimer mot de passe">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group w-50 mx-auto">
                                    <input  class="form-control" name="code" type="number" required placeholder="Code ISEP">
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group w-50 mx-auto">
                                    <input  class="form-control" name="mail" type="text" required placeholder="Email">
                                </div>
                            </div>
                        </div>

                        <button class="btn btn-primary">S'enregistrer</button>



                    </form>
                </div>

            </div>
        </div>
    </jsp:body>
</t:layout-disconnected>
