package de.thkoeln.web.excercise.today.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String date;
    private String title;
    private String description;
    private String image;

    public Post() {
    }

    public Long getId() {return id;}
    public void setId() {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate() {
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle() {
        this.title = title;
    }
    public String getDescription() { return description; }
    public void setDescription() {
        this.description = description;
    }
    public String getImage() { return image; }
    public void setImage() {
        this.image = image;
    }
}
