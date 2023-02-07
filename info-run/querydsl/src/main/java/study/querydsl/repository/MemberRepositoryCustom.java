package study.querydsl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.querydsl.dto.MemberSerachCondition;
import study.querydsl.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> search(MemberSerachCondition condition);
    Page<MemberTeamDto> searchPageSimple(MemberSerachCondition condition , Pageable pageable);
    Page<MemberTeamDto> searchPageComplex(MemberSerachCondition condition, Pageable pageable);
}
