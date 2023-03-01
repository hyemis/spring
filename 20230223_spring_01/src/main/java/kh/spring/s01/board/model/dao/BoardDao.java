package kh.spring.s01.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.s01.board.model.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(BoardVo vo) {
		return sqlSession.insert("board.insert", vo);
	}
	
	public int update(BoardVo vo) {
		return sqlSession.update("board.update", vo);
	}
	
	public int delete(int boardNum) {
		return sqlSession.delete("board.delete", boardNum);
	}
	
	public int selectOne(int boardNum) {
		return sqlSession.selectOne("board.selectOne", boardNum);
	}
	
	public List<BoardVo> selectList() {
		return sqlSession.selectList("board.selectList");
	}
	
	public int selectOneCount() {
		return sqlSession.selectOne("board.selectOneCount");
	}

}
