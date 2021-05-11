package ru.slezkin.models;

import java.util.List;

public class FullPaper {

    private Integer id;

    private String name;

    private String description;

    private String place;

    private String doi;

    private List<Tag> tags;

    private List<Author> authors;

    public FullPaper() { }

    public FullPaper(Paper paper, List<Tag> tags, List<Author> authors) {
        this.id = paper.getId();
        this.name = paper.getName();
        this.description = paper.getDescription();
        this.place = paper.getPlace();
        this.doi = paper.getDoi();
        this.tags = tags;
        this.authors = authors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}