package cn.edu.bupt.springmvc.demo.controller;

import cn.edu.bupt.springmvc.demo.service.ICartService;
import cn.edu.bupt.springmvc.demo.util.JsonRsult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;


import javax.servlet.http.HttpSession;

import static javax.security.auth.callback.ConfirmationCallback.OK;

@RestController
@RequestMapping("carts")
public class CartController {

    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonRsult<Void> addToCart(Long num, Long food_id, HttpSession session, Authentication authentication) {
        cartService.addtoCart(food_id, authentication.getName(), num);
        return new JsonRsult<>(OK);
    }
}
