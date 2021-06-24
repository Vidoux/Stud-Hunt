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
    <title>Stud-Hunt-Match</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/img/logo(sans%20titre).png"/>
</head>

<body>
<%@include file="header.jsp"%>
<div id="bigdiv" class="ui centered grid">
    <div class="ui segment">
        <h1 class="ui header"><i class="star icon"></i>3 points Communs avec ce profil</h1>
        <img class="ui centered medium circular image" src="./img/code-development.svg">

        <div class="ui message">
            <div class="header">
                Sa formation
            </div>
            <p>DUT Informatique en Apprentissage à l'Université de Paris Rive Seine</p>
        </div>
        <div class="ui segment">
            <h4 class="ui header">
                Ses projets
            </h4>
            <div class="ui two column very relaxed grid">
                <div class="column">
                    <p>Labyrinthe en Java</p>
                    <p>e-Portfoliio en HTML/CSS</p>
                </div>
                <div class="column">
                    <p>2021</p>
                    <p>2020</p>
                </div>
            </div>
        </div>
        <div class="ui segment">
            <h4 class="ui header">
                Ses compétences
            </h4>
            <div class="ui two column very relaxed grid">
                <div class="column">
                    <p>Optimiste</p>
                    <p>Sérieux</p>
                    <p>Rigoureux</p>
                    <p>Sociable</p>
                </div>
                <div class="column">
                    <p>Impliqué & Attentif</p>
                    <p>Communication</p>
                    <p>Persévérant</p>
                    <p>Discipliné</p>
                </div>
            </div>
        </div>

        <br><br>

        <div class="iu centered grid">
            <button id="NO" class="massive red basic circular ui icon button">
                <i class="close icon"></i>
            </button>

            <button id="YES" class="massive green basic circular ui icon button">
                <i class="check icon"></i>
            </button>
        </div>

    </div>
</div>



<div style="flex-grow:1"></div>

<%@include file="footer.jsp"%>

</body>
<script src="${pageContext.request.contextPath}/scripts/mainScript.js"></script>
<script type="text/javascript">

    let btnyes = document.getElementById('YES');
    let btnno = document.getElementById('NO');
    let bigdiv = document.getElementById('bigdiv');

    btnyes.addEventListener('click', updateBigdivYES);
    btnno.addEventListener('click', updateBigdivNO);

    function updateBigdivYES() {
        bigdiv.setAttribute("style","animation: slideOutRight 0.5s");
        setTimeout(suiteTraitement1, 500);//Effectue une pausse de 1sec
    }

    function updateBigdivNO() {
        bigdiv.setAttribute("style","animation: slideOutLeft 0.5s");
        setTimeout(suiteTraitement1, 500);//Effectue une pausse de 1sec
    }

    function suiteTraitement1() {
        //Continue le traitement après la pause
        bigdiv.setAttribute("style","animation: slideInDown 0.5s");
        setTimeout(suiteTraitement, 500);
    }
    function reset(){
       //ok;
    }
    function suiteTraitement() {
        //Continue le traitement après la pause
        reset();
    }

</script>
</html>