package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    // 스프링 컨테이너가 @Controller 어노테이션을 보고 객체로 생성해 스프링 컨테이너가 관리해줌

    private final MemberService memberService;

    // 스프링 컨터이너에 MemberService를 하나만 등록하고 공용으로 사용
    // MemberController가 생성 될 때 스프링 Bean에 등록되어 있는 MemberService 객체를 주입해줌 => DI
    // 1. 생성자 주입 방식
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 2. 필드 주입 방식
    // @Autowired private MemberService memberService;

    // 3. setter 주입
    // @Autowired
    // public void setMemberService(MemberService memberService) {
    //     this.memberService = memberService;
    // }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }


}

