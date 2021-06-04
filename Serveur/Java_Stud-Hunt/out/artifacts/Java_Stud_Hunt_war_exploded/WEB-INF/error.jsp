<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>Error</title>
    <%--import de la feuille de style Semantic UI--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css">
    <style>
        body{
            margin-top: 100px;
        }
    </style>
</head>

<body>
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