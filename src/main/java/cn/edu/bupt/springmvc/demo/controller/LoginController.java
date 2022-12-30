package cn.edu.bupt.springmvc.demo.controller;

import cn.edu.bupt.springmvc.demo.entities.Users;
import cn.edu.bupt.springmvc.demo.mapper.UsersMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class LoginController {

    @Resource
    UsersMapper usersMapper;

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

    @GetMapping("/register")
    String register(){
        return "register";
    }

    @PostMapping("/register")
    String registerDone(Model model, @RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password,
                        @RequestParam(name = "password2") String password2) {
        System.out.println(username + password + password2);
        Users users = usersMapper.selectById(username);
        if (users != null) {
            model.addAttribute("msg", "用户名已存在！");
            return "register";
        } else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            users = new Users(username, passwordEncoder.encode(password), true);
            usersMapper.insert(users);
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
