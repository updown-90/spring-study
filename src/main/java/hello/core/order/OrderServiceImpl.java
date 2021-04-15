package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.disconunt.DiscountPolicy;
import hello.core.disconunt.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    @RequiredArgsConstructor 를 사용했으므로 자동으로 만들어주기때문에 필요없어짐
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    // 변경 전
    // 고정할인금액와 비율할인정책을 변경하려면 아래 구현객체만 변경하면된다
    // 아래 둘 다 OCP, DIP 위반
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 이런식으로 DiscountPolicy 인터페이스는 본인이 어떤 구현체를 사용할지 까지는 안해야 관심사의 분리가 된다 OCP, DIP

    // 변경 후
//   private DiscountPolicy discountPolicy; // OCP 통과

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(member.getId(), itemName, itemPrice, discountPrice);

    }

}
