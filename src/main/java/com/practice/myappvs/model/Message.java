package com.practice.myappvs.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.practice.myappvs.model.Student;
import lombok.Data;

@Entity
@Table(name = "msg")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Student student;

    public Message() {

    }

    public Message(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}