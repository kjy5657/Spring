package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //Static
    @GetMapping("hello")
    public String hello(Model model) {
        //hello.html파일에서 data변수에 hello라는 값을 넣어줌
        model.addAttribute("data", "hello");
        //template/hello 파일 찾아서 rendering 하라는 의미
        return "hello";
    }

    //MVC
    @GetMapping("hello-mvc")
    //@RequestParam : Web에서 param을 받음
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    //API
    //HttpMessageConverter 동작
    @GetMapping("hello-string")
    //HTTP에서 Header와 Body부분에서 Body를 직접 넣어주겠다는 뜻
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        //View 같은 게 없고, 그대로 이 문자가 데이터로 내려감
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        //return JSON(default)
        return hello;
    }

    static class Hello {
        private String name;

        //getter
        public String getName() {
            return name;
        }

        //setter
        public void setName(String name) {
            this.name = name;
        }
    }
}
