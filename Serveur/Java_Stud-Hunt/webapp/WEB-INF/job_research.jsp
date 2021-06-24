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
    <title>Stud-Hunt-job research</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/img/logo(sans%20titre).png"/>
</head>

<body>
<%@include file="header.jsp"%>

<div class="bigdiv ui centered stackable grid">
    <div class="ui nine wide column center aligned segment transparent">
        <h1>Consultez et renseignez de nouvelles recherches de profils</h1>
    </div>
    <div class="nine wide column">
        <div class="ui center aligned segment transparent">
            <h2>Vos recherches</h2>
            <table class="ui celled table">
                <thead>
                    <tr>
                        <th>Titre de la recherche</th>
                        <th>Accéder aux profils correspondants: </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td data-label="title">Apprenti technicien Informatique</td>
                        <td data-label="button">
                            <a href="./match?id=1">
                                <button class="ui blue button">Voir les profils</button>
                            </a>

                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="ui center aligned segment transparent">
            <h2>Créer une nouvelle recherche :  </h2>
            <div class="ui message">
                <div class="header">
                    Conseil
                </div>
                <p>Une bonne recherche est une recherche précise et complète, elle vous permettra d'avoir de meilleurs profils</p>
            </div>
            <form class="ui form" method="post" action="./job_research">
                <h4 class="ui dividing header">Information</h4>
                <div class="field">
                    <label>Titre de l'offre</label>
                        <div class="field">
                            <input type="text" name="title" placeholder="Titre de votre offre">
                        </div>
                </div>

                <div class="inline field">
                    <label for="level">Votre niveau d'étude: bac +</label>
                    <input type="number" name="level" id="level" placeholder="1,2,3,4,5, ...">
                </div>
                <div class="inline field">
                    <label for="industry">Secteur d'activité </label>
                    <input type="text" name="industry" id="industry" placeholder="automobile, aviation, ...">
                </div>
                <div class="grouped fields" id="contractTypeCheckbox">
                    <label>Type de contrat</label>
                    <div class="field">
                        <div class="ui slider checkbox" id="company_checkbox">
                            <input type="radio" name="type" value="apprenticeship">
                            <label>Apprentissage</label>
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui slider checkbox" id="student_checkbox">
                            <input type="radio" name="type" value="internship">
                            <label>Stage</label>
                        </div>
                    </div>
                </div>
                <div class="inline field">
                    <label for="date">Date de début du contrat</label>
                    <input type="date" name="date" id="date">
                </div>
                <div class="inline field">
                    <label for="duration">Durée du contrat</label>
                    <input type="number" name="duration" id="duration" placeholder="12">
                    <div class="ui basic label">
                        mois
                    </div>
                </div>
                <button class="big ui blue fluid button" id="submitButton" type="submit">Valider</button>
            </form>
        </div>
    </div>
</div>
<br><br>
<div style="flex-grow:1"></div>

<%@include file="footer.jsp"%>

</body>
<script src="${pageContext.request.contextPath}/scripts/mainScript.js"></script>
</html>