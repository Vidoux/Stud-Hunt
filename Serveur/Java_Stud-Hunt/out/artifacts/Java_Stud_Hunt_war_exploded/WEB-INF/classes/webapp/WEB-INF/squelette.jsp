<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <!-- You MUST include jQuery before Fomantic -->
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.3.1/dist/jquery.min.js"></script>
  <%@include file="scripts/mainScript.js" %>
  <jsp:include page="style/custom.css" />
  <jsp:include page="semantic/dist/semantic.min.js"/>
  <jsp:include page="semantic/dist/semantic.min.css"/>

  <title>Title</title>
</head>



<body>
  <%@include file="header.jsp"%>

  <div class="ui  container mainContent">
    <div class="ui container segment ">
      Pellentesque ornare nisi sit amet augue maximus, vitae euismod neque varius. Quisque blandit rutrum condimentum. Vivamus facilisis venenatis erat ut porta. Donec vehicula felis ac erat molestie pulvinar. Praesent aliquet magna mauris. Mauris venenatis semper leo, eu tempus mauris facilisis vel. Pellentesque tempor efficitur metus, ac fermentum ante vestibulum sed. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Nunc sollicitudin velit eget pharetra pretium. Donec molestie euismod nibh sed pharetra. Aenean accumsan dolor nec turpis feugiat aliquet. Fusce ac velit sit amet justo tincidunt aliquam. Morbi dictum bibendum sem id lobortis. Morbi viverra ut mauris a mattis. Etiam elementum ex sed lacus gravida fermentum. In congue, lectus tincidunt laoreet dignissim, est nibh pharetra erat, eget blandit sapien dolor ac risus. Donec rhoncus metus nisi, a porta massa bibendum eu. Suspendisse eu porta elit. Vivamus venenatis id metus eget ullamcorper. Sed vel tincidunt magna, et pulvinar felis. Aliquam quis interdum orci. Maecenas vel facilisis lacus. Vivamus sagittis tortor eget facilisis portt
    </div>
    <div class="ui container segment ">
      Pellentesque ornare nisi sit amet augue maximus, vitae euismod neque varius. Quisque blandit rutrum condimentum. Vivamus facilisis venenatis erat ut porta. Donec vehicula felis ac erat molestie pulvinar. Praesent aliquet magna mauris. Mauris venenatis semper leo, eu tempus mauris facilisis vel. Pellentesque tempor efficitur metus, ac fermentum ante vestibulum sed. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Nunc sollicitudin velit eget pharetra pretium. Donec molestie euismod nibh sed pharetra. Aenean accumsan dolor nec turpis feugiat aliquet. Fusce ac velit sit amet justo tincidunt aliquam. Morbi dictum bibendum sem id lobortis. Morbi viverra ut mauris a mattis. Etiam elementum ex sed lacus gravida fermentum. In congue, lectus tincidunt laoreet dignissim, est nibh pharetra erat, eget blandit sapien dolor ac risus. Donec rhoncus metus nisi, a porta massa bibendum eu. Suspendisse eu porta elit. Vivamus venenatis id metus eget ullamcorper. Sed vel tincidunt magna, et pulvinar felis. Aliquam quis interdum orci. Maecenas vel facilisis lacus. Vivamus sagittis tortor eget facilisis portt
    </div>
  </div>

  <%@include file="footer.jsp"%>

</body>
<script src="scripts/mainScript.js"></script>
</html>