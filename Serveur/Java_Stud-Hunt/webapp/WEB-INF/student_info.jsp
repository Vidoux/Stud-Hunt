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
    <title>Stud-Hunt-Profil</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/img/logo(sans%20titre).png"/>
</head>

<body>
<%@include file="header.jsp"%>


<div class="bigdiv ui centered stackable grid">
    <div class="ui nine wide column center aligned segment transparent">
        <h1>Consultez et modifiez votre profil</h1>
        <h1>${sessionScope.user.getName()}</h1>
    </div>
    <div class="nine wide column">
        <div class="ui center aligned segment transparent">
            <h1>Photo de Profil</h1>
            <img src="./image_action" class="ui centered small circular image">
            <form method="post" action="./image_action" enctype="multipart/form-data">
                <div class="item">
                    <div>
                        <label for="image_uploads">Sélectionner des images à uploader (PNG, JPG)</label>
                        <div class="ui input">
                            <input type="file" id="image_uploads" name="image_upload" accept=".jpg, .jpeg, .png">
                        </div>
                    </div>
                </div><br>
                <div class="preview">
                    <p>Aucun fichier sélectionné pour le moment</p>
                </div>
                <button class="ui blue button" id="change_picture" type="submit">
                    <i class="pen icon"></i>
                    Valider et envoyer votre photo
                </button>
            </form>
        </div>
        <div class="ui center aligned segment transparent">
            <h1>CV</h1>

            <a href="./cv_action" target="_blank">
                <button class="ui labeled green icon button">
                    <i class="file alternate icon"></i>
                    Voir votre CV
                </button>
            </a><br><br>
            <form method="post" action="./cv_action" enctype="multipart/form-data">
                <div class="item">
                    <div>
                        <label for="cv_uploads">Sélectionnez votre CV (.pdf)</label>
                        <div class="ui input">
                            <input type="file" id="cv_uploads" name="cv_upload" accept=".pdf">
                        </div>
                    </div>
                </div><br>
                <button class="ui blue button" id="change_cv" type="submit">
                    <i class="pen icon"></i>
                    Valider et envoyer votre cv
                </button>
            </form>
        </div>


        <div class="ui left aligned fluid segment transparent">
            <h1 style="text-align: center;">Vos Informations</h1>
            <button class="ui fluid blue button" id="change_profil" onclick="activateStudentForm()">
                <i class="pen icon"></i>
                Modifier ces informations
            </button><br>
            <form class="ui form" id="student_info_frm" method="post" action="./student_info">
                <div class="field">
                    <label>Votre identité</label>
                    <div class="two fields">
                        <div class="field">
                            <input type="text" name="nom" placeholder="Nom" value="${sessionScope.user.getName()}">
                        </div>
                        <div class="field">
                            <input type="text" name="prenom" placeholder="Prénom" value="${sessionScope.user.getForname()}">
                        </div>
                    </div>
                </div>


<%--                <div class="two fields">--%>
<%--                    <div class="field">--%>
<%--                        <label>Ville</label>--%>
<%--                        <input type="text" name="ville" placeholder="Ville">--%>
<%--                    </div>--%>
<%--                    <div class="field">--%>
<%--                        <label>Pays</label>--%>
<%--                        <div class="ui fluid search selection dropdown">--%>
<%--                            <input type="hidden" name="country">--%>
<%--                            <i class="dropdown icon"></i>--%>
<%--                            <div class="default text">Selectionner un Pays</div>--%>
<%--                            <div class="menu">--%>
<%--                                <div class="item" data-value="be"><i class="be flag"></i>Belgique</div>--%>
<%--                                <div class="item" data-value="ca"><i class="ca flag"></i>Canada</div>--%>
<%--                                <div class="item" data-value="fr"><i class="fr flag"></i>France</div>--%>
<%--                                <div class="item" data-value="ch"><i class="ch flag"></i>Suisse</div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

                <h4 class="ui dividing header">Parcours scolaire</h4>
                <div class="field">
                    <label>Nom de la formation</label>
                    <!--<div class="sixteen fields">-->
                    <div class="field">
                        <input type="text" name="nomdiplome" placeholder="Nom du dîplome" value="${sessionScope.user.getDiploma()}">
                    </div>
                    <div class="field">
                      <input type="text" name="ecole" placeholder="Ecole" value="${sessionScope.school}">
                    </div>
                </div>
                <div class="fields">
                    <div class="ten wide field">
                        <label>Secteur</label>
                        <input type="text" name="secteur" placeholder="Secteur" value="${sessionScope.user.getIndustry()}">
                    </div>
                </div>


                    <div class="field">
