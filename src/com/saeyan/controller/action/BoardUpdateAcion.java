package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardUpdateAcion implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardVO boardVo = new BoardVO();
		
		String name = request.getParameter("name");
		int num = Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		boardVo.setContent(content);
		boardVo.setEmail(email);
		boardVo.setName(name);
		boardVo.setNum(num);
		boardVo.setPass(pass);
		boardVo.setTitle(title);
		
		BoardDAO boardDao = BoardDAO.getInstance();
		boardDao.updateBoard(boardVo);
		
		
		
		response.sendRedirect("BoardServlet?command=board_list");
	}

}
