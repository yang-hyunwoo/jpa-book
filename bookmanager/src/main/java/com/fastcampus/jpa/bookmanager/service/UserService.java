package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.HwUser;
import com.fastcampus.jpa.bookmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class UserService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
    @Transactional
    public void put(){
        var user = new HwUser();
        user.setName("newUser");
        user.setEmail("newUser@fastcampus.com");
        entityManager.persist(user);
//        entityManager.detach(user);
        user.setName("newUserAfterPersist");
        entityManager.merge(user);
//        entityManager.flush();
//        entityManager.clear();
//        userRepository.save(user);
        var user1 = userRepository.findById(1L).get();
        entityManager.remove(user1);
        user1.setName("marrrrrrrtin");
        entityManager.merge(user1);



    }



}
