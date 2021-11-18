package com.example.jpa;

import com.example.jpa.domain.ArticleRepository;
import com.example.jpa.domain.User;
import com.example.jpa.domain.UserRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FetchJoinTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("일반 jpql 쿼리문은 N+1문제가 발생한다.")
    void normalJpqlTest() {
        System.out.println("== start ==");
        List<User> users = userRepository.findAllJPQL();
        System.out.println("== find all ==");
        for (User user : users) {
            System.out.println(user.articles().size());
        }
    }

    @Test
    @DisplayName("fetch join을 하면 N+1문제가 발생하지 않는다.")
    void fetchJoinTest() {
        System.out.println("== start ==");
        List<User> users = userRepository.findAllJPQLFetch();
        System.out.println("== find all ==");
        for (User user : users) {
            System.out.println(user.articles().size());
        }
    }

    @Test
    @DisplayName("@EntityGraph을 하면 N+1문제가 발생하지 않는다.")
    void entityGraphpTest() {
        System.out.println("== start ==");
        List<User> users = userRepository.findAllEntityGraph();
        System.out.println("== find all ==");
        for (User user : users) {
            System.out.println(user.articles().size());
        }
    }

}
