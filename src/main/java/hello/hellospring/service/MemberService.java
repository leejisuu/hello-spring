package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// bean 등록을 해주지 않으면 컨테이너에서 인식 못함
// @Service

@Transactional
public class MemberService { // ctrl + shitf + T -> Test 생성 가능

    private final MemberRepository memberRepository;

    // MemberService가 생성 될 때 스프링 Bean에 등록되어 있는 MemoryMemberRepository 객체(구현체)를 주입해줌 => DI
    // @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    *  회원 가입
    * */
    public Long join(Member member) { // jpa는 모든 join은 트랜잭션 안에서 수행되어야 함

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();

        // 시간을 찍던 로직을 AOP로 따로 정의
        /*long start = System.currentTimeMillis();

        try {
            // 같은 이름이 있는 중복 회원 X
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }*/
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    * 전체 회원 조회
   * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
