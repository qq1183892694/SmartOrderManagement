package com.example.demo3.entity;

import java.util.Date;
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
@TableName("Orders")
public class Orders {
    @TableId("orderId")
    @TableField("orderId")
    private String orderId;
    @TableField("orderDate")
    private Date orderDate;
    @TableField("orderTable")
    private String orderTable;
    @TableField("orderPrice")
    private Double orderPrice;
    @TableField("orderUserId")
    private String orderUserId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public String getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(String orderTable) {
        this.orderTable = orderTable;
    }
    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }
    public String getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(String orderUserId) {
        this.orderUserId = orderUserId;
    }

    @Override
    public String toString() {
        return "Orders{" +
        "orderId=" + orderId +
        ", orderDate=" + orderDate +
        ", orderTable=" + orderTable +
        ", orderPrice=" + orderPrice +
        ", orderUserId=" + orderUserId +
        "}";
    }
}
