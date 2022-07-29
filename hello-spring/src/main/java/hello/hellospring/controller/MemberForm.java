package hello.hellospring.controller;

public class MemberForm {

  // input 태그의 name property 값을 보고 Spring이 name에 넣어줌
  private String name;

  public String getName() {
    return name;
  }

  // Spring이 setName 호출
  public void setName(String name) {
    this.name = name;
  }
}
