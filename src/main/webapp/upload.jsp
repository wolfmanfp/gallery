<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="hu.ptemik.gallery.dto.User" %>
<html>
    <head>
        <title>Feltöltés</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css" >
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
        %>
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
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                        out.println("<div class=\"alert alert-danger\" role=\"alert\">"
                                + "<i class=\"fa fa-exclamation-triangle\"></i> " + errorMessage
                                + " </div>");
                    }
                    
                    String successMessage = (String) request.getAttribute("successMessage");
                    if (successMessage != null) {
                        out.println("<div class=\"alert alert-success\" role=\"alert\">"
                                + "<i class=\"fa fa-check\"></i> " + successMessage
                                + " </div>");
                    }
                    
                    if (user!=null) {
                %>
                <form class="gallery-form" action="UploadServlet" role="form" method="post" enctype="multipart/form-data">
                    <fieldset>
                        <legend>Feltöltés</legend>
                        <div class="form-group col-md-12">
                            <label class="control-label" for="title">Cím:</label> 
                            <input class="form-control input-md" value="" name="title" type="text">
                        </div>
                        <div class="form-group col-md-12">
                            <label class="control-label" for="description">Leírás:</label> 
                            <input class="form-control input-md" value="" name="description" type="text">
                        </div>
                        <div class="form-group col-md-12">
                            <label class="control-label" for="file">Fájl tallózása:</label> 
                            <input type="file" name="file">
                        </div>
                        <div class="form-group col-md-12">
                            <button type="submit" class="btn btn-default btn-primary">
                                <i class="fa fa-upload"></i> Feltöltés
                            </button>
                        </div>
                    </fieldset>
                </form>
                <%
                    }
                %>
            </div>
        </div>
        <footer class="navbar-static-bottom">
            <div class="container">
                <p class="navbar-text">Készítette: Farkas János (FAJTAAP.PTE), Farkas Péter (FAPVABP.PTE)</p>
            </div>
        </footer>
    </body>
</html>
