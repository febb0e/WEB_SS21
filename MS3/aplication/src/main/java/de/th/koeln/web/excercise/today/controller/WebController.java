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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Controller
public class WebController {

    private static final String UPLOAD_PATH = "/home/febbo/Dokumente/SS21/WEB/WEB_SS21/MS3/aplication/src/main/resources/static/post/";

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/index")
    public String index() { return "index"; }
    @GetMapping("/feed")
    public String feed() {
        return "feed";
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
    public String uploadPost(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @RequestPart("title") String title,
                                    @RequestPart("description") String description , @RequestPart("image") MultipartFile image,
                                    RedirectAttributes redirectAttrs) throws IOException {

        String imageType = image.getContentType();

        if(!imageType.equals("image/jpeg") && !imageType.equals("image/png")) {
            redirectAttrs.addFlashAttribute("message", "Upload fehlgeschlagen - kein PNG oder JPEG Format!");
            return "redirect:/upload";
        }
        Path path = Paths.get(UPLOAD_PATH +image.getOriginalFilename());

        Post post = new Post(date, title, description, path.toString());
        postRepository.save(post);

        redirectAttrs.addFlashAttribute("post", post);
        Files.write(path, image.getBytes());
        redirectAttrs.addFlashAttribute("success", "Upload erfolgreich vom Post: "+post.getTitle());
        redirectAttrs.addFlashAttribute("image", "post/"+image.getOriginalFilename());
        return "redirect:/success";
    }
}
