<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css">
    <link rel="stylesheet" href="style/custom.css">
    <title>Title</title>
</head>

<body id="loginBody">
<div class="ui container segment small">

    <div class="ui stackable middle aligned grid">
        <div class="two column row">
            <div class="three wide centered column loginLogo">
                <img src="images/logo_titre.png">
            </div>
            <div class="thirteen wide column">
                <div class="ui center aligned container loginTitle">
                    <h1 class="ui header">Authentification</h1>
                    <h4 class="ui header">Veuillez vous connecter pour accéder à votre espace personnel</h4>
                </div>
            </div>
        </div>
    </div>


    <form method="post" action="/login">

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
            <button class="big ui teal button" id="submitButton" type="submit">Connexion</button>

        </div>

    </form>

</div>
</div>
</body>
</html>