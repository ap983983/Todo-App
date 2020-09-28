package com.main.app.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "todo_list")
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private int listId;

    @Column(name = "list_sub")
    private String listSub;

    @Column(name = "list_msg")
    @NotBlank(message = "Please Enter Message")
    private String listMsg;

}
