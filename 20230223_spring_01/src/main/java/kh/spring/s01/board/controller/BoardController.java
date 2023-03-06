package kh.spring.s01.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.s01.board.model.service.BoardService;
import kh.spring.s01.board.model.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	private final static int BOARD_LIMIT = 5; // 한 페이지에 보여줄 게시글 수 
	private final static int PAGE_LIMIT = 3;  // 한 페이지에 보여줄 게시판 페이지 수 
	
	// 게시글 전체 조회 + 페이징 TODO
	@GetMapping("/list")
	public ModelAndView viewReadBoard(ModelAndView mv) {
		
		
		// 검색 조회 (제목, 내용, 작성자에서 검색)
		String searchWord = "답";
		
		int currentPage = 2;
		int totalCnt = service.selectOneCount(searchWord);
		int totalPage = (totalCnt%BOARD_LIMIT==0)?
				(totalCnt/BOARD_LIMIT) : 
				(totalCnt/BOARD_LIMIT) + 1;
		int startPage = (currentPage%PAGE_LIMIT==0) ?
				(currentPage/PAGE_LIMIT -1)*PAGE_LIMIT + 1 :
				(currentPage/PAGE_LIMIT   )*PAGE_LIMIT + 1;
		int endPage = (startPage + PAGE_LIMIT > totalPage) ?
				totalPage : 
				(startPage + PAGE_LIMIT);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("totalPage", totalPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		mv.addObject("pageInfo", map);
		
		
//		mv.addObject("currentPage", currentPage);
//		mv.addObject("totalPage", totalPage);
//		mv.addObject("startPage", startPage);
//		mv.addObject("endPage", endPage);

		
		mv.addObject("boardlist", service.selectList(currentPage, BOARD_LIMIT, searchWord));
		mv.setViewName("board/list");
		return mv;
	}
	
	
	
	// 수정 페이지 이동 
	@GetMapping("/update")
	public void viewUpdateBoard() {
	}
	
	// 수정 작성 
//	@PostMapping("/update")
	@GetMapping("/updatePostTest")
	public void viewBoard() {
		int boardNum = 1;
		String boardTitle = "수정제목";
		String boardContent = "수정 내용";
		String boardOriginalFilename = "";
		String boardRenameFilename ="";
		
		BoardVo vo = new BoardVo();
		vo.setBoardTitle(boardTitle);
		vo.setBoardContent(boardContent);
		vo.setBoardOriginalFilename(boardOriginalFilename);
		vo.setBoardRenameFilename(boardRenameFilename);

		int result = service.update(vo);
		
	}
	
	// 삭제 
	@GetMapping("/delete")
	public void viewDeleteBoard() {
		int boardNum = 4;
		int result = service.delete(boardNum);
	
	}
	
	
	// 게시글 하나 읽기 + 조회 수 증가 
	@GetMapping("/read")
	public void viewOneReadBoard() {
		int boardNum = 1;
		String writer = "user22";
		BoardVo vo = service.selectOne(boardNum, writer);
		
	}
	
	
	// 원글 작성 페이지 이동 
	@GetMapping("/insert")
	public ModelAndView viewInsertBoard(
			ModelAndView mv
			, HttpServletRequest req
			, HttpSession session
			, BoardVo vo
			) {
		mv.setViewName("board/insert");
		return mv;
	}
	
	// 원글 작성 
	//@PostMapping("/insert")
	@GetMapping("/insertPostTest")
	public ModelAndView doInsertBoard(ModelAndView mv, BoardVo vo) {
		vo.setBoardContent("임시내용");
		vo.setBoardTitle("임시제목");
		vo.setBoardWriter("user11");
		int result = service.insert(vo);
		return mv;
	}
	
	// 답글 작성 페이지 이동 
	@GetMapping("/insertReply")
	public ModelAndView viewInsertReply(ModelAndView mv, int boardNum) {
		
		mv.setViewName("insertReply");
		return mv;
	}
	
	// 답글 작성 
	@GetMapping("/insertReplyPostTest")
	public ModelAndView doInsertReply(ModelAndView mv, BoardVo vo) {
		
		int boardNum = 1;
		vo.setBoardNum(boardNum);
		
		vo.setBoardContent("임시1번글답글내용");
		vo.setBoardTitle("임시1번답글제목");
		vo.setBoardWriter("user11");
		
		service.insert(vo);
		return mv;
	}
	
	@RequestMapping("/test")
	public ModelAndView test(ModelAndView mv) {
		return mv;
	}
	
	
}
