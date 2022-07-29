package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

  // 테스팅 시에, 이렇게 생성자로 하지 않으면, 다른 repository로 testing 해야 하는 불편함 있음
  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Long join(Member member) {

    // null일 가능성이 있으면 Optional로 감싸면, Optional 안에 member 객체가 있는 것
    // findByName method returns Optional
    validateDuplicateMember(member);

    // 바로 꺼내는 방법, 바로 꺼내는 것을 권장하지는 않음
    // Member member1 = result.get();

    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    memberRepository
        .findByName(member.getName())
        .ifPresent(
            m -> {
              throw new IllegalStateException("이미 존재하는 회원입니다");
            });
  }

  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
