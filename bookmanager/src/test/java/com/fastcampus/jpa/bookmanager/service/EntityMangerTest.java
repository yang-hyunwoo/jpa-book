package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@Transactional
public class EntityMangerTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    void entityMangerTest() {
        System.out.println(entityManager.createQuery("select u from HwUser u").getResultList());
    }

    //entity의 id 값을 조회 하면 entity 1차 캐시에 담긴다.
    @Test
    void cacheFindTest() {
//        System.out.println(userRepository.findByEmail("martin@fastcampus.com"));
//        System.out.println(userRepository.findByEmail("martin@fastcampus.com"));
//        System.out.println(userRepository.findByEmail("martin@fastcampus.com"));
//        System.out.println(userRepository.findById(2L).get());
//        System.out.println(userRepository.findById(2L).get());
//        System.out.println(userRepository.findById(2L).get());

        userRepository.deleteById(1L);
    }

    @Test
    void cacheFindTest2() {
        var user =userRepository.findById(1L).get();
        user.setName("marrrrrrtin");
        user.setId(1L);
//        var savedPost = userRepository.save(user); // persist
        userRepository.save(user);
//        userRepository.flush();
        System.out.println("------------------------------");
        user.setEmail("marrrrrrrtin@fastcampus.com");
//        userRepository.save(user);
//        userRepository.flush();
        System.out.println(">>> 1 : " + userRepository.findById(1L).get());
        userRepository.flush();
        System.out.println(">>> 2 : " + userRepository.findById(1L).get());

    }
}
