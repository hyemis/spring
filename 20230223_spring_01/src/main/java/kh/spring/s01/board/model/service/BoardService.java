package kh.spring.s01.board.model.service;

import java.util.List;

import kh.spring.s01.board.model.vo.BoardVo;

public interface BoardService {

	
	public int insert(BoardVo vo);
	
	public int update(BoardVo vo);
	
//	public int updateForReply(int boardNum);
	
	public int delete(int boardNum);
	
	public BoardVo selectOne(int boardNum, String writer);
//	public int updateReadCount(int boardNum);
	
	// 전체 조회 
	public List<BoardVo> selectList();
	
	// 페이징 처리 조회 
	public List<BoardVo> selectList(int currentPage, int limit, String searchWord);
	
	public int selectOneCount(String searchWord);
}
