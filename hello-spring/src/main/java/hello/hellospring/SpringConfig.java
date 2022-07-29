package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
  @Bean
  public MemberService memberService() {
    // Spring Bean에 등록된 것을 넣어줌
    return new MemberService(memberRepository());
  }

  @Bean()
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
}
