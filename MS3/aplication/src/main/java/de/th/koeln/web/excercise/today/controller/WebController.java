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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
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
    @GetMapping("/upload")
    public String upload() { return "upload"; }
    @GetMapping("/statistics")
    public String statistics() { return "statistics"; }
    @GetMapping("/feed")
    public String feed(Model model) {
        model.addAttribute("post", postRepo.findAllByOrderByIdDesc());
        return "feed";
    }
    @PostMapping("/upload")
    public String uploadPost(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                             @RequestPart("duration") String duration, @RequestPart("title") String title,
                             @RequestPart("description") String description , @RequestPart("image") MultipartFile image,
                             RedirectAttributes redirectAttrs) throws IOException {

        String imageType = image.getContentType();
        InputStream in = image.getInputStream();
        BufferedImage originalImage = ImageIO.read(in);
        if(originalImage.getWidth() >= 640 && originalImage.getHeight() >= 480) {
            originalImage.getSubimage(0,0,640,480);
        } else {
            redirectAttrs.addFlashAttribute("success", "Bitte mindestens Bildformat 640 x 480 Pixel waehlen!");
            return "redirect:/success";
        }

        if(!imageType.equals("image/jpeg") && !imageType.equals("image/png")) {
            redirectAttrs.addFlashAttribute("message", "Upload fehlgeschlagen - kein PNG oder JPEG Format!");
            return "redirect:/upload";
        }
        UUID uuid = UUID.randomUUID();
        String imageName = uuid+"_"+image.getOriginalFilename();
        path = Paths.get("src/main/resources/static/uploads/"+imageName);

        Post post = new Post(date, Float.parseFloat(duration), title, description, path.toString());
        postRepo.save(post);
        post.generateImageLink(post.getId() != null);
        postRepo.save(post);
        Files.write(path, image.getBytes());
        redirectAttrs.addFlashAttribute("postId", post.getId());
        redirectAttrs.addFlashAttribute("success", "Upload erfolgreich!");
        redirectAttrs.addFlashAttribute("date", post.getDate());
        redirectAttrs.addFlashAttribute("title", post.getTitle());
        redirectAttrs.addFlashAttribute("duration", post.getDuration());
        redirectAttrs.addFlashAttribute("description", post.getDescription());
        redirectAttrs.addFlashAttribute("image", post.getImageLink());
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String success() { return "success"; }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model, RedirectAttributes redirectAttrs) {
        try {
            Post post = postRepo.findById(id).orElse(new PostNotFoundException(id));
            model.addAttribute("post", post);
            //model.addAttribute("current", postRepo.findFirst3ByOrderByIdDesc());
            return "/post";
        } catch(Exception e) {
            e.printStackTrace();
            redirectAttrs.addFlashAttribute("message", "Error loading Post");
            return "redirect:/index";
        }
    }
}
