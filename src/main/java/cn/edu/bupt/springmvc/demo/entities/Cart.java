package cn.edu.bupt.springmvc.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Cart {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private Long food_id;
    private Long num;

    public void setid(Long id) {
        this.id=id;
    }

    public void setfood(Long food_id) {
        this.food_id=food_id;
    }

    public void setuser(String username) {
        this.username=username;
    }

    public void setnum(long num) {
        this.num=num;
    }

    public long getNum() {
        return this.num;
    }

    public Long getid() {
        return this.id;
    }
}