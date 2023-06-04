package com.example.iozzspringbootlz.db;

import com.example.iozzspringbootlz.Student;

import javax.persistence.*;
import java.util.Set;

@Entity
public class StudentRow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String number;
    private String grupa;

    @OneToMany(mappedBy = "student")
    private Set<ScoreRow> scores;

    public Set<ScoreRow> getScores() {
        return scores;
    }

    public void setScores(Set<ScoreRow> scores) {
        this.scores = scores;
    }

    public Student toStudent()
    {
        return new Student(
                this.getId(),
                this.getName(),
                this.getNumber(),
                this.getGrupa());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String group1) {
        this.grupa = group1;
    }

    public StudentRow(String name, String number, String grupa) {
        this.name = name;
        this.number = number;
        this.grupa = grupa;
    }

    protected StudentRow() {
    }
}
