/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ptemik.gallery.servlets;

import hu.ptemik.gallery.control.Controller;
import hu.ptemik.gallery.control.Encrypt;
import hu.ptemik.gallery.dto.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FPeter
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        
        boolean missingDataError = firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() ||
                email.isEmpty() || password.isEmpty();
        boolean usernameError = Controller.isExistingUser(username);
        boolean emailError = Controller.isExistingEmail(email);
        boolean passwordError = !password2.equals(password);
        boolean error = missingDataError || usernameError || emailError || passwordError;

        if (error) {
            String errorMessage="";
            if(missingDataError) {
                errorMessage = "Hiányzó adatok!";
            }
            else if(usernameError) {
                errorMessage = "Már létezik ilyen nevű felhasználó!";
            }
            else if(emailError) {
                errorMessage = "Már regisztráltak erre az e-mail címre!";
            }
            else if(passwordError) {
                errorMessage = "A megadott jelszavak nem egyeznek!";
            }
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
        else {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUserName(username);
            user.setEmail(email);
            user.setPasswordHash(password);

            try {
                Controller.newUser(user);
            } catch (SQLException ex) {
                Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("index.jsp");
        } 
    }
}
