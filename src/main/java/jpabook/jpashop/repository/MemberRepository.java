package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // @PersistenceContext : 스프링이 엔티티 매니저를 만들어 주입시킴 -> 이거 대신 @RequiredArgsConstructor 쓰면 코드 줄어듬
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member); // 영속성
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // jpql은 sql과 유사, 엔티티 대상임
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
