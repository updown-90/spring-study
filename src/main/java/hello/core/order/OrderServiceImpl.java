package hello.core.order;

import hello.core.disconunt.DiscountPolicy;
import hello.core.disconunt.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 고정할인금액와 비율할인정책을 변경하려면 아래 구현객체만 변경하면된다
    // 아래 둘 다 OCP, DIP 위반
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 이런식으로 DiscountPolicy 인터페이스는 본인이 어떤 구현체를 사용할지 까지는 안해야 관심사의 분리가 된다 OCP, DIP
//    private DiscountPolicy discountPolicy; // OCP 통과

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(member.getId(), itemName, itemPrice, discountPrice);

    }

}
