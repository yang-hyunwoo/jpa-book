package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Modifying
    @Query(value="update book set category='none'" , nativeQuery = true)
    void update();

    List<Book> findByCategoryIsNull();

    List<Book> findAllByDeletedFalse();

    List<Book> findByCategoryIsNullAndDeletedFalse();

    List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(String name, LocalDateTime createdAt , LocalDateTime updatedAt);

    @Query(value ="select b from Book b "
            + "where name =:name and " +
            "createdAt >= :createdAt and " +
            "updatedAt >= :updatedAt and " +
            "category is null")
    List<Book> findByNameRecently(
            @Param("name") String name ,
            @Param("createdAt") LocalDateTime createdAt ,
            @Param("updatedAt") LocalDateTime updatedAt);

    @Query(value = "select new com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategory(b.name, b.category) from Book b")
    List<BookNameAndCategory> findBookNameAndCategory();

    @Query(value = "select new com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategory(b.name, b.category) from Book b")
    Page<BookNameAndCategory> findBookNameAndCategory(Pageable pageable);

//    @Query(value = "select new com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategory(b.name, b.category) from Book b")
//    List<BookNameAndCategory> findBookNameAndCategory();
    @Query(value ="select * from book" , nativeQuery = true)
    List<Book> findAllCustom();


    // nativeQuery 실행시 transactional 을 선언 해야 된다 아닐때는 알아서 save,update가 transactional을 해준다.
    @Modifying
    @Transactional
    @Query(value ="update book set category ='IT전문서'", nativeQuery = true)
    int updatedCategories();

    @Query(value ="show tables" , nativeQuery = true)
    List<String> showTables();

    @Query(value = "select * from book order by id desc limit 1" , nativeQuery = true)
    Map<String , Object> findRawRecord();
}
