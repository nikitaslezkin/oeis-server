package ru.slezkin.models;

import java.util.List;

public class FullCircuit {

    private Integer id;

    private String name;

    private String description;

    private String ckt;

    private String truth_table;

    private User user;

    private Basis basis;

    private Boolean checked;

    private List<Tag> tags;

    private List<Paper> papers;

    public FullCircuit() { }

    public FullCircuit(Circuit circuit, List<Tag> tags, List<Paper> papers) {
        this.id = circuit.getId();
        this.name = circuit.getName();
        this.description = circuit.getDescription();
        this.ckt = circuit.getCkt();
        this.basis = circuit.getBasis();
        this.truth_table = circuit.getTruth_table();
        this.user = circuit.getUser();
        this.checked = circuit.getChecked();
        this.tags = tags;
        this.papers = papers;
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

    public String getCkt() {
        return ckt;
    }

    public void setCkt(String ckt) {
        this.ckt = ckt;
    }

    public String getTruth_table() {
        return truth_table;
    }

    public void setTruth_table(String truth_table) {
        this.truth_table = truth_table;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Basis getBasis() {
        return basis;
    }

    public void setBasis(Basis basis) {
        this.basis = basis;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}