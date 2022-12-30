package cn.edu.bupt.springmvc.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

//用户表
@Data
public class Users {
    @TableId(type = IdType.INPUT)
    private String username;

    private String password;
    private Boolean enabled;

    public Users(String username, String password, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
