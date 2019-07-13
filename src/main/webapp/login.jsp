<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="hu.ptemik.gallery.entities.User" %>
<html>
    <head>
        <title>Bejelentkezés</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css" >
    </head>
    <body>
        <header class="navbar navbar-default navbar-static-top">
            <div class="container">
                <div class="navbar-header navbar-left">
                    <a href="index.jsp"><img class="navbar-brand" src="img/logo.png" alt="Gallery"></a>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="index.jsp"><i class="fa fa-arrow-left"></i> Főoldal</a></li>
                </ul>
            </div>
        </header>
        <div class="jumbotron jumbotron-special jumbotron-index">
            <div class="container">
                <%
                    String username = request.getParameter("username");
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                        out.println("<div class=\"alert alert-danger\" role=\"alert\">"
                                + "<i class=\"fa fa-exclamation-triangle\"></i> " + errorMessage
                                + " </div>");
                    }
                %>
                <form class="gallery-form" action="LoginServlet" role="form" method="post">
                    <fieldset>
                        <legend>Bejelentkezés</legend>
                        <div class="form-group col-md-12">
                            <label class="control-label" for="username">Felhasználónév:</label> 
                            <input class="form-control input-md" 
                                   value="<% if(username!=null) out.println(username); %>" name="username" type="text">
                        </div>
                        <div class="form-group col-md-12">
                            <label class="control-label" for="password">Jelszó:</label> 
                            <input class="form-control input-md" name="password" type="password">
                        </div>
                        <div class="form-group col-md-12">
                            <button type="submit" class="btn btn-default btn-primary">
                                <i class="fa fa-sign-in"></i> Bejelentkezés
                            </button>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
        <footer class="navbar-static-bottom">
            <div class="container">
                <p class="navbar-text">Készítette: Farkas János (FAJTAAP.PTE), Farkas Péter (FAPVABP.PTE)</p>
            </div>
        </footer>
    </body>
</html>
