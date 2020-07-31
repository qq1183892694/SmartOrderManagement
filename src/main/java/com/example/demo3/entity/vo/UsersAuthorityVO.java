package com.example.demo3.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;


import java.io.Serializable;

public class UsersAuthorityVO implements Serializable {
    @TableId("uid")
    private Long uid;
    @TableField("userLoginName")
    private String userLoginName;
    @TableField("userPassword")
    private String userPassword;
    @TableField("userAvatarPath")
    private String userAvatarPath;
    @TableField("sysName")
    private String sysName;
    @TableField("characterName")
    private String characterName;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long userId) {
        this.uid = userId;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public String getUserAvatarPath() {
        return userAvatarPath;
    }

    public void setUserAvatarPath(String userAvatarPath) {
        this.userAvatarPath = userAvatarPath;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    @Override
    public String toString() {
        return "UsersAuthorityVO{" +
                "userId=" + uid +
                ", userLoginName='" + userLoginName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userAvatarPath='" + userAvatarPath + '\'' +
                ", sysName='" + sysName + '\'' +
                ", characterName='" + characterName + '\'' +
                '}';
    }
}
