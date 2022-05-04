package com.sciodev.blogoner.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, max = 30)
    @NotEmpty
    private String title;

    @Size(min = 3, max = 50)
    @NotEmpty
    private String author;

    @Size(min = 3, max = 50)
    @NotEmpty
    private String aboutOf;
    @Size(min = 1, max = 200)
    @NotEmpty
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAboutOf() {
        return aboutOf;
    }

    public void setAboutOf(String aboutOf) {
        this.aboutOf = aboutOf;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
