package study.querydsl.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberSerachCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaReposiotryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaReposiotry;

    @Test
    void basicTest() {
        Member member = new Member("member1", 10);
        memberJpaReposiotry.save(member);

        Member findMember = memberJpaReposiotry.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> result1 = memberJpaReposiotry.findAll();
        assertThat(result1).containsExactly(member);
        List<Member> result3 = memberJpaReposiotry.findAll_Querydsl();
        assertThat(result3).containsExactly(member);

        List<Member> result2 = memberJpaReposiotry.findByUsername("member1");
        assertThat(result2).containsExactly(member);

    }

    @Test
    void searchTest() {

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSerachCondition condition = new MemberSerachCondition();
        condition.setAgeGoe(20);
        condition.setAgeLoe(40);
        condition.setTeamName("teamB");
        List<MemberTeamDto> result = memberJpaReposiotry.searchByBuilder(condition);
        assertThat(result).extracting("username").containsExactly("member4");
    }




}