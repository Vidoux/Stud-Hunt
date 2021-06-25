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
    <title>Stud-Hunt</title>
</head>

<body>
<%@include file="header.jsp"%>

<div class="pusher">
    <div class="ui vertical masthead center aligned segment headerSegment">
        <div class="ui text container">
            <h1 class="ui inverted header">
                Stud-Hunt
            </h1>
            <h2>Trouvez l’étudiant qu’il vous faut</h2>
        </div>

    </div>

    <div class="ui center aligned vertical stripe segment" id="droite">
        <div class="ui middle aligned stackable grid container">
            <div class="row">
                <div class="eight wide column">
                    <h2 class="ui blue header">Une aide aux entreprises et aux etudiants dans leurs recherches</h2>
                    <p>Nous savons à quel point il est important pour vous d'avoir le collabotateur le plus adapté au valeurs de votre entreprise. Et a quel point il est important pour un salarié de se sentir valorisé par les missions confiés et réalisées</p>
                </div>
                <div class="six wide right floated column">
                    <img src="./img/loupe.png" class="ui centered small rounded image">
                </div>
            </div>
            <div class="row">
                <div class="center aligned column">
                    <a class="ui huge teal button" href="./register">Commencer</a>
                </div>
            </div>
        </div>
    </div>


    <div class="ui center aligned vertical stripe quote segment" id="gauche">
        <H2 class="blue centered">Quelques avis</H2>
        <div class="ui equal width stackable internally celled grid">
            <div class="center aligned row">

                <div class="column">
                    <h3>"Quelle site ! Après 5 mois de recherche j'ai trouvé à peine en 1 semaine avec ce site."</h3>
                    <p>M.Lopez</p>
                </div>
                <div class="column">
                    <h3>"J'ai pu trouver mon stage de fin d'études sans rien faire, c'est l'entreprise qui est venue à moi"</h3>
                    <p>M.Sacko</p>
                </div>
            </div>
        </div>
    </div>

    <div class="ui center aligned vertical stripe segment" id="droite">
        <div class="ui middle aligned stackable grid container">
            <div class="row">
                <div class="eight wide column">
                    <h2 class="ui blue header">Ne perdez plus votre temps</h2>
                    <p>On sait que votre temps est précieux, notre algorithme se charge pour vous de mettre en relation entreprise et étudiants avec une efficacité optimale.</p>
                    <!-- <h3 class="ui header">Tout le monde à sa chance</h3>
                    <p>Nos mises en relation se fait de manière non discriminatoire, peu importe vos particularités ou d'où vous venez</p> -->
                </div>
                <div class="six wide right floated column">
                    <img src="./img/sablier.svg" class="ui centered small rounded image">
                </div>
            </div>
            <!-- <div class="row">
                <div class="center aligned column">
                    <a class="ui huge button">Commencer</a>
                </div>
            </div> -->
        </div>
    </div>

    <div class="ui center aligned vertical stripe segment" id="gauche">
        <div class="ui middle aligned stackable grid container">
            <div class="row">
                <div class="eight wide column">
                    <h2 class="ui blue header">Venez comme vous êtes</h2>
                    <h2 class="ui blue header">Tout le monde à sa chance</h2>
                    <p>Nos mises en relation se font de manière non discriminatoire, peu importe vos particularités ou d'où vous venez, vous avez votre opportunité à portée de main.</p>
                </div>
                <div class="six wide right floated column">
                    <img src="./img/trefle.svg" class="ui centered small rounded image">
                </div>
            </div>
            <!-- <div class="row">
                <div class="center aligned column">
                    <a class="ui huge button">Commencer</a>
                </div>
            </div> -->
        </div>
    </div>






</div>

<div style="flex-grow:1"></div>

<%@include file="footer.jsp"%>
</body>
<script src="${pageContext.request.contextPath}/scripts/mainScript.js"></script>
</html>