
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout-connected>
    <jsp:attribute name="head">
        <link href='static/core/main.css' rel='stylesheet' />
        <link href='static/daygrid/main.css' rel='stylesheet' />
    </jsp:attribute>
    <jsp:body>
        <div>
            <div id='calendar'></div>
        </div>
    </jsp:body>
</t:layout-connected>
<script src='static/core/main.js'></script>
<script src='static/daygrid/main.js'></script>
<script src="static/js/home.js"></script>
