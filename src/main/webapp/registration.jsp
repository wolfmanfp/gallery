<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Regisztráció</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="styles/style.css">
    </head>
    <body>
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
                <%
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String username = request.getParameter("username");
                    String email = request.getParameter("email");
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    String successMessage = (String) request.getAttribute("successMessage");
                %>

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
                <form class="gallery-form" role="form" action="RegistrationServlet" method="post">
                    <fieldset>
                        <legend>Regisztráció</legend>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label class="control-label" for="lastName">Vezetéknév</label>
                                <input id="lastName" name="lastName" class="form-control input-md"
                                       value="<% if(lastName != null) out.println(lastName); %>" type="text">
                            </div>
                            <div class="form-group col-md-6">
                                <label class="control-label" for="firstName">Keresztnév</label>
                                <input id="firstName" name="firstName" class="form-control input-md"
                                       value="<% if(firstName != null) out.println(firstName); %>" type="text">
                            </div>
                        </div>
                        <div class="form-group col-md-12">
                            <label class="control-label" for="email">E-mail cím:</label> 
                            <input id="email" name="email" class="form-control input-md"
                                   value="<% if(email != null) out.println(email); %>" type="email">
                        </div>
                        <div class="form-group col-md-12">
                            <label class="control-label" for="username">Felhasználónév:</label> 
                            <input id="username" name="username" class="form-control input-md"
                                   value="<% if(username != null) out.println(username); %>" type="text">
                        </div>
                        <div class="form-group col-md-12">
                            <label class="control-label" for="password">Jelszó:</label> 
                            <input id="password" name="password" class="form-control input-md" type="password">
                        </div>
                        <div class="form-group col-md-12">
                            <label class="control-label" for="password2">Jelszó megerősítése:</label> 
                            <input id="password2" name="password2" class="form-control input-md" type="password">
                        </div>
                        <div class="form-group col-md-12">
                            <button type="submit" class="btn btn-default btn-primary">
                                <i class="fa fa-key"></i> Regisztráció
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
