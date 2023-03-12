package kh.spring.s01.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.spring.s01.member.model.service.MemberService;
import kh.spring.s01.member.model.vo.MemberVo;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	// 회원 가입 화면 
	@GetMapping("/signUp")
	public ModelAndView viewInsert(ModelAndView mv) throws Exception {
		mv.setViewName("member/signUp");
		return mv;
	}
	
	// 회원 가입 
	@PostMapping("/signUp")
	public ModelAndView insert(ModelAndView mv
			, MemberVo vo
			, String bbb
			, String id
			, RedirectAttributes rttr)throws Exception {
		int result = service.insert(vo);
		
		if(result > 0) {
			rttr.addFlashAttribute("msg", "회원가입 성공 방법 rttr");
			mv.setViewName("redirect:/");
		} else {
			rttr.addFlashAttribute("msg", "회원가입 실패 방법 rttr");
			mv.setViewName("redirect:/member/signUp");
		}
		
		return mv;
	}
	
	// 회원 수정 
	@GetMapping("/update")
	public ModelAndView viewUpdate(ModelAndView mv
			, @RequestParam("id") String id
			) throws Exception{
		MemberVo vo = service.selectOne(id); 
		mv.addObject("membervo", vo);
		mv.setViewName("/member/update");
		return mv;
	}
	
	// 회원 수정 
	@PostMapping("/update")
	public ModelAndView update(ModelAndView mv, MemberVo vo) throws Exception{
		service.update(vo);
		
		return mv;
	}
	
	// 회원 정보 삭제 
	@GetMapping("/delete")
	public ModelAndView delete(ModelAndView mv, String id) throws Exception{
		service.delete(id);
		return mv;
	}
	
	// 정보 보기 - 회원 
	@GetMapping("/info")
	public ModelAndView selectOne(ModelAndView mv, String id) throws Exception{
		
		if(id ==  null) {
			mv.setViewName("redirect:/");
		}
		MemberVo result = service.selectOne(id);

		mv.addObject("membervo", result);
		mv.setViewName("member/info");
		
		return mv;
	}
	
	// 회원 list 
	@GetMapping("/list")
	public ModelAndView selectList(ModelAndView mv) throws Exception {
		List<MemberVo> result = service.selectList();
		mv.addObject("membervolist", result);
		return mv;
	}
	
	@ExceptionHandler
	public ModelAndView memberExceptionHandler(Exception e
			//, ModelAndView mv 에러 발생 
			) {
		
		e.printStackTrace();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()+ " 오류가 발생했습니다. 다시 시도해주세요.");
		mv.setViewName("error/error500");
		return mv;
		
		
	}
	
	

}
