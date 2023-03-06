package kh.spring.s01.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.s01.member.model.service.MemberService;
import kh.spring.s01.member.model.vo.MemberVo;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	// 회원 가입 화면 
	@GetMapping("/signUp")
	public ModelAndView viewInsert(ModelAndView mv) {
		mv.setViewName("member/signUp");
		return mv;
	}
	
	// 회원 가입 
	@PostMapping("/signUp")
	public ModelAndView insert(ModelAndView mv, MemberVo vo, String bbb, String id) {
		int result = service.insert(vo);
		
		if(result > 0) {
			mv.setViewName("redirect:/");
		} else {
			mv.setViewName("redirect:/member/signUp");
		}
		
		return mv;
	}
	
	// 회원 수정 페이지 - 관리자 
	@GetMapping("/update")
	public ModelAndView viewUpdate(ModelAndView mv, String id) {
		MemberVo vo = service.selectOne(id); 
		mv.setViewName("/member/signUp");
		return mv;
	}
	
	// 회원 수정 - 관리자 
	@PostMapping("/update")
	public ModelAndView update(ModelAndView mv, MemberVo vo) {
		service.update(vo);
		return mv;
	}
	
	// 회원 정보 삭제 
	@GetMapping("/delete")
	public ModelAndView delete(ModelAndView mv, String id) {
		service.delete(id);
		return mv;
	}
	
	// 정보 보기 - 회원 
	@GetMapping("/info")
	public ModelAndView selectOne(ModelAndView mv, String id) {
		service.selectOne(id);
		return mv;
	}
	
	// 회원 list 
	@GetMapping("/list")
	public ModelAndView selectList(ModelAndView mv) {
		List<MemberVo> result = service.selectList();
		return mv;
	}
	
	

}