<%--                        <label>Ecole</label>--%>
<%--                        <input type="text" name="nomecole" value="${schoolname}">--%>
<%--                        <div class="ui fluid search selection dropdown">--%>
<%--                            <input type="hidden" name="school">--%>
<%--                            <i class="dropdown icon"></i>--%>
<%--                            <div class="default text">Sélectioner une école</div>--%>
<%--                            <div class="menu">--%>
<%--                                <div class="item" data-value="af"><i></i>HEC</div>--%>
<%--                                <div class="item" data-value="ax"><i></i>Epitech</div>--%>
<%--                                <div class="item" data-value="al"><i></i>Epita</div>--%>
<%--                                <div class="item" data-value="dz"><i></i>ESSEC</div>--%>
<%--                                <div class="item" data-value="as"><i></i>Polytechnique</div>--%>
<%--                                <div class="item" data-value="ad"><i></i>La Sorbonne</div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                    </div>
<%--                    <div class="field">--%>
<%--                        <label>Diplôme actuel</label>--%>
<%--                        <div class="ui fluid search selection dropdown">--%>
<%--                            <input type="hidden" name="graduate">--%>
<%--                            <i class="dropdown icon"></i>--%>
<%--                            <div class="default text">Selectionner un diplôme</div>--%>
<%--                            <div class="menu">--%>
<%--                                <div class="item" data-value="+1"><i></i>Bac/Cap</div>--%>
<%--                                <div class="item" data-value="+2"><i></i>Bac+1</div>--%>
<%--                                <div class="item" data-value="+3"><i></i>Bac+2</div>--%>
<%--                                <div class="item" data-value="+4"><i></i>Bac+3</div>--%>
<%--                                <div class="item" data-value="+5"><i></i>Bac+4</div>--%>
<%--                                <div class="item" data-value="+6"><i></i>Bac+5</div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="field">--%>
<%--                        <label>Secteur d'activité recherché</label>--%>
<%--                        <input type="text" name="industry" placeholder="secteur....">--%>
<%--                    </div>--%>

<%--                <h4 class="ui dividing header">Coordonnées</h4>--%>
<%--                <div class="field">--%>
<%--                    <label>Téléphone</label>--%>
<%--                    <input type="number" name="telephone" placeholder="+330 12 34 56 78">--%>
<%--                </div>--%>
                <div class="ui form">
                    <div class="field">
                        <label>Présente toi</label>
                        <input type="text"
                               placeholder="Présente toi en quelques mots max 500 caractères (les entreprises verront cette partie donnez leurs envies de t'embaucher)"
                               maxlength="500" name="bio"
                               value="${sessionScope.user.getBio()}">
                    </div>
                </div>
                <br>
                <h4 class="ui dividing header">Tes projets </h4>
                <div class="field">
                    <label>Nom</label>
                    <div class="two fields">
                        <div class="field">

                            <input type="text" name="nom_projet" placeholder="Nom" value="<c:if test="${ sessionScope.proj != null }">
                        ${sessionScope.proj.getProjectName()}
                    </c:if>">
                        </div>
                        <div class="field">
                            <input type="number" name="annee-projet" value="<c:if test="${ sessionScope.proj != null }">
                        ${sessionScope.proj.getRealisation_year()}
                    </c:if>">
                        </div>
                    </div>
                    <input type="text" value="<c:if test="${ sessionScope.proj != null }">
                        ${sessionScope.proj.getProjectBio()}
                    </c:if>" name="bio_projet">
                </div>

                <button class="big ui blue button invisibleDiv" id="submitButton" type="submit">Valider</button>
            </form>
            <button class="ui blue button invisibleDiv" id="cancelButton" onclick="desactivateStudentForm()">
                <i class="close icon"></i>
                Annuler
            </button>
        </div>
    </div>
</div>

<div style="flex-grow:1"></div>

<%@include file="footer.jsp"%>

</body>
<script src="${pageContext.request.contextPath}/scripts/mainScript.js"></script>
</html>