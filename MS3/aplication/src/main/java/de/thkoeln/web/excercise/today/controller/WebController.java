package de.thkoeln.web.excercise.today.controller;

import de.thkoeln.web.excercise.today.entities.Post;
import de.thkoeln.web.excercise.today.entities.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/upload")
    public String index(Model model) {
        Post post = new Post();
        post.setId();
        post.setDate();
        post.setTitle();
        post.setDescription();
        post.setImage();

        postRepository.save(post);
        model.addAttribute("post", postRepository.findAll());
        return "posts";
    }

    @GetMapping("/")
    public String home() {
        return "index";
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
    @GetMapping("upload_get")
    public String formControllerGet(@RequestParam("date") String date, @RequestParam("title") String title,
                                    @RequestParam("description") String description ,@RequestParam("image") String image) {
        System.out.println("Date: "+date);
        System.out.println("Title: "+title);
        System.out.println("Description: "+description);
        System.out.println("Image: "+image);
        return "redirect:/";
    }
    @PostMapping("upload_post")
    public String formControllerPost(@RequestParam("date") String date, @RequestParam("title") String title,
                                     @RequestParam("description") String description ,@RequestParam("image") String image) {
        System.out.println("Date: " + date);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Image: " + image);
        return "redirect:/";
    }
    public String formControllerEnc(@RequestPart("date") String date, @RequestPart("title") String title,
                                    @RequestPart("description") String description , @RequestPart("image") MultipartFile image,
                                    RedirectAttributes redirectAttrs) {
        System.out.println("Date: " + date);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Image: " + image.getOriginalFilename());
        redirectAttrs.addFlashAttribute("message", "Test");
        return "redirect:/";
    }
}
