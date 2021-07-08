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
    private LocalDate date;
    private float duration;
    private String title;
    @Lob
    @Column(columnDefinition = "LONGVARCHAR")
    private String description;
    @Lob
    private String image;
    private String imageLink;

    public Post() {}
    public Post(LocalDate date, float duration, String title, String description, String image) {
        this.date = date;
        this.duration = duration;
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

    public float getDuration() { return duration; }
    public void setDuration(float duration) { this.duration = duration; }

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
