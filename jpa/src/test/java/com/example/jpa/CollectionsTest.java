package com.example.jpa;

import com.example.jpa.domain.User;
import com.example.jpa.domain.UserRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
public class CollectionsTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("collection join 2개일 때 fetch join")
    void collectionFetchJoinTest() {
        System.out.println("== start ==");
        List<User> users = userRepository.findAllEntityGraph2();
        System.out.println("== find all ==");
        for (User user : users) {
            user.articles()
                .forEach(article -> System.out.println(article.title()));
        }
    }

    @Test
    @DisplayName("collection join 2개일 때 fetch join Page")
    void pagingBatchSizeTest() {
        System.out.println("== start ==");
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<User> users = userRepository.findAllPage2(pageRequest);
        System.out.println("== find all ==");
        for (User user : users) {
            user.articles()
                .forEach(article -> System.out.println(article.title()));
        }
    }

}
