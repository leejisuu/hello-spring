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
        em.persist(member); // jqpl에서는 insert 대신 persist 사용
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class) // :name에서 :은 이름 기준으로 데이터를 바인딩
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // JPQL => 객체를 대상으로 sql 구문 수행(Member는 테이블이 아닌 객체)
                .getResultList();
    }
    // Ctrl + Alt + N 인라인 단축키
}
