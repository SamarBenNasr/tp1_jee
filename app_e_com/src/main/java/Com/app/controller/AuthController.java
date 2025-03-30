package Com.app.controller;

import Com.app.metier.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class AuthController extends HttpServlet {
    private AuthService authService;

    public AuthController() {
        this.authService = new AuthService();  // Normally injected, but no Spring here
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is already logged in (if session exists)
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect("produits"); // Redirect to the product page if already logged in
            return;
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authService.authenticate(username, password)) {
            // Set user in session
            HttpSession session = request.getSession(true);
            session.setAttribute("user", username);

            // Optionally, store the username in a cookie for auto-login (if desired)
            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(30 * 60); // 30 minutes
            userCookie.setHttpOnly(true); // Prevent access via JavaScript
            userCookie.setSecure(true); // Use only over HTTPS
            userCookie.setPath("/"); // Set path to make the cookie available across all pages
            response.addCookie(userCookie);

            response.sendRedirect("produits"); // Redirect to the product page after successful login
        } else {
            response.sendRedirect("login.jsp?error=true"); // Redirect back to login page with error
        }
    }
}
