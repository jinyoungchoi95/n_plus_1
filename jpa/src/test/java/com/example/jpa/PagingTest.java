package com.example.jpa;

import com.example.jpa.domain.Article;
import com.example.jpa.domain.ArticleRepository;
import com.example.jpa.domain.User;
import com.example.jpa.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
public class PagingTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("fetch join을 paging처리에서 사용해도 N+1문제가 발생한다.")
    void pagingFetchJoinTest() {
        System.out.println("== start ==");
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<User> users = userRepository.findAllPage(pageRequest);
        System.out.println("== find all ==");
        for (User user : users) {
            System.out.println(user.articles().size());
        }
    }

    @Test
    @DisplayName("Article로 User pagination 시 N+1문제가 발생하지 않는다.")
    void pagingJoinByArticleTest() {
        System.out.println("== start ==");
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<Article> articles = articleRepository.findAllPage(pageRequest);
        System.out.println("== find all ==");
        for (Article article : articles) {
            System.out.println(article.user().name());
        }
    }

    @Test
    void pagingBatchSizeTest() {
        System.out.println("== start ==");
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<User> users = userRepository.findAll(pageRequest);
        System.out.println("== find all ==");
        for (User user : users) {
            user.articles()
            .forEach(article -> System.out.println(article.title()));
        }
    }

}
