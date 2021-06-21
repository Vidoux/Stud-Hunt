<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.3.1/dist/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/semantic/dist/semantic.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/custom.css">
    <script src="${pageContext.request.contextPath}/semantic/dist/semantic.min.js"></script>
    <title>Title</title>
</head>

<body class="littleBody">
<div class="ui container segment small">

    <div class="ui container center aligned">
        <h1>Erreur :</h1>
        <%--Affichage de l'erreur qui a provoquée l'affichage de la page--%>
        <h2 style="color: red;"><c:out value="${errorMessage}">No Error description</c:out></h2>
        <%--Retour à la page de connexion--%>
        <form method="get" action="<c:out value="${backDestination}"></c:out>">
            <button class="big ui teal button"  type="submit">Retour</button>
        </form>
    </div>
</div>
</body>
</html>