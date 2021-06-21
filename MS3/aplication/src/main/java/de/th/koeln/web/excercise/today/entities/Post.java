package de.th.koeln.web.excercise.today.entities;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty(required = true, notes="Date of post creation", example="21.06.2021")
    private Date date;
    @ApiModelProperty(required = true, notes="Ttile of action in post", example="My new post title")
    private String title;
    @ApiModelProperty(required = true, notes="Description of what happend in the post", example="Here is described what was going on")
    private String description;
    @Lob
    @ApiModelProperty(required = true)
    private String image;

    public Post() {}
    public Post(Date date, String title, String description, String image) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
