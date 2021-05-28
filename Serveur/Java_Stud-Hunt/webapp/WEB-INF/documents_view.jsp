<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Documents</title>
    <%--import de la feuille de style Semantic UI--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css">
    <style>
        body{
            margin-top: 70px;
        }
    </style>

</head>
<body>


    <div class="ui container segment small">
        <div class="ui container step">
            <div class="ui center aligned container">
                <h1 class="ui header">Gestion du Catalogue</h1>
                <h4 class="ui header">Consultation, ajout et Supression de Documents</h4>
            </div>
        </div>
    </div>
    <div class="ui container segment small">
        <div class="ui container step">
            <div class="ui center aligned container">
                <h4 class="ui header">Tous les documents</h4>
            </div>
        </div>
<%--        Tableau contenant tous les documents--%>
        <table class="ui celled table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Type</th>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Est Emprunté?</th>
            </tr></thead>
            <tbody>
                <%--Pour chaque Document du Catalogue on ajoute la ligne correspondante dans le tableau--%>
                <c:forEach items="${ listDocuments }" var="doc">
                    <tr>
                        <td data-label="ID"><c:out value="${doc.data()[4]}" /></td>
                        <td data-label="Type"><c:out value="${doc.data()[3]}" /></td>
                        <td data-label="Titre"><c:out value="${doc.data()[0]}" /></td>
                        <td data-label="Auteur"><c:out value="${doc.data()[1]}" /></td>
                        <td data-label="Est Emprunté?" >

                            <div class="ui toggle checkbox" onclick="return false;">
                                <input type="checkbox" name="public"
                                    <c:if test="${doc.data()[2] != null}">checked=""</c:if>
                                >
                                <label></label>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="ui container segment small">
        <h1>Suppression d'un document</h1>
        <form action="./supress_doc" method="post">
            <div class="ui fluid search selection dropdown" id="suppress_dropdown">
                <input type="hidden" name="document">
                <i class="dropdown icon"></i>
                <div class="default text">Selection du Document</div>
                <%--On initialise ici la liste qui permet de sélectionner le document à supprimer--%>
                <div class="menu">
                    <c:forEach items="${ listDocuments }" var="doc">
                        <div class="item" data-value="<c:out value="${doc.data()[4]}" />">
                            <i class="icon
                            <c:choose>
                                <c:when test="${ doc.data()[3] == 'CD' }">music</c:when>
                                <c:when test="${ doc.data()[3] == 'DVD' }">film</c:when>
                                <c:when test="${ doc.data()[3] == 'BOOK' }">book</c:when>
                                <c:otherwise>chess knight</c:otherwise>
                            </c:choose>
                            "></i>
                            <c:out value="${doc.data()[0]}" />
                        </div>
                    </c:forEach>
                </div>
            </div>
            <br>
            <input class="ui button active" type="submit" value="Supprimer" />
        </form>
    </div>

    <div class="ui container segment small">

        <form action="./add_doc" method="post">
            <div class="ui form container center aligned">
                <h1>Ajout d'un document</h1>
                <label>Auteur :</label>
                <div class="ui input">
                    <input type="text" placeholder="Auteur" name="author">
                </div>
                <div class="ui hidden divider"></div>
                <label>Titre :</label>
                <div class="ui input">
                    <input type="text" placeholder="Titre" name="title">
                </div>


                <div class="ui hidden divider"></div>

                <div class="ui small search selection dropdown " id="type_dropdown">
                    <input type="hidden" name="type">
                    <i class="dropdown icon"></i>
                    <div class="default text">Selection du type de document</div>
                    <div class="menu">

                        <div class="item" data-value="3">
                            <i class="icon music"></i>CD
                        </div>
                        <div class="item" data-value="2">
                            <i class="icon film"></i>DVD
                        </div><div class="item" data-value="1">
                            <i class="icon book"></i>Livre
                        </div>

                    </div>
                </div>

                <div class="ui hidden divider"></div>

                <input class="ui button active" type="submit" value="Ajouter" />
            </div>
        </form>
    </div>


    <%--Import du javascript Semantic UI--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js"></script>
    <script>
        $('.dropdown').dropdown();
    </script>
</body>
</html>
