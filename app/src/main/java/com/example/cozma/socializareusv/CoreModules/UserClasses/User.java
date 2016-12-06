package com.example.cozma.socializareusv.CoreModules.UserClasses;

/**
 * Created by cozma on 02.12.2016.
 */

public class User {

    private static User ourInstance = new User();

    public static User getInstance() {
        return ourInstance;
    }

    public static void resetInstance() {
        User.ourInstance = new User();
    }

    private User() {
    }

    private UserModel currentUser = new UserModel();

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }
}
