<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<t:layout-connected>
    <jsp:body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav">
                <li class="nav-item" id="assos">
                    <a class="nav-link" href="admin?action=assos">Assos</a>
                </li>
                <li class="nav-item" id="add-asso">
                    <a class="nav-link" href="admin?action=add-asso">Nouvelle Asso</a>
                </li>
                <li class="nav-item" id="admins-visep">
                    <a class="nav-link" href="admin?action=admins-visep">Admins Visep</a>
                </li>
            </ul>
        </div>
        </nav>
        <jsp:useBean id="action" type="java.lang.String" scope="request" />
        <c:choose>
            <c:when test="${action.equals('assos')}">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Asso</th>
                            <th scope="col" colspan="3">Admin</th>
                        </tr>
                    </thead>
                    <tbody>
                        <jsp:useBean id="associations" type="java.util.List<model.Association>" scope="request" />
                        <c:forEach var="association" items="${associations}">
                            <tr>
                                <form method="post" action="admin?action=change-admin-asso">
                                    <td>${association.name}</td>
                                    <td><input class="form-control" type="text" name="fName" value="${association.admin.firstName}" /></td>
                                    <td><input class="form-control" type="text" name="lName" value="${association.admin.lastName}" /></td>
                                    <td><button class="btn btn-sm btn-primary" name="association" value="${association.name}">Changer l'admin</button></td>
                                </form>
                                <form method="post" action="admin?action=del-asso">
                                    <td><button class="btn btn-sm btn-danger" name="association" value="${association.name}">Supprimer l'asso</button></td>
                                </form>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:when test="${action.equals('add-asso')}">
                <br />
                <h3>Créer une association</h3>
                <br />
                <div class="container">
                    <form method="post" action="admin?action=add-asso">
                        <div class="form-group w-50 mx-auto">
                            <input class="form-control" type="text" name="name"  required placeholder="Nom de l'association" />
                        </div>
                        <div class="form-group w-50 mx-auto">
                            <input class="form-control" type="text" name="description"  required placeholder="Description de l'association" />
                        </div>
                        <div class="form-group w-50 mx-auto">
                            <input class="form-control" type="text" name="recruitment"  required placeholder="Procédure de recrutement" />
                        </div>
                        <button class="btn btn-primary">Créer l'asso</button>
                    </form>
                </div>
            </c:when>
            <c:when test="${action.equals('admins-visep')}">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col" colspan="4">Admins Visep</th>
                        </tr>
                    </thead>
                    <tbody>
                        <jsp:useBean id="visepAdmins" type="java.util.List<model.User>" scope="request" />
                        <c:forEach var="visepAdmin" items="${visepAdmins}">
                            <tr>
                                <td>${visepAdmin.firstName}</td>
                                <td>${visepAdmin.lastName}</td>
                                <td>${visepAdmin.code}</td>
                                <form method="post" action="admin?action=del-visep-admin">
                                    <td><button class="btn btn-sm btn-danger" name="code" value="${visepAdmin.code}">Supprimer</button></td>
                                </form>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <thead>
                        <tr>
                            <th scope="col" colspan="4">Ajouter un nouvel admin Visep</th>
                        </tr>
                    </thead>
                    <tbody>
                        <form method="post" action="admin?action=add-visep-admin">
                            <td><input class="form-control" type="text" name="fName" placeholder="Prénom" required /></td>
                            <td><input class="form-control" type="text" name="lName" placeholder="Nom" required /></td>
                            <td><button class="btn btn-primary">Ajouter</button></td>
                        </form>
                    </tbody>
                </table>
            </c:when>
        </c:choose>

    </jsp:body>
</t:layout-connected>
<script src="static/js/admin.js"></script>