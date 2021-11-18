package com.example.jpa;

import com.example.jpa.domain.Article;
import com.example.jpa.domain.ArticleRepository;
import com.example.jpa.domain.User;
import com.example.jpa.domain.UserRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FetchEagerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("Egar type은 User를 단일 조회할 때 join문이 날아간다.")
    void userSingleFindTest() {
        System.out.println("== start ==");
        User user = userRepository.findByName("username1")
            .orElseThrow(RuntimeException::new);
        System.out.println("== end ==");
        System.out.println(user.name());
    }

    @Test
    @DisplayName("Eager type은 User를 전체 검색할 때 N+1문제가 발생한다.")
    void userFindTest() {
        System.out.println("== start ==");
        List<User> users = userRepository.findAll();
        System.out.println("== find all ==");
    }

    @Test
    @DisplayName("Eager type은 Article을 findById 검색할 때 join문이 날아간다.")
    void articleFindByIdTest() {
        System.out.println("== start ==");
        Article article = articleRepository.findByTitle("title1_1")
            .orElseThrow(RuntimeException::new);
        System.out.println("== end ==");
        System.out.println(article.content());
    }

    @Test
    @DisplayName("Eager type은 Article을 검색할 때 N+1문제가 발생한다.")
    void articleFindTest() {
        System.out.println("== start ==");
        List<Article> articles = articleRepository.findAll();
        System.out.println("== find all ==");
    }

}
