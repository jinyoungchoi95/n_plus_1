package com.example.jpa.domain;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByTitle(String title);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraphType.FETCH)
    @Query("select a from Article a left join a.user")
    Page<Article> findAllPage(Pageable pageable);

}
