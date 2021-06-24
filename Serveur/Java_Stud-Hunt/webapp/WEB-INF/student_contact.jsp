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
    <title>Stud-Hunt-contact</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/img/logo(sans%20titre).png"/>
</head>

<body>
<%@include file="header.jsp"%>

<div class="bigdiv ui centered stackable grid">
    <div class="ui nine wide column center aligned segment transparent">
        <h1>Consultez les entreprises souhaitant travailler avec vous</h1>
    </div>
    <div class="nine wide column">
        <table class="ui celled table">
            <thead>
                <tr>
                    <th>Titre de l'offre</th>
                    <th>Nom de l'entreprise</th>
                    <th>Accéder aux profils correspondants: </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td data-label="title">Apprenti technicien Informatique</td>
                    <td data-label="company">Orange</td>
                    <td data-label="button">
<%--                        Id= dynamique (TODO)--%>
                        <a href="./contact_view?id=1">
                            <button class="ui blue button">Voir l'offre</button>
                        </a>
                    </td>
                </tr>
            </tbody>
        </table>
        </div>
</div>
<br><br>
<div style="flex-grow:1"></div>

<%@include file="footer.jsp"%>

</body>
<script src="${pageContext.request.contextPath}/scripts/mainScript.js"></script>
</html>