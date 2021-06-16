<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
    <nav id="desktopMenu">
        <div class="ui fixed blue inverted massive menu">
            <div class="item">
                <img src="images/logo(sans titre).png" alt="logo"/>
            </div>
            <div class="center menu">
                <div class="item" id="titrePage">
                    <h1>Titre de la page</h1>
                </div>
            </div>
            <div class="right menu">
                <!--          Si étudient alors: CV, Demandes de contact-->
                <!--          Si entreprise alors: mes recherches, Prises de Contacts-->
                <a class="active item"></a>
                <a class="item"></a>
                <div class="ui dropdown item">
                    <img class="ui avatar image" src="" />  <!--insérer, dynamiquement l'image du profil-->
                    <i class="dropdown icon"></i>
                    <div class="menu">
                        <a class="item">Modifier le profil</a>
                        <a class="item">Déconnexion</a>
                    </div>
                </div>
            </div>
        </div>
    </nav>
    <nav id="mobileMenu">
        <div id="menu" class="ui top fixed menu">
            <div class="right menu">
                <div class="ui dropdown item">
                    <i class="bars icon"></i>
                    <div class="menu">
                        <a class="item">Electronics</a>
                        <a class="item">Automotive</a>
                        <a class="item">Home</a>
                    </div>
                </div>
            </div>
        </div>

    </nav>

</header>