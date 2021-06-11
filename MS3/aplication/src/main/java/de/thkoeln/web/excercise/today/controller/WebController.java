package de.thkoeln.web.excercise.today.controller;

import de.thkoeln.web.excercise.today.entities.Post;
import de.thkoeln.web.excercise.today.entities.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    private PostRepository postRepository;

    public String index() {
        Post post = new Post("","","");
        return "Post uploaded successful!";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/upload.html")
    public String upload() {
        return "upload";
    }
    @GetMapping("/posts.html")
    public String posts() {
        return "posts";
    }
    @GetMapping("/post1.html")
    public String post1() {
        return "post1";
    }
    @GetMapping("/post2.html")
    public String post2() {
        return "post2";
    }
    @GetMapping("/post3.html")
    public String post3() {
        return "post3";
    }
}
