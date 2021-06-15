package de.th.koeln.web.excercise.today.controller;

import de.th.koeln.web.excercise.today.entities.Post;
import de.th.koeln.web.excercise.today.entities.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class WebController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/index")
    public String index() { return "index"; }
    @GetMapping("/posts")
    public String posts() {
        return "posts";
    }
    @GetMapping("/post1")
    public String post1() { return "post1"; }
    @GetMapping("post2")
    public String post2() { return "post2"; }
    @GetMapping("post3")
    public String post3() { return "post3"; }
    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }
    @GetMapping("/success")
    public String success() { return "success"; }
    @GetMapping("/failure")
    public String failure() { return "failure"; }
    @PostMapping("/upload")
    public String formControllerEnc(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @RequestPart("title") String title,
                                    @RequestPart("description") String description , @RequestPart("image") MultipartFile image,
                                    RedirectAttributes redirectAttrs) {
        Post post;
        try {
            post = new Post(date,  title, description, image.getBytes());
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Upload fehlgeschlagen!");
            return "redirect:/failure";
        }
        redirectAttrs.addFlashAttribute("success", "Upload erfolgreich vom Post: ");
        redirectAttrs.addFlashAttribute("post", post.getTitle());
        return "redirect:/success";
    }

}
