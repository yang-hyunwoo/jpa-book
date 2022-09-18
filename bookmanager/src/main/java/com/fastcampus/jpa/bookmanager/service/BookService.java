package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Author;
import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.repository.AuthorRepository;
import com.fastcampus.jpa.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final EntityManager entityManager;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;
    @Transactional
    public void putBookAndAuthor(){
        var book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);
        try {
            authorService.putAuthor();
        }  catch (RuntimeException e){

        }


//        var author = new Author();
//        author.setName("martin");
//
//        authorRepository.save(author);
        throw new RuntimeException("오류가 발생하였습니다. transaction은 어덯게 될까요");
    }
    // 두개를 많이 씀 격리 영역
//    @Transactional(isolation = Isolation.REPEATABLE_READ)
//    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void get(Long id) {
        System.out.println("1>>>  " + bookRepository.findById(id));
        System.out.println("2>>>  " + bookRepository.findAll());
        entityManager.clear();
        bookRepository.update();
        System.out.println("3>>>  " + bookRepository.findById(id));
        System.out.println("4>>>  " + bookRepository.findAll());
        entityManager.clear();
        var book = bookRepository.findById(id).get();
        book.setName("바뀔까?");
        bookRepository.save(book);
    }

    @Transactional
    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);

        return books;
    }
}
