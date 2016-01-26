package com.grandland.glits.ms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User
 *
 * @author Allen Jin
 * @date 2015/12/29
 */

@Entity
@Table(name = "users", indexes = {
        @Index(name = "IDX_NAME", columnList = "name")
})
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role{
        ROLE_ADMIN("管理员"),ROLE_USER("普通用户");
        private String name;
        Role(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
    }

    private String mail;

    private String tel;

    private String avatar;

    private Date lastLogin;

    private boolean isEnable;

    public Long
    getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean getIsEnable(){
        return isEnable;
    }

    public void setIsEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", tel='" + tel + '\'' +
                ", avatar='" + avatar + '\'' +
                ", lastLogin=" + lastLogin +
                '}';
    }

    //待完善
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> al = new ArrayList<GrantedAuthority>();
        al.add(new SimpleGrantedAuthority(getRole().name()));
        return al;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            User oUser = (User) obj;
            return name.equals(oUser.getUsername());
        }
        return false;
    }
}
