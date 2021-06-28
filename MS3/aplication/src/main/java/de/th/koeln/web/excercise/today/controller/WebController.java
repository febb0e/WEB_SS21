package de.th.koeln.web.excercise.today.controller;

import de.th.koeln.web.excercise.today.entities.Post;
import de.th.koeln.web.excercise.today.entities.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
public class WebController {

    public static Path path;

    @Autowired
    private PostRepository postRepo;

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/index")
    public String index() { return "index"; }
    @GetMapping("/feed")
    public String feed(Model model) {
        model.addAttribute("current", postRepo.findFirst3ByOrderByIdDesc());
        model.addAttribute("all", postRepo.findAllByOrderByIdDesc());
        return "feed";
    }
    @GetMapping("/post1")
    public String post1() { return "post1"; }
    @GetMapping("/post2")
    public String post2() { return "post2"; }
    @GetMapping("/post3")
    public String post3() { return "post3"; }
    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }
    @GetMapping("/failure")
    public String failure() { return "failure"; }
    @PostMapping("/upload")
    public String uploadPost(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestPart("title") String title,
                             @RequestPart("description") String description , @RequestPart("image") MultipartFile image,
                             RedirectAttributes redirectAttrs) throws IOException {

        String imageType = image.getContentType();

        if(!imageType.equals("image/jpeg") && !imageType.equals("image/png")) {
            redirectAttrs.addFlashAttribute("message", "Upload fehlgeschlagen - kein PNG oder JPEG Format!");
            return "redirect:/upload";
        }
        UUID uuid = UUID.randomUUID();
        String imageName = uuid+"_"+image.getOriginalFilename();
        path = Paths.get("src/main/resources/static/uploads/"+imageName);

        Post post = new Post(date, title, description, path.toString());
        postRepo.save(post);
        post.generateImageLink(post.getId() != null);
        postRepo.save(post);
        Files.write(path, image.getBytes());
        redirectAttrs.addFlashAttribute("postId", post.getId());
        redirectAttrs.addFlashAttribute("success", "Upload erfolgreich vom Post: "+post.getTitle());
        redirectAttrs.addFlashAttribute("image", WebController.path);
        return "redirect:/success";
    }
    @GetMapping("/success")
    public String success(HttpServletRequest request, RedirectAttributes redirectAttrs, Model model) {
        model.addAttribute("current", postRepo.findFirst3ByOrderByIdDesc());
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if(inputFlashMap != null) {
            Long id = (Long) inputFlashMap.get("postId");
            Post post = postRepo.findById(id).orElse(new PostNotFoundException(id));
            model.addAttribute("post", post);
            return "post";
        } else {
            redirectAttrs.addFlashAttribute("message", "Error loading Post");
            return "redirect:/uplaod";
        }
    }
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model, RedirectAttributes redirectAttrs) {
        try {
            Post post = postRepo.findById(id).orElse(new PostNotFoundException(id));
            model.addAttribute("post", post);
            model.addAttribute("current", postRepo.findFirst3ByOrderByIdDesc());
            return "post";
        } catch(Exception e) {
            e.printStackTrace();
            redirectAttrs.addFlashAttribute("message", "Error loading Post");
            return "redirect:/index";
        }
    }
}
