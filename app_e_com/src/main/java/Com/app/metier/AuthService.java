package Com.app.metier;

import Com.app.dao.UserDAO;

public class AuthService {
    private UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    // Modify the method to use the User object returned by isValidUser
    public boolean authenticate(String username, String password) {
        User user = userDAO.isValidUser(username, password);
        return user != null; // Return true if the user is found (valid), false otherwise
    }
}
