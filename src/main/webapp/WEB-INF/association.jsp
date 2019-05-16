<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<t:layout-connected>
    <jsp:body>
        <jsp:useBean id="association" type="model.Association" scope="request" />
        <h2 class="text-primary"><jsp:getProperty name="association" property="name"/></h2>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav">
                    <li class="nav-item" id="description">
                        <a class="nav-link" href="association?name=${association.name}&action=description">Description</a>
                    </li>
                    <li class="nav-item" id="members">
                        <a class="nav-link" href="association?name=${association.name}&action=members">Membres</a>
                    </li>
                </ul>
            </div>
        </nav>
        <br />
        <jsp:useBean id="action" type="java.lang.String" scope="request" />
        <c:choose>
            <c:when test="${action.equals('description')}">
                <h5>Description de l'association</h5>
                <p><jsp:getProperty name="association" property="description"/></p>
                <h5>Procédure de recrutement</h5>
                <p><jsp:getProperty name="association" property="recruitment"/></p>
            </c:when>
            <c:when test="${action.equals('members')}">
                <table class="table">
                    <tbody>
                        <jsp:useBean id="members" type="java.util.List<model.User>" scope="request" />
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
    </jsp:body>
</t:layout-connected>
<script src="static/js/association.js"></script>
