package com.practice.myappvs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="STUDENT")

public class Student {
    
@Id
@Column(name="ID")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(name="MARK")
private int mark;

@Column(name="NAME")
private String name;

@OneToOne(mappedBy ="student")
@JsonBackReference
private Message message;


//now if we want to directional mapping means means we can fetch msg from student to then
// we will use  JSONManagedReference in parent and  JSONBackreference in child
    //like

}
