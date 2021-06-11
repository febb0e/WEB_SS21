package de.thkoeln.web.excercise.today.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExcerciseTodayController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }
    @GetMapping("/posts")
    public String posts() {
        return "posts";
    }
    @GetMapping("/post1")
    public String post1() {
        return "post1";
    }
    @GetMapping("/post2")
    public String post2() {
        return "post2";
    }
    @GetMapping("/post3")
    public String post3() {
        return "post3";
    }
}
