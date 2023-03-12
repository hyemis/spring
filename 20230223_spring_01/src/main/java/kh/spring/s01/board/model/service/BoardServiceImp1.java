package kh.spring.s01.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.s01.board.model.dao.BoardDao;
import kh.spring.s01.board.model.vo.BoardVo;

@Service
public class BoardServiceImp1 implements BoardService{

	@Autowired
	private BoardDao dao;
	
	@Override
	public int insert(BoardVo vo) {
		if(vo.getBoardNum() != 0) {
			dao.updateForReply(vo.getBoardNum());
		}
		return dao.insert(vo);
	}

	@Override
	public int update(BoardVo vo) {
		return dao.update(vo);
	}

	@Override
	public int delete(int boardNum) {
		return dao.delete(boardNum);
	}

	@Override
	public BoardVo selectOne(int boardNum, String writer) {
		BoardVo result = dao.selectOne(boardNum);
		
		if(!result.getBoardWriter().equals(writer)) {
			dao.updateReadCount(boardNum);
		}
		
		return result;
	}

	@Override
	public List<BoardVo> selectList(int currentPage, int limit, String searchWord) {
		return dao.selectList(currentPage, limit, searchWord);
	}

	@Override
	public int selectOneCount(String searchWord) {
		return dao.selectOneCount(searchWord);
	}

	@Override
	public List<BoardVo> selectList() {
		return dao.selectList();
	}

	@Override
	public int selectOneCount() {
		return dao.selectOneCount();
	}

	@Override
	public List<BoardVo> selectList(int currentPage, int limit) {
		return dao.selectList(currentPage, limit);
	}
	


	

}
