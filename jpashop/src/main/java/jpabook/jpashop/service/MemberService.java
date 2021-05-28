package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository repository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member);
        repository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = repository.findByName(member.getName());

        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
//    @Transactional(readOnly = true) // 이렇게하면 읽기 전용이라 성능 좋음 . 조회에는 넣어주는게 좋다.
    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Member findOne(Long memberId) {
        return repository.findOne(memberId);
    }


}
