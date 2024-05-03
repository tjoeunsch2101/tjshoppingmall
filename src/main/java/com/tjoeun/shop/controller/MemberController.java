package com.tjoeun.shop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.shop.dto.MemberFormDto;
import com.tjoeun.shop.entity.Member;
import com.tjoeun.shop.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping("/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){

    	  log.info(">>>>>>>>>>>>>>>> memberFormDto : " + memberFormDto);
    	
    	  if(bindingResult.hasErrors()){
            return "member/memberForm";
        }

    		try {
					// DTO --> Entity
					// 화면에서 받아온 회원가입 정보를 Member Entity 에 저장함
          Member member = Member.createMember(memberFormDto, passwordEncoder);
          log.info(">>>>>>>>>>>>>>>> member : " + member);
          Member savedMember = memberService.saveMember(member);
          log.info(">>>>>>>>>>>>>>>> savedMember : " + savedMember);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String loginMember(){
    	log.info(">>>>>>>>>>>>>>>> login");
        return "/member/memberLoginForm";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }
    
  	@GetMapping("/logout")
  	public String signout(HttpServletRequest request, HttpServletResponse response) {
  		log.info(">>>>>>>>>>>>>>>> logout");
  		
  		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  		// session 정보 삭제하기
  		if(authentication != null) {
  			new SecurityContextLogoutHandler().logout(request, response, authentication);
  		}
  		
  		return "redirect:/";
  	}
    
  	@GetMapping("login/accessDenied")
    public String accessDenied(Model model) {
    		
    		model.addAttribute("loginErrorMsg", "관리자로 로그인해 주세요");
    		
    		return "member/memberLoginForm";
    	}

}