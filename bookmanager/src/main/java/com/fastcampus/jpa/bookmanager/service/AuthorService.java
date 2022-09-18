package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Author;
import com.fastcampus.jpa.bookmanager.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

//    @Transactional(propagation = Propagation.REQUIRES_NEW) 독립 트랜잭션
    //@Transactional(propagation = Propagation.REQUIRED) 재활용 같은 트랜잭션을 탄다?.
//@Transactional(propagation = Propagation.NESTED) 하나의 트랜잭션이지만 분리된다
    @Transactional(propagation = Propagation.NESTED)
    public void putAuthor() {
        var author = new Author();
        author.setName("martin");
        authorRepository.save(author);
//        throw new RuntimeException("오류가 발생하였습니다. transaction은 어덯게 될까요");
    }
}
