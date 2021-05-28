<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>Login</title>
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

        <div class="ui container step">
            <div class="ui center aligned container">
                <h1 class="ui header">Authentification</h1>
                <h4 class="ui header">Veuillez vous connecter pour accéder à l'interface de gestion</h4>
            </div>
            <div class="ui section divider"></div>

            <%--Formulaire envoyant les données via une requête HTTP POST--%>
            <form method="post" action="./login_frm">

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
                    <button class="big ui teal button" id="submitButton" type="submit">Submit !</button>

                </div>

            </form>

        </div>
    </div>
</body>
</html>














