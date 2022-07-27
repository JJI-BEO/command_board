package com.saeyan.dao;

import java.sql.*;
import java.util.*;

import com.saeyan.dto.BoardVO;

import util.DBManager;

public class BoardDAO {
	private BoardDAO() {
		
	}
	
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	//전체 리스트 조회
	public List<BoardVO> selectAllBoards() {
		
		String sql = "select * from board order by num desc";
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardVO boardVo = new BoardVO();
				boardVo.setNum(rs.getInt("num"));
				boardVo.setName(rs.getString("name"));
				boardVo.setEmail(rs.getString("email"));
				boardVo.setPass(rs.getString("pass"));
				boardVo.setTitle(rs.getString("title"));
				boardVo.setContent(rs.getString("content"));
				boardVo.setReadcount(rs.getInt("readcount"));
				boardVo.setWritedate(rs.getTimestamp("writedate"));
				list.add(boardVo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, stmt, rs);
		}
		
		return list;
	}
	
	// 게시글 등록
	public void insertBoard(BoardVO boardVo) {
		String sql = "insert into board(num, name, email, pass, title, content)"
				+ "values(board_seq.nextval, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVo.getName());
			pstmt.setString(2, boardVo.getEmail());
			pstmt.setString(3, boardVo.getPass());
			pstmt.setString(4, boardVo.getTitle());
			pstmt.setString(5, boardVo.getContent());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	//조회수 증가
	public void updateReadCount(String num) {
		String sql = "update board set readcount=readcount+1 where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	//하나의 글 조회
	public BoardVO selectOneBoardByNum(String num) {
		BoardVO boardVo = new BoardVO();
		
		String sql = "select * from board where num=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardVo.setNum(rs.getInt("num"));
				boardVo.setName(rs.getString("name"));
				boardVo.setEmail(rs.getString("email"));
				boardVo.setPass(rs.getString("pass"));
				boardVo.setTitle(rs.getString("title"));
				boardVo.setContent(rs.getString("content"));
				boardVo.setReadcount(rs.getInt("readcount"));
				boardVo.setWritedate(rs.getTimestamp("writedate"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return boardVo;
	}
	
	
	//업데이트
	public void updateBoard(BoardVO boardVo) {
		String sql = "update board set content=?, email=?, name=?, pass=?, title=? where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getContent());
			pstmt.setString(2, boardVo.getEmail());
			pstmt.setString(3, boardVo.getName());
			pstmt.setString(4, boardVo.getPass());
			pstmt.setString(5, boardVo.getTitle());
			pstmt.setInt(6, boardVo.getNum());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	//삭제
	public void boardDelete(int num) {
		String sql = "delete board where num = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
			
			
}
