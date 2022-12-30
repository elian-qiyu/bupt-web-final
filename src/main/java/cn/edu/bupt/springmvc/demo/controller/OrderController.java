package cn.edu.bupt.springmvc.demo.controller;

import cn.edu.bupt.springmvc.demo.entities.Food;
import cn.edu.bupt.springmvc.demo.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping("")
public class OrderController {

    @Autowired
    IFoodService foodService;

    //    库存列表页
    @GetMapping("/list")
    public String list(Model model) {
//        List<Book> allBooks = new ArrayList<>(this.booksMap.values());
        List<Food> allFoods = foodService.list();
        model.addAttribute("foods", allFoods);
        System.out.println(allFoods);
        return "food/food_list";
    }

    //    新增食物页
    @GetMapping("/reg")
    public String reg() {
        return "food/food_reg";
    }

    @RequestMapping("/submit")
    public String handleFormUpload(@RequestParam("food_name") String food_name, @RequestParam("num") String num,
                                   @RequestParam("date") String date,
                                   @RequestPart("photo") MultipartFile file, Model model) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String targetFilename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
//        file.transferTo(Paths.get("./upload/"+targetFilename));

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
        food.setDate(date);
        food.setStock(num);
        foodService.save(food);
        model.addAttribute("food", food);
        return "food/upload_result";
    }
}
