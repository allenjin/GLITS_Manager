package com.grandland.glits.ms.form;

import com.grandland.glits.ms.domain.User;

/**
 * UserForm
 *
 * @author Allen Jin
 * @date 2016/01/28
 */
public class UserForm {
    private String name;
    private User.Role userRole;
    private Boolean enabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User.Role getUserRole() {
        return userRole;
    }

    public void setUserRole(User.Role userRole) {
        this.userRole = userRole;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "name='" + name + '\'' +
                ", userRole='" + userRole + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
