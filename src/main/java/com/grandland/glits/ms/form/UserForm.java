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
    private String mail;
    private String tel;
    private String avatar;
    private User.Role userRole;
    private Boolean enabled;
    private Boolean updated = false;

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getUpdated() {
        return updated;
    }

    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", tel='" + tel + '\'' +
                ", avatar='" + avatar + '\'' +
                ", userRole=" + userRole +
                ", enabled=" + enabled +
                ", updated=" + updated +
                '}';
    }
}
