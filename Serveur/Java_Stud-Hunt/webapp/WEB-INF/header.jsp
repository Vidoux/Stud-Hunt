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
                <a class="item" href="./">Accueil</a>
                <!--          Si étudiant alors: CV, Demandes de contact-->
                <c:if test="${ sessionScope.user.getUserType() == 'STUDENT' }">
                    <a class="item">Demandes de contact</a>
                </c:if>

                <!--          Si entreprise alors: mes recherches, Prises de Contacts-->
                <c:if test="${ sessionScope.user.getUserType() == 'COMPANY' }">
                    <a class="item">Vos Recherches</a>
                </c:if>
                <div class="ui dropdown item <c:if test="${ sessionScope.user == null }">invisibleDiv</c:if>">
                    <img class="ui avatar image" src="" />  <!--insérer, dynamiquement l'image du profil-->
                    <i class="dropdown icon"></i>
                    <div class="menu">
                        <c:if test="${ sessionScope.user.getUserType() == 'COMPANY' }">
                            <a class="item" href="./company_info">Modifier votre profil</a>
                        </c:if>
                        <c:if test="${ sessionScope.user.getUserType() == 'STUDENT' }">
                            <a class="item" href="./student_info">Modifier votre profil</a>
                        </c:if>
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
                        <!--          Si étudiant alors: CV, Demandes de contact-->
                        <c:if test="${ sessionScope.user.getUserType() == 'STUDENT' }">
                            <a class="item">Demandes de contact</a>
                        </c:if>
                        <!--          Si entreprise alors: mes recherches, Prises de Contacts-->
                        <c:if test="${ sessionScope.user.getUserType() == 'COMPANY' }">
                            <a class="item">Vos Recherches</a>
                        </c:if>
                        <a class="item" href="./">Accueil</a>
                        <c:if test="${ sessionScope.user != null }">
                            <a class="item" href="./student_info">Modifier votre profil</a>
                            <a class="item" href="./logout">Déconnexion</a>
                        </c:if>
                        <c:if test="${ sessionScope.user == null }">
                            <a class="item" href="./login">
                                <i class="user icon"></i>
                                <label>Connexion</label>
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

    </nav>

</header>