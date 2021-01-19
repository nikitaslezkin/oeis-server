package ru.slezkin.models;

import javax.persistence.*;

@Entity
@Table(name="circuit_paper")
public class CircuitPaper {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "circuit_id", referencedColumnName = "id")
    private Circuit circuit;

    @ManyToOne
    @JoinColumn(name = "paper_id", referencedColumnName = "id")
    private Paper paper;

    public CircuitPaper() { }

    public CircuitPaper(Circuit circuit, Paper paper) {
        this.circuit = circuit;
        this.paper = paper;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }
}