package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Address;
import com.fastcampus.jpa.bookmanager.domain.Gender;
import com.fastcampus.jpa.bookmanager.domain.HwUser;
import com.fastcampus.jpa.bookmanager.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRespository userHistoryRespository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void crud() { // create , read , update , delete
//        List<HwUser> users = userRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));

//        List<HwUser> users = userRepository.findAllById(Lists.newArrayList(1L,3L,5L));

//          HwUser user1 = new HwUser("jack","jack@fastcampus.com");
//          HwUser user2 = new HwUser("steve","jack@fastcampus.com");
//          userRepository.saveAll(Lists.newArrayList(user1,user2));

     /*    페이징
        Page<HwUser> users = userRepository.findAll(PageRequest.of(1,3));
        System.out.println("page : "+ users);
        System.out.println("totalElements : " + users.getTotalElements());
        System.out.println("totalPages : " + users.getTotalPages());
        System.out.println("numberOfElements : " + users.getNumberOfElements());
        System.out.println("sort : " + users.getSort());
        System.out.println("size : " + users.getSize());
        users.getContent().forEach(System.out::println);
      */
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("name")
//                .withMatcher("email",endsWith());
//
//        Example<HwUser> example = Example.of(new HwUser("martin","martin@fastcampus.com"));
//        HwUser user = new HwUser();
//        user.setEmail("slow");
//        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email",contains());
//        Example<HwUser> example = Example.of(user,matcher);
//        userRepository.findAll(example).forEach(System.out::println);

         userRepository.save(new HwUser("david","david@fastcampus.com"));
         HwUser user =userRepository.findById(1L).orElseThrow(RuntimeException::new);
         user.setEmail("martin-update@fastcampus.com");
         userRepository.save(user);
    }

    @Test
    void select() {
        /*
        System.out.println(userRepository.findByName("dennis"));
        System.out.println("findByEmail : " + userRepository.findByEmail("martin@fastcampus.com"));
        System.out.println("getByEmail : " + userRepository.getByEmail("martin@fastcampus.com"));
        System.out.println("readByEmail : " + userRepository.readByEmail("martin@fastcampus.com"));
        System.out.println("queryByEmail : " + userRepository.queryByEmail("martin@fastcampus.com"));
        System.out.println("searchByEmail : " + userRepository.searchByEmail("martin@fastcampus.com"));
        System.out.println("streamByEmail : " + userRepository.streamByEmail("martin@fastcampus.com"));
        System.out.println("findUserByEmail : " + userRepository.findUserByEmail("martin@fastcampus.com"));
        System.out.println("findSomethingByEmail : " + userRepository.findSomethingByEmail("martin@fastcampus.com"));

        System.out.println("findFirst1ByName : " + userRepository.findFirst2ByName("martin"));
        System.out.println("findTop1ByName : " + userRepository.findTop2ByName("martin"));
        System.out.println("findLast1ByName : " + userRepository.findLast1ByName("martin"));
         */

        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("martin@fastcampus.com","martin"));
        System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("martin@fastcampus.com","dennis"));
        System.out.println("findByNameOrName : " + userRepository.findByNameOrName("martin","dennis"));
        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L));
        System.out.println("findByCreatedAtGraterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThanEqual : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtBetween : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L),LocalDateTime.now().plusDays(1L)));
        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L ,3L));
        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L ,3L));
        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());
