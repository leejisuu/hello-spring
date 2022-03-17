package hello.hellospring.domain;


import javax.persistence.*;

// ORM
// @Entity 태그를 달면 JPA에서 관리하는 엔티티 설정
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)// PK 설정. identity 전략 => DB가 알아서 시퀀스 생성
    private Long id; // 시스템이 저장하는 아이디

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
