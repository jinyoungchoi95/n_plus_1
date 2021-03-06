package com.example.jpa.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    @Query("select distinct u from User u left join u.articles")
    List<User> findAllJPQL();

    @Query("select distinct u from User u left join fetch u.articles")
    List<User> findAllJPQLFetch();

    @EntityGraph(attributePaths = {"articles"}, type = EntityGraphType.FETCH)
    @Query("select distinct u from User u left join u.articles")
    List<User> findAllEntityGraph();

    @EntityGraph(attributePaths = {"articles"}, type = EntityGraphType.FETCH)
    @Query("select distinct u from User u left join u.articles")
    Page<User> findAllPage(Pageable pageable);

    Page<User> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"articles", "questions"}, type = EntityGraphType.FETCH)
    @Query("select distinct u from User u left join u.articles left join u.questions")
    List<User> findAllEntityGraph2();

    @Query("select distinct u from User u left join u.articles left join u.questions")
    Page<User> findAllPage2(Pageable pageable);

}
