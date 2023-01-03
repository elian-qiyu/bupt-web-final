package cn.edu.bupt.springmvc.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Address {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String province;
    private String city;
    private String street;
    private String user;

}
