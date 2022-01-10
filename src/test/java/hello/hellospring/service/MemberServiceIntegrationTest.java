package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring1");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());


    }

    @Test
    public  void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        // when
        Member member2 = new Member();
        member2.setName("spring");

        // then
        memberService.join(member1);

        //assertThrows는 2번째 인자에 해당하는 로직이 실행되다가 예외가 발생하면 1번째 인자와 같은 타입인지 확인한다.
        // assertThrows는 예외 객체를 반환한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // 반환된 IllegalStateException 객체를 이용해 메시지 동일성 검증도 가능하다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

    }


}