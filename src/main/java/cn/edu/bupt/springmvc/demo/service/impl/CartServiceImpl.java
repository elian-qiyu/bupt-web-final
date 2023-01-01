package cn.edu.bupt.springmvc.demo.service.impl;

import cn.edu.bupt.springmvc.demo.entities.Cart;
import cn.edu.bupt.springmvc.demo.mapper.CartMapper;
import cn.edu.bupt.springmvc.demo.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
}
