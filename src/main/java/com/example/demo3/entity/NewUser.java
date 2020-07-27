package com.example.demo3.entity;

import java.util.Objects;

/**
 * Created on 2020/7/26.
 *
 * @author Zhouyong Tan
 */
public class NewUser {

    private Integer uid;

    private String username;

    private String password;

    private String picture;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewUser newUser = (NewUser) o;
        return uid.equals(newUser.uid) &&
                username.equals(newUser.username) &&
                password.equals(newUser.password) &&
                picture.equals(newUser.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, username, password, picture);
    }

    @Override
    public String toString() {
        return "newUser{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
