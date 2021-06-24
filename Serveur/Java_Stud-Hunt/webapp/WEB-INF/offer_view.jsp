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
    <title>Stud-Hunt-offer view</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/img/logo(sans%20titre).png"/>
</head>

<body>
<%@include file="header.jsp"%>
<div id="bigdiv" class="ui centered grid">
    <div class="ui segment">
        <h1 class="ui header"><i class="star icon"></i>3 points Communs avec cette offre</h1>
        <img class="ui centered medium circular image" src="./img/code-development.svg">

        <div class="ui message">
            <div class="header">
                Titre de l'offre :
            </div>
            <p>Apprenti technicien Informatique</p>
        </div>
        <div class="ui segment">
            <h2>Titre de l'offre :</h2>
            <p style="max-width: 450px;">Lorem ipsum odor amet, consectetuer adipiscing elit. Habitant condimentum urna accumsan dolor blandit velit tempus. Morbi fermentum risus est rutrum; velit tellus. Non commodo consequat cubilia penatibus congue dis tellus ultrices porta. Dolor lectus nascetur tempus posuere malesuada aliquam mi turpis erat. In magna vitae pulvinar condimentum rhoncus mattis eget ornare. Est quis semper mauris vestibulum natoque rhoncus suspendisse ullamcorper nostra.</p>
        </div>
        <div class="ui segment">
            <h4 class="ui header">
                Compétences recherchées:
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