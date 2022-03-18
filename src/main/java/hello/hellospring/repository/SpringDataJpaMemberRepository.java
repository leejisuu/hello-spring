package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 스프링 Data JPA가 JpaRepository를 상속받고 있는 인터페이스를 자동으로 bean으로 등록해줌

    // JPQL select m from Member m where m.name = ?
   @Override
    Optional<Member> findByName(String name);

   // JpaRepository가 findByName, findAll 등 기본적인 CRUD  메소드들을 기본으로 가지고 있음
}
