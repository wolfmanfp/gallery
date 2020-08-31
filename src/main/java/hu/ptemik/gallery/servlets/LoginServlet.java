package hu.ptemik.gallery.servlets;

import hu.ptemik.gallery.entities.User;
import hu.ptemik.gallery.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author FPeter
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errorMessage;

        boolean missingData = username.isEmpty() || password.isEmpty();

        if (missingData) {
            errorMessage = "Hiányzó adatok!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            UserService userService = new UserService();
            User user = userService.submitLogin(username, password);
            if (user == null) {
                errorMessage = "Hibás felhasználónév/jelszó!";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(30 * 60);
                response.sendRedirect("index.jsp");
            }
        }
    }

}
