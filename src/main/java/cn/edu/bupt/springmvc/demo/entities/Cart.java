package cn.edu.bupt.springmvc.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Cart {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private Long food_id;
    private Long num;
}