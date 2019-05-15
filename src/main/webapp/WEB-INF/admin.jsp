<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<t:layout-connected>
    <jsp:body>
        <div>
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-left">
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="admin?action=assos">Assos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="admin?action=admins-assos">Admins Assos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="admin?action=add-asso">Nouvelle Asso</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="admin?action=admins-visep">Admins Visep</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <br />
            <jsp:useBean id="action" type="java.lang.String" scope="request" />
            <c:choose>
                <c:when test="${action.equals('assos')}">

                </c:when>
                <c:when test="${action.equals('admins-assos')}">

                </c:when>
                <c:when test="${action.equals('add-asso')}">
                    <h2>Créer une association</h2>
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
                    <h3>Admins Visep</h3>
                    <div class="container">
                    <jsp:useBean id="visepAdmins" type="java.util.List<model.User>" scope="request" />
                    <c:forEach var="visepAdmin" items="${visepAdmins}">
                        <div class="row">
                            <div class="col">
                                <p>${visepAdmin.firstName}</p>
                            </div>
                            <div class="col">
                                <p>${visepAdmin.lastName}</p>
                            </div>
                            <div class="col">
                                <p>${visepAdmin.code}</p>
                            </div>
                            <div class="col">
                                <form method="post" action="admin?action=del-visep-admin">
                                    <button class="btn btn-sm btn-danger" name="code" value="${visepAdmin.code}">Supprimer</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                        <br />
                        <h3>Ajouter un nouvel admin Visep</h3>
                        <form method="post" action="admin?action=add-visep-admin">
                            <div class="row">
                                <div class="col">
                                    <input class="form-control" type="text" name="fName" placeholder="Prénom" required />
                                </div>
                                <div class="col">
                                    <input class="form-control" type="text" name="lName" placeholder="Nom" required />
                                </div>
                                <div class="col">
                                    <button class="btn btn-primary">Ajouter</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </jsp:body>
</t:layout-connected>
