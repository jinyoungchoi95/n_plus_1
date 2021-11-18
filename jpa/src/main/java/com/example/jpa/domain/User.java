package com.example.jpa.domain;

import static java.util.Collections.emptySet;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Article> articles = emptySet();

    protected User() {
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Set<Article> articles() {
        return articles;
    }

}
