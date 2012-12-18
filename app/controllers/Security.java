package controllers;

import models.User;

/**
 * Custom security class.
 *
 * @author Ivan Masich w3cvalid@gmail.com
 */
public class Security extends Secure.Security {

    /**
     * Simple audentify realization, if user doesn't exist it will be created.
     *
     * @param username Username value.
     * @param password Password value.
     * @return If user exist then will be returned true and if it doesn't exist false.
     */
    static boolean authentify(String username, String password) {
        User user = User.findByUsername(username);
        if (user == null) {
            new User(username, password).save();
            return true;
        }

        return user.password.equals(password);
    }
}
