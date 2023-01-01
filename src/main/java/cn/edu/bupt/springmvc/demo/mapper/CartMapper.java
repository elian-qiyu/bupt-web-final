package cn.edu.bupt.springmvc.demo.mapper;

import cn.edu.bupt.springmvc.demo.entities.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface CartMapper extends BaseMapper<Cart> {
    int insert(Cart cart);//加入商品

    int updateNumByid(Long id,Long num);//更新数量

    int findByidanduser(Long food_id,String username);//根据用户和食物查询


}
