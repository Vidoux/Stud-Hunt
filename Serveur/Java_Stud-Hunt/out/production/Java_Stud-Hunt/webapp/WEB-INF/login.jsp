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
    <title>StudHunt-login</title>
</head>

<body class="littleBody">

<div class="ui container segment small">

    <div class="ui stackable middle aligned grid">
        <div class="two column row">
            <div class="three wide centered column loginLogo">
                <img src="${pageContext.request.contextPath}/img/logo_titre.png">
            </div>
            <div class="thirteen wide column">
                <div class="ui center aligned container loginTitle">
                    <h1 class="ui header">Authentification</h1>
                    <h4 class="ui header">Veuillez vous connecter pour accéder à votre espace personnel</h4>
                </div>
            </div>
        </div>
    </div>


    <form method="post" action="./login">

        <div class="ui form container center aligned">
            <label>Identifiant: </label>
            <div class="ui input ">
                <input type="text" placeholder="identifiant" name="login">
            </div>
            <div class="ui hidden divider"></div>
            <label>mot de passe: </label>
            <div class="ui input">
                <input type="password" placeholder="mot de passe" name="password">
            </div>
            <div class="ui hidden divider"></div>
            <button class="big ui blue button" id="submitButton" type="submit">Connexion</button>

        </div>

    </form>

</div>
<div style="flex-grow:1"></div>

<%@include file="footer.jsp"%>
</body>
<script src="${pageContext.request.contextPath}/scripts/mainScript.js"></script>
</html>