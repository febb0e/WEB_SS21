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

import java.io.IOException;
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
    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }
    @GetMapping("/success")
    public String success() { return "success"; }
    @PostMapping("/upload")
    public String formControllerEnc(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @RequestPart("title") String title,
                                    @RequestPart("description") String description , @RequestPart("image") MultipartFile image,
                                    RedirectAttributes redirectAttrs) {
        try {
            Post post = new Post(date,  title, description, image.getBytes());
        } catch (IOException e) {
            redirectAttrs.addFlashAttribute("error", "Upload fehlgeschlagen");
            return "redirect:/upload";
        }
        redirectAttrs.addFlashAttribute("success", "Upload erfolgreich!");
        return "redirect:/success";
    }

}
