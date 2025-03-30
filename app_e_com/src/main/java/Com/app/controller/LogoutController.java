package Com.app.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session to log the user out
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Delete the username cookie by setting its max age to 0
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    cookie.setMaxAge(0); // Delete the cookie
                    cookie.setPath("/"); // Ensure cookie is deleted for all pages
                    response.addCookie(cookie);
                }
            }
        }

        response.sendRedirect("login.jsp"); // Redirect to login page after logout
    }
}
