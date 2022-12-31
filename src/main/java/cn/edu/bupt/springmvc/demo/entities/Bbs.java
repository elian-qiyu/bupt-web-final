package cn.edu.bupt.springmvc.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Bbs {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String body;
    private String date;
    private String time;
}
