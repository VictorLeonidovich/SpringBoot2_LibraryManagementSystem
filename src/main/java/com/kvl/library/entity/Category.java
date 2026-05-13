package com.kvl.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
@ToString(onlyExplicitlyIncluded = true)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long id;
    @Column(name = "name", length = 50, nullable = false, unique = true)
    @ToString.Include
    private String name;
    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();

    public Category(String name) {
        this.name = name;
    }
}
