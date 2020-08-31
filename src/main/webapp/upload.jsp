<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="hu.ptemik.gallery.entities.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Feltöltés</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="styles/style.css">
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            String errorMessage = (String) request.getAttribute("errorMessage");
            String successMessage = (String) request.getAttribute("successMessage");
        %>
        <header class="navbar navbar-expand-md navbar-light navbar-static-top">
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
                <c:if test="${errorMessage != null}">
                    <div class="alert alert-danger" role="alert">
                        <i class="fa fa-exclamation-triangle"></i> <%= errorMessage %>
                    </div>
                </c:if>
                <c:if test="${successMessage != null}">
                    <div class="alert alert-success" role="alert">
                        <i class="fa fa-check"></i> <%= successMessage %>
                    </div>
                </c:if>
                <c:if test="${user != null}">
                    <form class="gallery-form" action="UploadServlet" role="form" method="post" enctype="multipart/form-data">
                        <fieldset>
                            <legend>Feltöltés</legend>
                            <div class="form-group col-md-12">
                                <label class="control-label" for="title">Cím:</label>
                                <input id="title" name="title" class="form-control input-md"
                                       value="" type="text">
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label" for="description">Leírás:</label>
                                <input id="description" name="description" class="form-control input-md"
                                       value="" type="text">
                            </div>
                            <div class="form-group col-md-12">
                                <label class="control-label" for="file">Fájl tallózása:</label>
                                <input id="file" name="file" type="file">
                            </div>
                            <div class="form-group col-md-12">
                                <button type="submit" class="btn btn-default btn-primary">
                                    <i class="fa fa-upload"></i> Feltöltés
                                </button>
                            </div>
                        </fieldset>
                    </form>
                </c:if>
            </div>
        </div>
        <footer class="navbar-static-bottom">
            <div class="container">
                <p class="navbar-text">Készítette: Farkas János (FAJTAAP.PTE), Farkas Péter (FAPVABP.PTE)</p>
            </div>
        </footer>
    </body>
</html>
