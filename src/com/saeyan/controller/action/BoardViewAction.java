package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardView.jsp";
		
		String num = request.getParameter("num");
		
		BoardDAO boardDao = BoardDAO.getInstance();
		
		boardDao.updateReadCount(num);
		System.out.println("카운트 증가?? BoardViewAction -> boardDao.updateReadCount(num) 호출");
		BoardVO boardVo = boardDao.selectOneBoardByNum(num);
		System.out.println("한가지 글에 대한 정보 가져오기");
		
		request.setAttribute("board", boardVo);
		
		
//		response.sendRedirect("BoardServlet?command=board_list");
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
