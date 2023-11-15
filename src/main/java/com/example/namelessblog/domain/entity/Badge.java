package com.example.namelessblog.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "badges")
public class Badge {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @JsonIgnore
    @ManyToMany(mappedBy = "badges")
    private Set<Post> postList = new HashSet<>();

    public Badge(String name, String color) {
        this.name = name;
        this.color = color;
    }

}
