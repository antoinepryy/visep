<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout-connected>
    <jsp:body>
        <div>
            <div class="py-3">
                <h2>Créer une association</h2>
            </div>
            <form method="post" action="admin">
                <div class="form-group w-50 mx-auto">
                    <input class="form-control" type="text" name="name" placeholder="Nom de l'association" required />
                </div>
                <div class="form-group w-50 mx-auto">
                    <textarea class="form-control" name="description" placeholder="Description"></textarea>
                </div>
                <div class="form-group w-50 mx-auto">
                    <textarea class="form-control" type="textarea" name="recruitment" placeholder="Recrutement"></textarea>
                </div>
                <button class="btn btn-primary">Créer</button>
            </form>

        </div>
    </jsp:body>
</t:layout-connected>
