package com.example.demo.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
//@RestController == @Controller + @ResponseBody
public class HelloController {

    private LimitConfig config;

    //从配置文件读取
    @Value("${age}")
    private BigDecimal age;

    @Value("${name}")
    private String name;

    @Value("${description}")
    private String description;


    @GetMapping("/hello")
    public String say() {
        return description;
    }


    //通过配置类获取数据
    @Autowired
    public HelloController(LimitConfig config) {
        this.config = config;
    }

    @GetMapping({"/hello1", "/hello2"})
    public String aaa() {
        return config.getDescription();
    }

    @GetMapping("/hello3/{id}")
    public String aaa(@PathVariable("id") Integer id) {
        return config.getDescription() + "       你输入的值是:" + id;
    }

    @PostMapping("/hello4")
    public String bbb() {
        return "13213213";
    }

    //url后加/再跟参数-------参数必须要
    @PostMapping("/hello5/{id}")
    public String ccc(@PathVariable("id") Integer id) {
        return id + "";
    }

    //url后加？再跟参数-------参数必须，可以跟多个
    @PostMapping("/hello6")
    public String ddd(@RequestParam("id") Integer id, String name) {
        return "id:" + id + "     name:" + name;
    }
//    @PostMapping("/hello6")
//    public String ddd(@RequestParam Integer id,  String name) {
//        return "id:" + id +"     name:"+name;
//    }

    //url后加？再跟参数-------参数可不加，可以跟多个
    @PostMapping("/hello7")
    public String eee(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id, String name) {
        return "id:" + id + "     name:" + name;
    }
}
