<%@page import="java.util.List"%>
<%@page import="hu.ptemik.gallery.control.Controller"%>
<%@page import="hu.ptemik.gallery.dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Galéria</title>
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
                    <a href="index.jsp"><img class="navbar-brand" src="logo.png" alt="Gallery"></a>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <%
                        if (user == null) {
                    %>
                    <li><a href="registration.jsp"><i class="fa fa-user"></i> Regisztráció</a></li>
                    <li><a href="login.jsp"><i class="fa fa-sign-in"></i> Bejelentkezés</a></li>
                        <% } else {%>
                    <li><a href="pictures.jsp?username=<%=user.getUserName()%>"><i class="fa fa-picture-o"></i> <%=user.getUserName()%></a></li>
                    <li><a href="#"><i class="fa fa-cloud-upload"></i> Feltöltés</a></li>
                    <li><a href="LogoutServlet"><i class="fa fa-cloud-upload"></i> Kijelentkezés</a></li>
                        <% }%>
                    <li class="active"><a href="#"><i class="fa fa-users"></i> Felhasználók</a></li>
                </ul>
            </div>
        </header>
        <div class="jumbotron jumbotron-special jumbotron-index">
            
            <div class="container">
                <ul class="list-group">
                    <%
                        List<User> users = Controller.queryUsers();
                        for (User u : users) {
                            out.println("<li class=\"list-group-item\"><i class=\"fa fa-user\"></i> "+
                                    "<a href=\"pictures.jsp?username="+
                                    u.getUserName()+
                                    "\">"+
                                    u.getUserName()+
                                    "</a>"+
                                    "</li>");
                        }
                    %>
                </ul>
            </div>  
        </div>
        <footer class="navbar-static-bottom">
            <div class="container">
                <p class="navbar-text">Készítette: Farkas János (FAJTAAP.PTE), Farkas Péter (FAPVABP.PTE)</p>
            </div>
        </footer>
    </body>
</html>
