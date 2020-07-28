package com.example.demo3.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("OrderGoodList")
public class OrderGoodList extends Model<OrderGoodList> {
  @TableId("id")
  @TableField("id")
  private String id;
  @TableField("dishId")
  private String dishId;
  @TableField("dishName")
  private String dishName;
  @TableField("dishPrice")
  private double dishPrice;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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


  public double getDishPrice() {
    return dishPrice;
  }

  public void setDishPrice(double dishPrice) {
    this.dishPrice = dishPrice;
  }
  @Override
  protected Serializable pkVal() {
    return this.dishId;
  }

  @Override
  public String toString() {
    return "OrderGoodList{" +
            "id='" + id + '\'' +
            ", dishId='" + dishId + '\'' +
            ", dishName='" + dishName + '\'' +
            ", dishPrice=" + dishPrice +
            '}';
  }
}
