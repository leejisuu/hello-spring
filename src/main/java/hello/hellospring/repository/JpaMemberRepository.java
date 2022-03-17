package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // 내부적으로 데이터 소스 들고있음
    private final EntityManager em;

    // EntityManager를 쓰려면 JPA를 주입 받아야함
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return null;
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // jpql => 객체를 대상으로 sql 구문 수행
                .getResultList();
    }
    // Ctrl + Alt + N 인라인 단축키
}
