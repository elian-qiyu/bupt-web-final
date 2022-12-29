package cn.edu.bupt.springmvc.demo.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Food {
//    @TableId("type = IdType.AUTO")
    @TableId("addr")
//    private int id;
    private String addr;
    private String name;
    private String date;
    private String stock;
}
