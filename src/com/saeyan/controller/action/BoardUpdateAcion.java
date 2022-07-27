package com.saeyan.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

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
		int result = boardDao.updateBoard(boardVo);
		
		System.out.println(result);
			
		PrintWriter out = response.getWriter();
		
		if(result == 1) {
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('수정완료')</script>");
			out.println("<script>location.href='BoardServlet?command=board_list'</script>");
		}
		
		
//		request.setAttribute("result", result);
		
		
//		response.sendRedirect("BoardServlet?command=board_list");
	}

}
