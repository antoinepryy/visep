<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<t:layout-connected>
    <jsp:attribute name="head">
            <link rel="stylesheet" href="static/css/page.css">
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
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:layout-connected>