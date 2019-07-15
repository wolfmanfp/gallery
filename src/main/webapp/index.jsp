<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="hu.ptemik.gallery.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Galéria</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="styles/style.css">
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
        %>
        <header class="navbar navbar-expand-md navbar-light navbar-static-top">
            <div class="container">
                <div class="navbar-header navbar-left">
                    <img class="navbar-brand" src="img/logo.png" alt="Gallery">
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="users.jsp"><i class="far fa-images"></i> Képek</a></li>
                    <c:choose>
                        <c:when test="${user == null}">
                            <li><a href="registration.jsp"><i class="fa fa-key"></i> Regisztráció</a></li>
                            <li><a href="login.jsp"><i class="fas fa-sign-in-alt"></i> Bejelentkezés</a></li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="pictures.jsp?username=<%= user.getUserName() %>">
                                    <i class="fa fa-user"></i> <%= user.getUserName() %>
                                </a>
                            </li>
                            <li><a href="upload.jsp"><i class="fa fa-upload"></i> Feltöltés</a></li>
                            <li><a href="LogoutServlet"><i class="fas fa-sign-out-alt"></i> Kijelentkezés</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </header>
        <div class="jumbotron jumbotron-index">
            <div class="container">
                <h1>Regisztrálj, és oszd meg legjobb képeidet</h1>
                <p>
                    <a href="registration.jsp" class="btn btn-lg btn-primary">
                        <i class="fa fa-key"></i> Regisztrálok
                    </a>
                </p>
            </div>
        </div>
        <div class="jumbotron jumbotron-index">
            <div class="container">
                <h1>Böngéssz a feltöltött képek között</h1>
                <p>
                    <a href="users.jsp" class="btn btn-lg btn-primary">
                        <i class="far fa-images"></i> Lássuk a medvét!
                    </a>
                </p>
            </div>
        </div>
        <div class="jumbotron jumbotron-index">
            <div class="container">
                <h1>Lorem ipsum dolor sit amet, consectetur adipiscing elit</h1>
                <p><a href="login.jsp" class="btn btn-lg btn-primary">Latinul van!</a></p>
            </div>
        </div>
        <footer class="navbar-static-bottom">
            <div class="container">
                <p class="navbar-text">Készítette: Farkas János (FAJTAAP.PTE), Farkas Péter (FAPVABP.PTE)<br>
                    Képek forrása: <a href="https://www.flickr.com/groups/creative_commons-_free_pictures/pool/">Flickr</a></p>
            </div>
        </footer>
    </body>
</html>
