package com.example.namelessblog.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "usuarios" )
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Post> posts;


    @ManyToMany
    @JoinTable(name = "followers",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "seguidor_id"))
    private Set<User> followers; // nome ao qual o inverseJoinColumn será mapeado

    @ManyToMany(mappedBy = "followers")
    // está sendo mapeado pelo "followers" acima,
    // estão no mesmo arquivo pois se trata de um relacionamento many to many entre entidades iguais
    private Set<User> following;

    public User(String name, String email, String password, String username) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
    }

}
