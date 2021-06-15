package de.th.koeln.web.excercise.today.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String title;
    private String description;
    @Lob
    private byte[] image;

    public Post() {}
    public Post(Date date, String title, String description, byte[] image) {
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

    public byte[] getImage() {
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

    public void setImage(byte[] image) {
        this.image = image;
    }
}
