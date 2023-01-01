package cn.edu.bupt.springmvc.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Food {
    @TableId(type = IdType.AUTO)
    private long id;
    private String addr;
    private String name;
    private String price;
    private String stock;
}
