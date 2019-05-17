<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<t:layout-connected>
    <jsp:body>
        <jsp:useBean id="association" type="model.Association" scope="request" />
        <jsp:useBean id="action" type="java.lang.String" scope="request" />
        <jsp:useBean id="isAdmin" type="java.lang.Boolean" scope="request" />
        <h2 class="text-primary"><jsp:getProperty name="association" property="name"/></h2>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav">
                    <li class="nav-item" id="description">
                        <a class="nav-link" href="association?name=${association.name}&action=description">Description</a>
                    </li>
                    <li class="nav-item" id="events">
                        <a class="nav-link" href="association?name=${association.name}&action=events">Events</a>
                    </li>
                    <li class="nav-item" id="members">
                        <a class="nav-link" href="association?name=${association.name}&action=members">Membres</a>
                    </li>
                </ul>
            </div>
        </nav>
        <br />
        <c:choose>
            <c:when test="${action.equals('description')}">
                <c:choose>
                    <c:when test="${isAdmin}">
                        <form method="post" action="association?name=${association.name}&action=change-description">
                            <h5>Description de l'association</h5>
                            <div class="form-group w-50 mx-auto">
                                <textarea class="form-control" name="description">${association.description}</textarea>
                            </div>
                            <h5>Procédure de recrutement</h5>
                            <div class="form-group w-50 mx-auto">
                                <textarea class="form-control" name="recruitment">${association.recruitment}</textarea>
                            </div>
                            <button class="btn btn-primary">Modifier</button>
                        </form>
                    </c:when>
                    <c:when test="${!isAdmin}">
                        <h5>Description de l'association</h5>
                        <p><jsp:getProperty name="association" property="description"/></p>
                        <h5>Procédure de recrutement</h5>
                        <p><jsp:getProperty name="association" property="recruitment"/></p>
                    </c:when>
                </c:choose>
            </c:when>
            <c:when test="${action.equals('members')}">
                <jsp:useBean id="members" type="java.util.List<model.User>" scope="request" />
                <c:choose>
                    <c:when test="${isAdmin}">
                        <table class="table">
                            <thead>
                                <tr scope="col">
                                    <th>Membres</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="member" items="${members}">
                                    <tr>
                                        <td>${member.firstName}</td>
                                        <td>${member.lastName}</td>
                                        <form method="post" action="association?name=${association.name}&action=del-member">
                                            <td><button class="btn btn-sm btn-danger" name="memberCode" value="${member.code}">Supprimer</button></td>
                                        </form>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <thead>
                                <tr scope="col">
                                    <th>Ajouter un membre</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <form method="post" action="association?name=${association.name}&action=add-member">
                                        <td><input class="form-control" type="text" name="fName" placeholder="Prénom"/></td>
                                        <td><input class="form-control" type="text" name="lName" placeholder="Nom"/></td>
                                        <td><button class="btn btn-sm btn-primary">Ajouter</button></td>
                                    </form>
                                </tr>
                            </tbody>
                        </table>
                    </c:when>
                    <c:when test="${!isAdmin}">
                        <table class="table">
                            <tbody>
                                <c:forEach var="member" items="${members}">
                                    <tr>
                                        <td>${member.firstName}</td>
                                        <td>${member.lastName}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                </c:choose>
            </c:when>
            <c:when test="${action.equals('events')}">
                <jsp:useBean id="events" type="java.util.List<model.Event>" scope="request" />
                <c:choose>
                    <c:when test="${isAdmin}">
                        <table class="table">
                            <thead>
                            <tr scope="col">
                                <th>Events</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="event" items="${events}">
                                <tr>
                                    <td>${event.dateEvent}</td>
                                    <td>${event.description}</td>
                                    <form method="post" action="association?name=${association.name}&action=del-event">
                                        <td><button class="btn btn-sm btn-danger" name="event_id" value="${event.id}">Supprimer</button></td>
                                    </form>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <thead>
                            <tr scope="col">
                                <th>Créer un event</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <form method="post" action="association?name=${association.name}&action=add-event">
                                    <td><input class="form-control" type="date" name="date" placeholder="Date" required/></td>
                                    <td><input class="form-control" type="text" name="description" placeholder="Description" required/></td>
                                    <td><button class="btn btn-sm btn-primary">Créer</button></td>
                                </form>
                            </tr>
                            </tbody>
                        </table>
                    </c:when>
                    <c:when test="${!isAdmin}">
                        <table class="table">
                            <tbody>
                            <c:forEach var="event" items="${events}">
                                <tr>
                                    <td>${event.dateEvent}</td>
                                    <td>${event.description}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                </c:choose>
            </c:when>
        </c:choose>
    </jsp:body>
</t:layout-connected>
<script src="static/js/association.js"></script>
