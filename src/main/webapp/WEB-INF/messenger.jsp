<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<t:layout-connected>
    <jsp:body>
        <jsp:useBean id="action" type="java.lang.String" scope="request" />

        <h2 class="text-primary">Messenger</h2>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav">
                    <li class="nav-item" id="send">
                        <a class="nav-link" href="messenger?action=send">Nouveau</a>
                    </li>
                    <li class="nav-item" id="list">
                        <a class="nav-link" href="messenger?action=list">Messages</a>
                    </li>
                </ul>
            </div>
        </nav>
        <br />
        <c:choose>
            <c:when test="${action.equals('send')}">
                <jsp:useBean id="users" type="java.util.List<model.User>" scope="request" />
                <jsp:useBean id="user" type="java.lang.String" scope="session" />
                <fmt:parseNumber var="user_id" value="${user}" integerOnly="true" />

                <form method="post" action="messenger">
                    <h5>Utilisateur</h5>
                    <div class="form-group w-50 mx-auto">
                        <select id="user-select" name="user-select">

                        <c:forEach var="user" items="${users}">
                            <c:if test="${user.code != user_id}">
                                <option value="${user.id}">${user.firstName} ${user.lastName}</option>
                            </c:if>
                        </c:forEach>
                        </select>

                    </div>
                    <h5>Message</h5>
                    <div class="form-group w-50 mx-auto">
                        <textarea class="form-control" name="text"></textarea>
                    </div>
                    <button class="btn btn-primary">Envoyer</button>
                </form>

            </c:when>
            <c:when test="${action.equals('list')}">
            <jsp:useBean id="messages" type="java.util.List<model.Message>" scope="request" />

                <h3>Liste des messages</h3>
                <c:forEach var="message" items="${messages}">
                    <div>${message.id} ${message.text}</div>
                </c:forEach>
            </c:when>

        </c:choose>
    </jsp:body>
</t:layout-connected>
<script src="static/js/messenger.js"></script>

