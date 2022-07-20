package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

  MemoryMemberRepository memoryMemberRepository;
  MemberService memberService;

  // test는 독립적으로 실행해야 하므로, BeforeEach로 실행
  @BeforeEach
  public void beforeEach() {
    memoryMemberRepository = new MemoryMemberRepository();
    // memberService 입장에서, 직접 Repository를 만들지 않음 => DI(Dependency Injection)
    memberService = new MemberService(memoryMemberRepository);
  }

  @AfterEach
  public void afterEach() {
    memoryMemberRepository.clearStore();
  }

  @Test
  void 회원가입() {
    // given
    Member member = new Member();
    member.setName("hello");

    // when
    Long saveId = memberService.join(member);

    // then
    Member findMember = memberService.findOne(saveId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  void 중복_회원_예외() {
    // given
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    // when
    memberService.join(member1);

    // 두번째 인자를 실행할 때, 첫 번째 인자의 예외가 터져야 함
    IllegalStateException e =
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

    //    try {
    //      memberService.join(member2);
    //      fail();
    //    } catch (IllegalStateException e) {
    //      assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    //    }

    // then
  }

  @Test
  void findMembers() {}

  @Test
  void findOne() {}
}
