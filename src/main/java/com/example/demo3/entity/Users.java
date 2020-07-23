package com.example.demo3.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fyq
 * @since 2020-07-23
 */
@Data
@TableName("Users")
public class Users{

    @TableId("userId")
    @TableField("userId")
    private String userId;
    @TableField("userLoginName")
    private String userLoginName;
    @TableField("userPassword")
    private String userPassword;
    @TableField("characterId")
    private Integer characterId;
    @TableField("userAvatarPath")
    private String userAvatarPath;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }
    public String getUserAvatarPath() {
        return userAvatarPath;
    }

    public void setUserAvatarPath(String userAvatarPath) {
        this.userAvatarPath = userAvatarPath;
    }

    @Override
    public String toString() {
        return "Users{" +
        "userId=" + userId +
        ", userLoginName=" + userLoginName +
        ", userPassword=" + userPassword +
        ", characterId=" + characterId +
        ", userAvatarPath=" + userAvatarPath +
        "}";
    }
}
