package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    // 변경 전
//        MemberService memberService = new MemberServiceImpl();
        MemberService memberService;

// 변경 후
        @BeforeEach
        public void beforEach(){
            AppConfig appConfig = new AppConfig();
            memberService = appConfig.memberService();
        }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.joinMember(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
