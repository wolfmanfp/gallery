<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="hu.ptemik.gallery.control.Controller"%>
<%@page import="hu.ptemik.gallery.entities.Picture"%>
<%@page import="hu.ptemik.gallery.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Galéria</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            String username = request.getParameter("username");
            User profile = null;
            List<Picture> pictureList = null;
            boolean isCurrentUser = false;
            try {
                profile = Controller.findUser(username);
                pictureList = Controller.queryPictures(username);
                isCurrentUser = user != null && user.getUserName().equals(username);
            } catch (Exception ex) {}
        %>
        <header class="navbar navbar-default navbar-static-top">
            <div class="container">
                <div class="navbar-header navbar-left">
                    <a href="index.jsp"><img class="navbar-brand" src="img/logo.png" alt="Gallery"></a>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="users.jsp"><i class="fa fa-picture-o"></i> Képek</a></li>
                    <c:choose>
                        <c:when test="${user == null}>">
                            <li><a href="registration.jsp"><i class="fa fa-key"></i> Regisztráció</a></li>
                            <li><a href="login.jsp"><i class="fa fa-sign-in"></i> Bejelentkezés</a></li>
                        </c:when>
                        <c:otherwise>
                            <li <% if (isCurrentUser) { out.println("class=\"active active-user\""); } %>>
                                <a href="pictures.jsp?username=<%= user.getUserName() %>">
                                    <i class="fa fa-user"></i> <%= user.getUserName() %>
                                </a>
                            </li>
                            <li><a href="upload.jsp"><i class="fa fa-upload"></i> Feltöltés</a></li>
                            <li><a href="LogoutServlet"><i class="fa fa-sign-out"></i> Kijelentkezés</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </header>
        <div class="jumbotron jumbotron-special jumbotron-plain">
            <div class="container">
                <c:choose>
                    <c:when test="<%= profile != null %>">
                        <h1>
                            <%= username %> <small><%= profile.getLastName() + " " + profile.getFirstName() %></small>
                            <c:if test="<%= isCurrentUser %>">
                                <a href="upload.jsp" class="btn btn-lg btn-primary"><i class="fa fa-upload"></i> Feltöltés</a>
                            </c:if>
                        </h1>
                        <div class="row">
                            <c:forEach items="<%= pictureList %>" var="pic">
                                <div class="thumb col-md-4">
                                    <div class="thumbnail">
                                        <img src="${pic.url}" alt="${pic.title}">
                                        <div class="caption">
                                            <h3>${pic.title}</h3>
                                            <p>${pic.description}</p>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <h1>Hiba</h1>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <footer class="navbar-static-bottom">
            <div class="container">
                <p class="navbar-text">Készítette: Farkas János (FAJTAAP.PTE), Farkas Péter (FAPVABP.PTE)</p>
            </div>
        </footer>
    </body>
</html>
