<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<t:layout-connected>
    <jsp:attribute name="head">
            <link rel="stylesheet" href="static/css/page.css">
    </jsp:attribute>
    <jsp:body>
        <h1>Associations</h1>
        <div class="container">
        <jsp:useBean id="associations" type="java.util.List<model.Association>" scope="request"/>
        <c:forEach var="association" items="${associations}">
                <div class="row">
                    <div class="col-2">
                        <h5>${association.name}</h5>
                    </div>
                    <div class="col-10">
                        <p>${association.description}</p>
                    </div>
                </div>
        </c:forEach>
        </div>
    </jsp:body>
</t:layout-connected>

