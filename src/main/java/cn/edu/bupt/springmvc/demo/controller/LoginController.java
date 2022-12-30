package cn.edu.bupt.springmvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class LoginController {
    //  返回登录页面
    @GetMapping("")
    public  String index(){
        return "redirect:/login";
    }

    //  登录验证
    @GetMapping("/login")
    public  String login(){
        return "login";
    }

//    @RequestMapping("/login_try")
//    public String login(HttpSession session, @RequestParam(name = "username", required = false) String username,
//                        @RequestParam(name = "password", required = false) String password, Model model){
//
//
//        if(username.equals("admin") && password.equals("admin")){
//            session.setAttribute("user", username);
//            return "redirect:/list";
//        }else{
//            model.addAttribute("message", "账号或密码错误，请重新登录！！！");
//            return "login";
//        }
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
