package de.th.koeln.web.excercise.today.entities;

import io.swagger.annotations.ApiModelProperty;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty(required = true, notes="Date of post creation", example="21.06.2021")
    private LocalDate date;
    @ApiModelProperty(required = true, notes="Ttile of action in post", example="My new post title")
    private String title;
    @ApiModelProperty(required = true, notes="Description of what happend in the post", example="Here is described what was going on")
    private String description;
    @Lob
    @ApiModelProperty(required = true)
    private String image;
    private String imageLink;

    public Post() {}
    public Post(LocalDate date, String title, String description, String image) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getImageLink() { return imageLink; }
    public void setImageLink() { this.imageLink = imageLink; }
    public void generateImageLink(boolean saved) {
        if (saved) this.imageLink = "/api/posts/"+this.id+"/img";
    }
}
