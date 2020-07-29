package com.example.demo3.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("proclamation")
public class proclamation {
    @TableField("proclamationId")
    @TableId("proclamationId")
    private  String proclamationId;
    @TableField("proclamationContext")
    private String proclamationContext;

    public String getProclamationContext() {
        return proclamationContext;
    }

    public void setProclamationContext(String proclamationContext) {
        this.proclamationContext = proclamationContext;
    }

    public void setProclamationId(String proclamationId) {
        this.proclamationId = proclamationId;
    }



    public String getProclamationId() {
        return proclamationId;
    }


}
