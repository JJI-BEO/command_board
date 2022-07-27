package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("BoardWriteAction execute");
		BoardVO boardVo = new BoardVO();
		
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		boardVo.setName(name);
		boardVo.setPass(pass);
		boardVo.setEmail(email);
		boardVo.setTitle(title);
		boardVo.setContent(content);
		
		
		BoardDAO boardDao = BoardDAO.getInstance();
		boardDao.insertBoard(boardVo);
		
		response.sendRedirect("BoardServlet?command=board_list");
		/* new BoardListAction().execute(request, response); */
	}

}
