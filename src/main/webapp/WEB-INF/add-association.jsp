
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout-connected>
    <jsp:attribute name="head">
    </jsp:attribute>
    <jsp:body>
        <div>
            <div class="text-center m-auto">
                <div class="py-3">
                    <h1>VISEP</h1>
                    <h2>Le réseau social made in ISEP !</h2>
                </div>

                <div class="container">
                    <form method="post" action="admin?action=addassociation">
                        <div class="form-group w-50 mx-auto">
                            <input class="form-control" type="text" name="name"  required placeholder="Nom de l'association">
                        </div>
                        <div class="form-group w-50 mx-auto">
                            <input class="form-control" type="text" name="description"  required placeholder="Description de l'association">
                        </div>
                        <div class="form-group w-50 mx-auto">
                            <input class="form-control" type="text" name="recruitment"  required placeholder="Procédure de recrutement">
                        </div>


                        <button class="btn btn-primary">Intégrer l'asso</button>



                    </form>
                </div>

            </div>
        </div>
    </jsp:body>
</t:layout-connected>
