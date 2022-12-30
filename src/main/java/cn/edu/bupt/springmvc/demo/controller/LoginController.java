package cn.edu.bupt.springmvc.demo.controller;

import cn.edu.bupt.springmvc.demo.entities.Authorities;
import cn.edu.bupt.springmvc.demo.entities.Users;
import cn.edu.bupt.springmvc.demo.mapper.AuthoritiesMapper;
import cn.edu.bupt.springmvc.demo.mapper.FoodMapper;
import cn.edu.bupt.springmvc.demo.mapper.UsersMapper;
import cn.edu.bupt.springmvc.demo.service.CustomUserService;
import cn.edu.bupt.springmvc.demo.service.IAuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    IAuthoritiesService iAuthoritiesService;

    @Resource
    UsersMapper usersMapper;


    //  返回登录页面
    @GetMapping("")
    public  String index(){
        return "redirect:/login";
    }

    //  登录验证
    @GetMapping("/login")
    public  String login(HttpSession session, Model model){
        if(session.getAttribute("login_fail") == "1")
            model.addAttribute("message", "账号或密码错误，请重新登录！！！");
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
        System.out.println("注册信息："+ username + password + password2);
        if(!password.equals(password2)){
            model.addAttribute("message", "两次密码必须一致！");
            return "register";
        }else{
            Users users = usersMapper.selectById(username);
            if (users != null) {
                model.addAttribute("message", "用户名已存在！");
                return "register";
            } else {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                users = new Users(username, passwordEncoder.encode(password), true);
                usersMapper.insert(users);
                Authorities authorities = new Authorities();
                authorities.setId((long) (iAuthoritiesService.list().size() + 1));
                authorities.setUsername(username);
                authorities.setAuthority("ROLE_USER");
                iAuthoritiesService.save(authorities);
                System.out.println(authorities);

                return "redirect:/login";
            }
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
