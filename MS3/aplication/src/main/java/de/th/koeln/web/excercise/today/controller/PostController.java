package de.th.koeln.web.excercise.today.controller;

import de.th.koeln.web.excercise.today.entities.Post;
import de.th.koeln.web.excercise.today.entities.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/posts/api/v1")
public class PostController {

    private static final String UPLOAD_PATH = "/home/febbo/Dokumente/SS21/WEB/WEB_SS21/MS3/aplication/src/main/resources/static/post/";

    @Autowired
    PostRepository postRepo;

    @GetMapping("/posts")
    public Iterable<Post> getPosts() {
        return postRepo.findAll();
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable(value="id") Long postId) {
        return postRepo.findById(postId).orElse(new PostNotFoundException(postId));
    }

    @GetMapping(value = "/{id}/img", produces = MediaType.IMAGE_JPEG_VALUE)

    @PostMapping("/posts")
    public Post postPosts(@RequestBody Post post) {
        Post postWithId = postRepo.save(post);
        return postWithId;
    }

    public byte[] getImg(@PathVariable(value= "id") Long postId, @PathVariable(value="image") String image) {
        try{
            Post post = postRepo.findById(postId).orElse(new PostNotFoundException(postId));
            Path path = Paths.get(UPLOAD_PATH + image);
            return Files.readAllBytes(path);
        } catch(IOException e) {
                System.out.println(e);
            }
        return null;
    }
}
