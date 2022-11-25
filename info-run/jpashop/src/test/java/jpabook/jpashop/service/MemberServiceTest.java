package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;


    @Test
    @DisplayName("회원가입")
    public void join() throws Exception {

        // Given
        Member member = new Member();
        member.setName("kim");

        // When
        Long savedId = memberService.join(member);

//        em.flush();   insert문이 안보인다 spring이 롤백을 하기 때문 그래서 insert문을 확인 하기 위해서 영속성을 flush로 해주면 된다.

        // Then
        assertEquals(member,memberRepository.findOne(savedId));

    }

    @Test
    @DisplayName("종복_회원_예외")
    public void valid_dup_join_exception() throws Exception{

        // Given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // When
        memberService.join(member1);
        try{
            memberService.join(member2);

        } catch (IllegalStateException e) {
            return ;
        }

        // Then
        fail("예외가 발생해야 한다.");

    }

}