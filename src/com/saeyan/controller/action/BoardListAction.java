package com.saeyan.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardListAction implements Action {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardList.jsp";
		
		BoardDAO boardDao = BoardDAO.getInstance();
		
		
		//페이징작업
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = boardDao.getListCount();
		
		
		//페이징 10개씩 짤라서 리스트에 담음
		List<BoardVO> boardList = boardDao.getBoardList(page, limit);
		
		//10개 단위로 페이지 나누기
		int maxPage = (int)((double)listCount/limit) + (listCount%limit == 0?0:1);
		System.out.println("최대 페이지 수 : " + maxPage);
		int startPage = ((int)((double)page/limit+0.9)-1)*limit+1;
		System.out.println("시작페이지 : " + startPage);
		int endPage = startPage + 10 -1;
		System.out.println("endpage : " + endPage);
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 전체 리스트 조회 요청
//		List<BoardVO> boardList = boardDao.selectAllBoards();
		
		request.setAttribute("boardList", boardList);
		
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("listCount", listCount);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
}
