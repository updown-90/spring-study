package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    // 순수한 자바 코드로만 개발 해보기
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        // 변경 후
        MemberService memberService = appConfig.memberService();
        // 변경 전
//        MemberServiceImpl memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.joinMember(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " +member.getName());
        System.out.println("find Member = " +findMember.getName());
    }
}
