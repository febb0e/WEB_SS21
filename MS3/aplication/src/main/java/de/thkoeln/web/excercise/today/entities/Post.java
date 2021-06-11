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

    public Post(String date, String title, String description) {
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public Long getId() {return id;}

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
