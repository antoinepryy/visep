<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<t:layout-connected>
    <jsp:attribute name="head">
    </jsp:attribute>
    <jsp:body>
        <h3>Association</h3>
        <jsp:useBean id="association" type="model.Association" scope="request"/>
        ${association.name}
        ${association.description}
        ${association.recruitment}

    </jsp:body>
</t:layout-connected>