package com.example.demo3.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author fyq
 * @since 2020-07-23
 */
@Data
@TableName("Dishs")
public class Dishs{

    /**
     * 我更改了dishId为Int型，原来是String
     */
    @TableId("dishId")
    @TableField("dishId")
    private int dishId;

    @TableField("dishName")
    private String dishName;
    @TableField("dishIntroduction")
    private String dishIntroduction;
    @TableField("dishDetails")
    private String dishDetails;
    @TableField("isRecommend")
    private Integer isRecommend;
    @TableField("dishPrice")
    private Double dishPrice;
    @TableField("dishAvatarPath")
    private String dishAvatarPath;

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }
    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
    public String getDishIntroduction() {
        return dishIntroduction;
    }

    public void setDishIntroduction(String dishIntroduction) {
        this.dishIntroduction = dishIntroduction;
    }
    public String getDishDetails() {
        return dishDetails;
    }

    public void setDishDetails(String dishDetails) {
        this.dishDetails = dishDetails;
    }
    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }
    public Double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(Double dishPrice) {
        this.dishPrice = dishPrice;
    }
    public String getDishAvatarPath() {
        return dishAvatarPath;
    }

    public void setDishAvatarPath(String dishAvatarPath) {
        this.dishAvatarPath = dishAvatarPath;
    }

    @Override
    public String toString() {
        return "Dishs{" +
        "dishId=" + dishId +
        ", dishName=" + dishName +
        ", dishIntroduction=" + dishIntroduction +
        ", dishDetails=" + dishDetails +
        ", isRecommend=" + isRecommend +
        ", dishPrice=" + dishPrice +
        ", dishAvatarPath=" + dishAvatarPath +
        "}";
    }
}