//        System.out.println("findByIdIsNotEmpty : " + userRepository.findByAddressIsNotEmpty());
        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("martin","dennis")));
        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("mar"));
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("tin"));
        System.out.println("findByNameContains : " + userRepository.findByNameContains("art"));
        System.out.println("findByNameLike : " + userRepository.findByNameLike("%art%"));

    }

    @Test
    void pagingAndSortingTest() {
        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
        System.out.println("findTopByNameOrderByIdDesc : " + userRepository.findTopByNameOrderByIdDesc("martin"));
        System.out.println("findFirstByName : " + userRepository.findFirstByName("martin",Sort.by(Sort.Order.desc("id"), Sort.Order.desc("email"))));
        System.out.println("findByNameWithPaging :"  + userRepository.findByName("martin",PageRequest.of(1,1,Sort.by(Sort.Order.desc("id")))).getTotalElements());


    }
    @Test
    void insertAndUpdateTest() {
        HwUser user = new HwUser();

        user.setEmail("martin");
        user.setEmail("martin2@fastcampus.com");
        userRepository.save(user);

        HwUser user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrrrtin");
        userRepository.save(user2);

    }

    @Test
    void enumTest(){
        HwUser user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRawRecord().get("gender"));
    }

    @Test
    void listenerTest() {
        HwUser user = new HwUser();
        user.setEmail("martin2@fastcampus.com");
        user.setName("martin");
        userRepository.save(user);

        HwUser user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrtin");
        userRepository.save(user2);

        userRepository.deleteById(4L);
    }

    @Test
    void prePersistTest() {
        HwUser user = new HwUser();
        user.setEmail("martin2@fastcampus.com");
        user.setName("martin");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        System.out.println(userRepository.findByEmail("martin2@fastcampus.com"));
    }
    @Test
    void preUpdateTest() {
        HwUser user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println("as-is: "+user);
        user.setName("martin22");
        userRepository.save(user);
        System.out.println("to-be :" + userRepository.findAll().get(0));
    }

    @Test
    void userHistoryTest() {
        HwUser user = new HwUser();
        user.setEmail("martin-new@fastcampus.com");
        user.setName("martin-new");
        userRepository.save(user);
        user.setName("martin-new-new");
        userRepository.save(user);
        userHistoryRespository.findAll().forEach(System.out::println);
    }

    @Test
    void userRelationTest() {
        HwUser user = new HwUser();
        user.setName("david");
        user.setEmail("david@fastcampus.com");
        user.setGender(Gender.MALE);
        userRepository.save(user);
        user.setName("daniel");
        userRepository.save(user);
        user.setEmail("daniel@fastcampus.com");
        userRepository.save(user);

        userHistoryRespository.findAll().forEach(System.out::println);
//        List<UserHistory> result = userHistoryRespository
//                .findByUserId(userRepository.findByEmail("daniel@fastcampus.com").getId());

        List<UserHistory> result = userRepository.findByEmail("daniel@fastcampus.com").getUserHistories();
        result.forEach(System.out::println);
        System.out.println("UserHistory.getUser() : " + userHistoryRespository.findAll().get(0));
    }


    @Test
    void embedTest(){
        userRepository.findAll().forEach(System.out::println);
        var user = new HwUser();
        user.setName("스티브");
        user.setHomeAddress(new Address("서울시","강남구","강남대로 364 미왕빌딩","06241"));
        user.setCompanyAddress(new Address("서울시","성동구","성수이로 113 제강빌딩","04794"));
        userRepository.save(user);

        var user1 = new HwUser();
        user1.setName("joshua");
        user1.setHomeAddress(null);
        user1.setCompanyAddress(null);
        userRepository.save(user1);

        var user2 = new HwUser();
        user2.setName("jordan");
        user2.setHomeAddress(new Address());
        user2.setCompanyAddress(new Address());
        userRepository.save(user2);
//        entityManager.clear();
        userRepository.findAll().forEach(System.out::println);
        userHistoryRespository.findAll().forEach(System.out::println);

        userRepository.findAllRawRecord().forEach(a -> System.out.println(a.values()));
        assertAll(
                ()-> assertThat(userRepository.findById(7L).get().getHomeAddress()).isNull(),
            ()-> assertThat(userRepository.findById(8L).get().getHomeAddress()).isInstanceOf(Address.class)

                );

    }

    private Sort getSort(){
        return Sort.by(
                Sort.Order.desc("id"),
                Sort.Order.asc("email")
        );
    }

}