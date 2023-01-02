package cn.edu.bupt.springmvc.demo.service.impl;

import cn.edu.bupt.springmvc.demo.entities.Cart;
import cn.edu.bupt.springmvc.demo.mapper.CartMapper;
import cn.edu.bupt.springmvc.demo.mapper.FoodMapper;
import cn.edu.bupt.springmvc.demo.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private FoodMapper foodMapper;
    @Override
    public void addtoCart(Long food_id, String username, long num) {
        Cart result=cartMapper.findByidanduser(food_id,username);
        if(result==null){
            Cart cart=new Cart();
            cart.setfood(food_id);
            cart.setuser(username);
            cart.setnum(num);
            cartMapper.insert(cart);
        }
        else{
            long amount=result.getNum()+num;
            cartMapper.updateNumByid(result.getid(),amount);
        }
    }
}
