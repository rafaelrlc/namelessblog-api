package com.example.namelessblog.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "posts" )
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String content;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private User author;

   public Post(String title, String content, User author, Date date) {
            this.title = title;
            this.content = content;
            this.author = author;
            this.date = date;
        }


}
