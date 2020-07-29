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
@TableName("OrderDetails")
public class OrderDetails {
    @TableId("id")
    @TableField("id")
    private String id;
    @TableField("dishId")
    private String dishId;
    @TableField("orderId")
    private String orderId;
    @TableField("dishName")
    private String dishName;
    @TableField("dishNumber")
    private String dishNumber;
    @TableField("dishPrice")
    private Double dishPrice;
    @TableField("dishStatus")
    private String dishStatus;
    @TableField("orderTable")
    private String orderTable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(String orderTable) {
        this.orderTable = orderTable;
    }

    public String getDishStatus() {
        return dishStatus;
    }

    public void setDishStatus(String dishStatus) {
        this.dishStatus = dishStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }
    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
    public String getDishNumber() {
        return dishNumber;
    }

    public void setDishNumber(String dishNumber) {
        this.dishNumber = dishNumber;
    }
    public Double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(Double dishPrice) {
        this.dishPrice = dishPrice;
    }


    @Override
    public String toString() {
        return "OrderDetails{" +
                "id='" + id + '\'' +
                ", dishId='" + dishId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", dishName='" + dishName + '\'' +
                ", dishNumber='" + dishNumber + '\'' +
                ", dishPrice=" + dishPrice +
                ", dishStatus='" + dishStatus + '\'' +
                ", orderTable='" + orderTable + '\'' +
                '}';
    }
}
