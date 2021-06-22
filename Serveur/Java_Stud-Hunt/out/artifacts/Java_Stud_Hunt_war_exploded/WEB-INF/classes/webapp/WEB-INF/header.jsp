<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
    <nav id="desktopMenu">
        <div class="ui fixed blue inverted massive menu">
            <a href="./">
                <div class="item">
                    <img src="./img/logo_blanc.png" alt="logo"/>
                </div>
            </a>
            <div class="center menu">

            </div>
            <div class="right menu">
                <!--          Si étudient alors: CV, Demandes de contact-->
                <div class="<c:if test="${ sessionScope.user.getUserType() == UserTypes.COMPANY }">invisibleDiv</c:if>">
                    <a class="item">Mes information</a>
                    <a class="item">Demandes de contact</a>
                </div>
                <!--          Si entreprise alors: mes recherches, Prises de Contacts-->
                <div class="<c:if test="${ sessionScope.user.getUserType() == UserTypes.STUDENT }">invisibleDiv</c:if>">
                    <a class="item">Votre Entreprise</a>
                    <a class="item">Vos Recherches</a>
                </div>

                <a class="item"><c:out value="${sessionScope.user.getUsername()}" /></a>
                <div class="ui dropdown item <c:if test="${ sessionScope.user == null }">invisibleDiv</c:if>">
                    <img class="ui avatar image" src="" />  <!--insérer, dynamiquement l'image du profil-->
                    <i class="dropdown icon"></i>
                    <div class="menu">
                        <a class="item">Modifier le profil</a>
                        <a class="item" href="./logout">Déconnexion</a>
                    </div>
                </div>
                <div class="<c:if test="${ sessionScope.user != null }">invisibleDiv</c:if>">
                    <a class="item" data-tooltip="Se connecter" data-position="bottom right" href="./login">
                        <i class="user icon"></i>
                    </a>
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