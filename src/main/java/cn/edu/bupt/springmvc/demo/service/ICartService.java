package cn.edu.bupt.springmvc.demo.service;

import cn.edu.bupt.springmvc.demo.entities.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ICartService extends IService<Cart> {
    void addtoCart(Long food_id, String username, long num);//添加商品到购物车
}
