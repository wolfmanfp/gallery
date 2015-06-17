<%@page import="hu.ptemik.gallery.dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Galéria</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="style.css" >
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">
    </head>
    <body>
        <%
            User user = (User)session.getAttribute("user");
        %>
        <header class="navbar navbar-default navbar-static-top">
            <div class="container">
                <div class="navbar-header navbar-left">
                    <img class="navbar-brand" src="logo.png" alt="Gallery">
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <% 
                        if (user==null) {
                    %>
                    <li><a href="registration.jsp"><i class="fa fa-user"></i> Regisztráció</a></li>
                    <li><a href="login.jsp"><i class="fa fa-sign-in"></i> Bejelentkezés</a></li>
                    <% } else { %>
                    <li><a href="#"><i class="fa fa-picture-o"></i> <%=user.getUserName()%></a></li>
                    <li><a href="#"><i class="fa fa-cloud-upload"></i> Feltöltés</a></li>
                    <li><a href="LogoutServlet"><i class="fa fa-cloud-upload"></i> Kijelentkezés</a></li>
                    <% } %>
                    <li><a href="#"><i class="fa fa-users"></i> Felhasználók</a></li>
                </ul>
            </div>
        </header>
        <div class="jumbotron">
            <div class="container">
                <h1>Oszd meg legszebb képeidet</h1>
                <p><a href="registration.jsp" class="btn btn-lg btn-primary">Regisztráció</a></p>
            </div>
        </div>
        <div class="jumbotron">
            <div class="container">
                <h1>Böngéssz mások képei között</h1>
                <p><a href="#" class="btn btn-lg btn-primary">Képek</a></p>
            </div>
        </div>
        <div class="jumbotron">
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
