package com.example.cozma.socializareusv.CoreModules.UserClasses;

/**
 * Created by cozma on 02.12.2016.
 */

public class UserModel {
    private Integer id = 0;
    private Integer type = 0;
    private String email = "";
    private String first_name = "";
    private String last_name = "";

    private Profile profile = new Profile();

    public UserModel() {
    }

    public UserModel(String string) {
        this.id = null;
        this.type = null;
        this.email = null;
        this.first_name = null;
        this.last_name = null;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
