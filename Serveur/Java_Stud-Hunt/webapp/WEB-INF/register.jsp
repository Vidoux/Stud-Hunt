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
    <title>StudHunt-register</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/img/logo(sans%20titre).png"/>
</head>

<body class="littleBody">
<%@include file="header.jsp"%>

<div class="ui container segment small transparent">

    <div class="ui stackable middle aligned grid">
        <div class="two column row">
            <div class="three wide centered column loginLogo">
                <img src="${pageContext.request.contextPath}/img/logo_titre.png">
            </div>
            <div class="thirteen wide column">
                <div class="ui center aligned container loginTitle">
                    <h1 class="ui header">Création d'un nouvel utilisateur</h1>
                </div>
            </div>
        </div>
    </div>
</div>


<form method="post" action="./register">
    <div class="ui form container segment center aligned transparent">
        <h3>Informations de création du compte</h3>
        <div class="inline three fields">
            <div class="grouped fields" id="userTypeCheckbox">
                <label>Vous êtes: </label>
                <div class="field">
                    <div class="ui slider checkbox" id="company_checkbox">
                        <input type="radio" name="type" value="company">
                        <label>Une Entreprise</label>
                    </div>
                </div>
                <div class="field">
                    <div class="ui slider checkbox" id="student_checkbox">
                        <input type="radio" name="type" value="student">
                        <label>Un Étudiant</label>
                    </div>
                </div>
            </div>
            <div class="field">
                <label>nom</label>
                <input type="text" placeholder="nom" name="nom">
            </div>
            <div class="field invisibleDiv" id="prenom_register">
                <label>prénom</label>
                <input type="text" placeholder="prénom" name="prenom">
            </div>
        </div>

        <div class="inline two fields">
            <div class="field">
                <label>adresse email (login de connexion)</label>
                <input type="email" placeholder="xxxxx@mail.fr" name="email">
            </div>
            <div class="field">
                <label>mot de passe</label>
                <input type="password" name="password">
            </div>
<%--            <div class="grouped fields invisibleDiv" id="studentTypeCheckbox">--%>
<%--                <label>Vous recherchez: </label>--%>
<%--                <div class="field">--%>
<%--                    <div class="ui slider checkbox" id="apprentice_checkbox">--%>
<%--                        <input type="checkbox" name="typeStudent" value="apprentice">--%>
<%--                        <label>Un Apprentissage</label>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="field">--%>
<%--                    <div class="ui slider checkbox" id="intern_checkbox">--%>
<%--                        <input type="radio" name="typeStudent" value="intern">--%>
<%--                        <label>Un Stage</label>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>

        <button class="big ui blue button" id="submitButton" type="submit">Enregister</button>
    </div>

</form>


<div style="flex-grow:1"></div>

<%@include file="footer.jsp"%>
</body>
<script src="${pageContext.request.contextPath}/scripts/mainScript.js"></script>
</html>