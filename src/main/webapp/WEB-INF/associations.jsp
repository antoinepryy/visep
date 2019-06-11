<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<t:layout-connected>
    <jsp:attribute name="head">
            <link rel="stylesheet" href="static/css/associations.css">
    </jsp:attribute>
    <jsp:body>
        <h3>Associations</h3>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Nom</th>
                <th scope="col">Description</th>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="associations" type="java.util.List<model.Association>" scope="request"/>
            <c:forEach var="association" items="${associations}">
                <tr>
                    <td>${association.name}</td>
                    <td>${association.description}</td>
                    <td><a href="association?name=${association.name}" class="badge badge-secondary">Plus d'infos</a></td>
                    <td><input type="checkbox" id="${association.id}" /><label for="${association.id}" class="badge badge-primary"><p class="suivre">Suivre</p><p class="suivi">Suivi</p></label></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:layout-connected>
<script src="static/js/associations.js"></script>