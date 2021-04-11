package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @ 어노테이션 붙은애들 찾아서 스프링 빈으로 등록해줌
@ComponentScan(
        basePackages = "hello.core",
        // AppConfig랑 충돌나지않게 제외설정
        // 기존예제코드들 살리기위해 ( @Configuration 제외)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)

)
public class AutoAppConfig {

}
