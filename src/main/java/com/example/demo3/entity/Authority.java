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
@TableName("Authority")
public class Authority{

    @TableId("characterId")
    @TableField("characterId")
    private Integer characterId;
    @TableField("sysName")
    private String sysName;
    @TableField("characterName")
    private String characterName;

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }
    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }
    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    @Override
    public String toString() {
        return "Authority{" +
        "sysName=" + sysName +
        ", characterId=" + characterId +
        ", characterName=" + characterName +
        "}";
    }
}
