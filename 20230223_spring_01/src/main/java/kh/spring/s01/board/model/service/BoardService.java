package kh.spring.s01.board.model.service;

import java.util.List;

import kh.spring.s01.board.model.vo.BoardVo;

public interface BoardService {

	
	public int insert(BoardVo vo);
	
	public int update(BoardVo vo);
	
	public int delete(int boardNum);
	
	public int selectOne(int boardNum);
	
	public List<BoardVo> selectList();
	
	public int selectOneCount();
}
