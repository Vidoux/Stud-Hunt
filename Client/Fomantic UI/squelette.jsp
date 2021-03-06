<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <!-- You MUST include jQuery before Fomantic -->
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.3.1/dist/jquery.min.js"></script>
  <link rel="stylesheet" type="text/css" href="semantic/dist/semantic.min.css">
  <script src="semantic/dist/semantic.min.js"></script>
  <link rel="stylesheet" href="style/custom.css">
  <title>Title</title>
</head>



<body>
  <%@include file="welcome.html"%>
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

  <div class="ui  container mainContent">
    <div class="ui container segment ">
      Pellentesque ornare nisi sit amet augue maximus, vitae euismod neque varius. Quisque blandit rutrum condimentum. Vivamus facilisis venenatis erat ut porta. Donec vehicula felis ac erat molestie pulvinar. Praesent aliquet magna mauris. Mauris venenatis semper leo, eu tempus mauris facilisis vel. Pellentesque tempor efficitur metus, ac fermentum ante vestibulum sed. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Nunc sollicitudin velit eget pharetra pretium. Donec molestie euismod nibh sed pharetra. Aenean accumsan dolor nec turpis feugiat aliquet. Fusce ac velit sit amet justo tincidunt aliquam. Morbi dictum bibendum sem id lobortis. Morbi viverra ut mauris a mattis. Etiam elementum ex sed lacus gravida fermentum. In congue, lectus tincidunt laoreet dignissim, est nibh pharetra erat, eget blandit sapien dolor ac risus. Donec rhoncus metus nisi, a porta massa bibendum eu. Suspendisse eu porta elit. Vivamus venenatis id metus eget ullamcorper. Sed vel tincidunt magna, et pulvinar felis. Aliquam quis interdum orci. Maecenas vel facilisis lacus. Vivamus sagittis tortor eget facilisis portt
    </div>
    <div class="ui container segment ">
      Pellentesque ornare nisi sit amet augue maximus, vitae euismod neque varius. Quisque blandit rutrum condimentum. Vivamus facilisis venenatis erat ut porta. Donec vehicula felis ac erat molestie pulvinar. Praesent aliquet magna mauris. Mauris venenatis semper leo, eu tempus mauris facilisis vel. Pellentesque tempor efficitur metus, ac fermentum ante vestibulum sed. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Nunc sollicitudin velit eget pharetra pretium. Donec molestie euismod nibh sed pharetra. Aenean accumsan dolor nec turpis feugiat aliquet. Fusce ac velit sit amet justo tincidunt aliquam. Morbi dictum bibendum sem id lobortis. Morbi viverra ut mauris a mattis. Etiam elementum ex sed lacus gravida fermentum. In congue, lectus tincidunt laoreet dignissim, est nibh pharetra erat, eget blandit sapien dolor ac risus. Donec rhoncus metus nisi, a porta massa bibendum eu. Suspendisse eu porta elit. Vivamus venenatis id metus eget ullamcorper. Sed vel tincidunt magna, et pulvinar felis. Aliquam quis interdum orci. Maecenas vel facilisis lacus. Vivamus sagittis tortor eget facilisis portt
    </div>
  </div>

  <footer class="ui inverted vertical footer segment">
      <div class="ui container">
        <div class="ui stackable inverted divided equal height stackable grid">
          <div class="three wide column">
            <h4 class="ui inverted header">About</h4>
            <div class="ui inverted link list">
              <a href="#" class="item">Sitemap</a>
              <a href="#" class="item">Contact Us</a>
              <a href="#" class="item">Religious Ceremonies</a>
              <a href="#" class="item">Gazebo Plans</a>
            </div>
          </div>
          <div class="three wide column">
            <h4 class="ui inverted header">Services</h4>
            <div class="ui inverted link list">
              <a href="#" class="item">Banana Pre-Order</a>
              <a href="#" class="item">DNA FAQ</a>
              <a href="#" class="item">How To Access</a>
              <a href="#" class="item">Favorite X-Men</a>
            </div>
          </div>
          <div class="seven wide column">
            <h4 class="ui inverted header">Footer Header</h4>
            <p>Extra space for a call to action inside the footer that could help re-engage users.</p>
          </div>
        </div>
      </div>
  </footer>

</body>
<script src="scripts/mainScript.js"></script>
</html>