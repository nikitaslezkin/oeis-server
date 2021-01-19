package ru.slezkin.models;

import javax.persistence.*;

@Entity
@Table(name = "circuit")
public class Circuit {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    private String description;

    private String ckt;

    private String truth_table;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "basis_id", referencedColumnName = "id")
    private Basis basis;

    private Boolean checked;

    public Circuit() { }

    public Circuit(String name, String description, String ckt, Basis basis, String truth_table, User user, Boolean checked) {
        this.name = name;
        this.description = description;
        this.ckt = ckt;
        this.basis = basis;
        this.truth_table = truth_table;
        this.user = user;
        this.checked = checked;
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
}