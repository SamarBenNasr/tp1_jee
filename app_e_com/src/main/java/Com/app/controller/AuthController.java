package Com.app.controller;

import Com.app.metier.AuthService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class AuthController extends HttpServlet {
    private AuthService authService;

    public AuthController() {
        this.authService = new AuthService();  // Normally injected, but no Spring here
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authService.authenticate(username, password)) {
            // Stocker l'utilisateur en session
            request.getSession().setAttribute("user", username);
            response.sendRedirect("produits"); // Page d'accueil après connexion
        } else {
            response.sendRedirect("login.jsp?error=true"); // Redirige avec un paramètre d'erreur
        }
    }

}