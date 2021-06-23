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
    <title>Stud-Hunt-Company Profil</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/img/logo(sans%20titre).png"/>
</head>

<body>
<%@include file="header.jsp"%>

<div class="bigdiv ui centered stackable grid">
    <div class="ui nine wide column center aligned segment transparent">
        <h1>Consultez et modifiez votre profil d'Entreprise</h1>
    </div>
    <div class="ui nine wide column">
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
        <div class="ui left aligned segment transparent">
            <form class="ui form">
                <h4 class="ui dividing header">Information</h4>
                <div class="field">
                    <label>Nom commercial</label>
                    <div class="two fields">
                        <div class="field">
                            <input type="text" name="noment" placeholder="Nom">
                        </div>
                        <div class="field">
                            <input type="text" name="shipping[last-name]" placeholder="Prénom">
                        </div>
                    </div>
                </div>
                <div class="field">
                    <label>Localisation</label>
                    <div class="fields">
                        <div class="twelve wide field">
                            <input type="text" name="shipping[address]" placeholder="Adresse">
                        </div>
                        <div class="four wide field">
                            <input type="text" name="shipping[address-2]" placeholder="Code postale">
                        </div>
                    </div>
                </div>

                <div class="two fields">
                    <div class="field">
                        <label>Ville</label>
                        <input type="text" name="ville" placeholder="Ville">
                    </div>
                    <div class="field">
                        <label>Pays</label>
                        <div class="ui fluid search selection dropdown">
                            <input type="hidden" name="country">
                            <i class="dropdown icon"></i>
                            <div class="default text">Selectionner un Pays</div>
                            <div class="menu">
                                <div class="item" data-value="be"><i class="be flag"></i>Belgique</div>
                                <div class="item" data-value="ca"><i class="ca flag"></i>Canada</div>
                                <div class="item" data-value="fr"><i class="fr flag"></i>France</div>
                                <div class="item" data-value="ch"><i class="ch flag"></i>Suisse</div>
                            </div>
                        </div>
                    </div>
                </div>

                <h4 class="ui dividing header">Secteur</h4>
                <div class="fields">
                    <div class="ten wide field">
                        <label>Domaine</label>
                        <input type="text" name="Secteur" placeholder="Domaine">
                    </div>
                    <div class="six wide field">
                        <label>Date de création</label>
                        <input type="date" name="DateDiplôme">
                    </div>
                </div>

                <h4 class="ui dividing header">Coordonnées</h4>
                <div class="field">
                    <label>E-mail</label>
                    <input type="email" size="30" name="e-mail" placeholder="E-mail">
                </div>
                <div class="field">
                    <label>Téléphone</label>
                    <input type="number" name="telephone" placeholder="+330 12 34 56 78">
                </div>

                <div class="ui form">
                    <div class="field">
                        <label>Texte</label>
                        <textarea placeholder="Présentez-vous en quelques mots (donnez un premier aperçu de votre entreprise à vos futurs collaborateurs)"></textarea>
                    </div>
                </div>

                <br>
                <div class="ui button" tabindex="0">Valider</div>




            </form>
        </div>
    </div>
</div>

<div style="flex-grow:1"></div>

<%@include file="footer.jsp"%>

</body>
<script src="${pageContext.request.contextPath}/scripts/mainScript.js"></script>
</html>