package de.th.koeln.web.excercise.today.controller;

import de.th.koeln.web.excercise.today.entities.Post;
import de.th.koeln.web.excercise.today.entities.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostRepository postRepo;

    @GetMapping("/")
    public Iterable<Post> getPosts() {
        return postRepo.findAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable(value="id") Long postId) {
        return postRepo.findById(postId).orElse(new PostNotFoundException(postId));
    }

    @PostMapping("/")
    public Post postPosts(@RequestBody Post post) {
        Post postWithId = postRepo.save(post);
        return postWithId;
    }

    @GetMapping(value = "/{id}/img", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImg(@PathVariable(value= "id") Long postId) {
        try{
            Post post = postRepo.findById(postId).orElse(new PostNotFoundException(postId));
            Path path = Paths.get(String.valueOf(WebController.path));

            //Resize all Images to 960 x 540 Pixels
            BufferedImage input = ImageIO.read(new File(path.toString()));
            BufferedImage output = new BufferedImage(960, 540, input.getType());
            Graphics2D resize = output.createGraphics();
            resize.drawImage(input, 0,0, 960, 540, null);
            resize.dispose();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(output, "png", out);
            byte [] image = out.toByteArray();
            return image;
        } catch(IOException e) {
                System.out.println(e);
            }
        return null;
    }
}
