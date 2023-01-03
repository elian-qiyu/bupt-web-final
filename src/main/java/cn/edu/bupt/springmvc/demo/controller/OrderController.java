package cn.edu.bupt.springmvc.demo.controller;

import cn.edu.bupt.springmvc.demo.entities.Authorities;
import cn.edu.bupt.springmvc.demo.entities.Bbs;
import cn.edu.bupt.springmvc.demo.entities.Food;
import cn.edu.bupt.springmvc.demo.entities.Users;
import cn.edu.bupt.springmvc.demo.service.IAuthoritiesService;
import cn.edu.bupt.springmvc.demo.service.IBbsService;
import cn.edu.bupt.springmvc.demo.service.IFoodService;
import cn.edu.bupt.springmvc.demo.service.IUsersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.Query;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/app")
public class OrderController {

    @Autowired
    IFoodService foodService;

    @Autowired
    IBbsService bbsService;

    @Autowired
    IUsersService usersService;

    @Autowired
    IAuthoritiesService authoritiesService;

    //  主页
    @GetMapping("/index")
    public String index(Model model, Authentication auth) {
        model.addAttribute("username", auth.getName());
        model.addAttribute("author", auth.getAuthorities().toString());
        System.out.println(auth.getAuthorities());
        Bbs bbs_empty = new Bbs();
        bbs_empty.setTime("");
        bbs_empty.setId((long)0);
        bbs_empty.setDate("");
        bbs_empty.setTitle("");
        bbs_empty.setBody("");

        List<Bbs> allBbs = bbsService.list();
        allBbs.add(0, bbs_empty);
        allBbs.add(0, bbs_empty);
        allBbs.add(0, bbs_empty);
        int bbs_count = (int)allBbs.size();
        System.out.println(bbs_count);
        model.addAttribute("bbs_count", bbs_count);
        model.addAttribute("bbs_1", allBbs.get(bbs_count - 1));
        model.addAttribute("bbs_2", allBbs.get(bbs_count - 2));
        model.addAttribute("bbs_3", allBbs.get(bbs_count - 3));
        return "food/index";
    }


    //    库存列表页
    @GetMapping("/list")
    public String list(Model model, Authentication auth) {
//        List<Book> allBooks = new ArrayList<>(this.booksMap.values());
        model.addAttribute("author", auth.getAuthorities().toString());
        model.addAttribute("username", auth.getName());
        List<Food> allFoods = foodService.list();
        model.addAttribute("foods", allFoods);
        System.out.println(allFoods);
        return "food/food_list";
    }

    @GetMapping("/user/order")
    public String food_order(Model model, Authentication auth){
        model.addAttribute("author", auth.getAuthorities().toString());
        return "food/food_order";
    }

    //    新增食物页
    @GetMapping("/admin/food_reg")
    public String food_reg(Model model, Authentication auth) {
        model.addAttribute("author", auth.getAuthorities().toString());
        return "food/food_reg";
    }

    //    新增BBS页
    @GetMapping("/admin/bbs_reg")
    public String bbs_reg(Model model, Authentication auth) {
        model.addAttribute("author", auth.getAuthorities().toString());
        return "food/bbs_reg";
    }


    @RequestMapping("/admin/submit/bbs")
    public String handleFormUpload(@RequestParam("bbs_title") String bbs_title, @RequestParam("bbs_body") String bbs_body,
                                   @RequestParam(value = "id", required = false) String id){
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        Date date = new Date();// 获取当前时间
        Bbs bbs = new Bbs();

        bbs.setTitle(bbs_title);
        bbs.setBody(bbs_body);
        sdf.applyPattern("yyyy/MM/dd");
        bbs.setDate(sdf.format(date));
        System.out.println(sdf.format(date));
        sdf.applyPattern("HH:mm");
        bbs.setTime(sdf.format(date));
        System.out.println(sdf.format(date));
        if(id == null) bbsService.save(bbs);
        else {
            bbs.setId((long)Integer.parseInt(id));
            bbsService.updateById(bbs);
        }
        return "redirect:/app/index";
    }

    @RequestMapping("/admin/submit/food")
    public String handleFormUpload(@RequestParam("food_name") String food_name, @RequestParam("num") String num,
                                   @RequestParam("price") String price,
                                   @RequestPart("photo") MultipartFile file, Model model) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String targetFilename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
//        file.transferTo(Paths.get("./upload/"+targetFilename));
        System.out.println("1");
        //保存上传的资源文件路径，路径在部署jar包或项目的同级目录。
        String path = System.getProperty("user.dir") + "/static/upload/";
        File dir = new File(path);
        // 如果不存在则创建目录
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file.transferTo(Paths.get(path + targetFilename));
        path = "../../upload/" + targetFilename;
        System.out.println(path);
        Food food = new Food();
        food.setAddr(path);
        food.setName(food_name);
        food.setPrice(price);
        food.setStock(num);
        foodService.save(food);
//        model.addAttribute("food", food);
        return "redirect:/app/list";
    }

    @GetMapping("/admin/delete")
    public String delete(@RequestParam("type") String type, @RequestParam("id") int id){
        switch (type){
            case "bbs":
                System.out.println("Delete id:" +id);
                bbsService.removeById(id);
                return "redirect:/app/index";
            case "user":
                Authorities authorities = authoritiesService.getById(id);
                Users users = usersService.getById(authorities.getUsername());
                System.out.println(users);
                String name = users.getUsername();
                System.out.println(name);
                authoritiesService.removeById(id);
                usersService.removeById(name);

                return "redirect:/app/admin/user";
        }

        return "err";
    }

    @GetMapping("/admin/edit")
    public String edit(@RequestParam("type") String type, @RequestParam("id") int id, Model model, Authentication auth){
        switch (type){
            case "bbs":
                Bbs bbs = new Bbs();
                bbs = bbsService.getById(id);
                model.addAttribute("bbs", bbs);
                model.addAttribute("author", auth.getAuthorities().toString());
                return "food/bbs_edit";
            case "user":
                Authorities authorities = new Authorities();
                authorities = authoritiesService.getById(id);

                List<Users> users = usersService.list();
                model.addAttribute("auth", authorities);
                model.addAttribute("user",users.get(id-1));
                System.out.println(authorities);
                System.out.println(users);
                return "food/user_edit";
        }
        return "err";
    }

    @GetMapping("/order")
    public String order(){
        return "food/food_order";
    }

    @GetMapping("/admin/user")
    public String user(Model model, Authentication auth){
        model.addAttribute("author", auth.getAuthorities().toString());
        model.addAttribute("username", auth.getName());
        List<Authorities> authentications = authoritiesService.list();
        List<Users> users = usersService.list();
        model.addAttribute("users", authentications);
        model.addAttribute("users1", users);

        return "food/user_list";
    }


    @RequestMapping("/admin/submit/user")
    public String handleFormUpload(@RequestParam("id") int id, @RequestParam("username") String username,
                                   @RequestParam(value = "auth") String auth, @RequestParam(value = "radio") boolean enabled){
        Authorities authorities = new Authorities();
        Users users = new Users();
        authorities = authoritiesService.getById(id);

        authorities.setAuthority(auth);
        authorities.setUsername(username);
        users = usersService.getById(username);
        users.setEnabled(enabled);
        authoritiesService.updateById(authorities);
        usersService.updateById(users);
        System.out.println(users);

        return "redirect:/app/admin/user";
    }

    @GetMapping("/adr")
    public String address(){
        return "food/adr_list";
    }

}
