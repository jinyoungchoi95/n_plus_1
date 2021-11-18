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
public class FetchLazyTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("Lazy type은 User 검색 후 필드 검색을 할 때 N+1문제가 발생한다.")
    void userFindTest() {
        System.out.println("== start ==");
        List<User> users = userRepository.findAll();
        System.out.println("== find all ==");
        for (User user : users) {
            System.out.println(user.articles().size());
        }
    }

}
