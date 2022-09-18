package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.HwUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<HwUser,Long> {

    Set<HwUser> findByName(String name);

    Set<HwUser> findUserByNameIs(String name);
    Set<HwUser> findUserByName(String name);
    Set <HwUser> findUserByNameEquals(String name);

    HwUser findByEmail(String email);

    HwUser getByEmail(String email);

    HwUser readByEmail(String email);

    HwUser queryByEmail(String email);

    HwUser searchByEmail(String email);

    HwUser streamByEmail(String email);

    HwUser findUserByEmail(String email);

    HwUser findSomethingByEmail(String email);

    List<HwUser> findFirst2ByName(String name);

    List<HwUser> findTop2ByName(String name);

    List<HwUser> findLast1ByName(String name);

    List<HwUser> findByEmailAndName(String email , String name);

    List<HwUser> findByEmailOrName(String email , String name);

    List<HwUser> findByNameOrName(String name,String name2);

    List<HwUser> findByCreatedAtAfter(LocalDateTime yesterday);

    List<HwUser> findByIdAfter(Long id);

    List<HwUser> findByCreatedAtGreaterThan(LocalDateTime yesterday);

    List<HwUser> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);

    List<HwUser> findByCreatedAtBetween(LocalDateTime yesterday , LocalDateTime tomorrow);
    List<HwUser> findByIdBetween(Long id1 , Long id2);

    List<HwUser> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1 , Long id2);
    List<HwUser> findByIdIsNotNull();

//    List<HwUser> findByAddressIsNotEmpty();

    List<HwUser> findByNameIn(List<String> names);

    List<HwUser> findByNameStartingWith(String name);

    List<HwUser> findByNameEndingWith(String name);

    List<HwUser> findByNameContains(String name);

    List<HwUser> findByNameLike(String name);

//    List<HwUser> findByName(String name);

    List<HwUser> findTop1ByName(String name);

    List<HwUser> findTopByNameOrderByIdDesc(String name);


    List<HwUser> findFirstByName(String name , Sort sort);

    Page<HwUser> findByName(String name , Pageable pageable);

    @Query(value = "select * from hw_user limit 1;",nativeQuery = true)
    Map<String,Object> findRawRecord();

    @Query(value = "select * from hw_user" , nativeQuery = true)
    List<Map<String ,Object>> findAllRawRecord();

}
