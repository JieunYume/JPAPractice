package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 롤백 해줌 -> db에 데이터가 남으면 안되니까
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("jieun");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test(expected = IllegalStateException.class) // try-catch문 대신 사용하면 코드가 깔끔해짐
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("yunju");

        Member member2 = new Member();
        member2.setName("yunju");


        //when
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야함

        //then
        fail("예외가 발생해야 한다.");
    }
}