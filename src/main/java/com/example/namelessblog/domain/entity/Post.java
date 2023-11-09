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

    private String titulo;
    private String conteudo;
    private Date dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private User autor;

}
