package com.saeyan.controller;

import com.saeyan.controller.action.*;

public class ActionFactory {
	
	private static ActionFactory instance = new ActionFactory();
	
	private ActionFactory() {
		
	}
	
	public static ActionFactory getInstace() {
		return instance;
	}
	
	public Action getAction(String command) {
		
		System.out.println("ActionFactory  getAction : " + command + "도착");
		
		Action action = null;
		
		
		if(command.equals("board_list")) {
			action = new BoardListAction();
			System.out.println("ActionFactory --> BoardListAction 리스트 호출");
		}else if(command.equals("board_write_form")) {
			action = new BoardWriteFormAction();
			System.out.println("ActionFactory --> BoardWriteFormAction 등록페이지 호출");
		}else if (command.equals("board_write")) {
	         action = new BoardWriteAction();
	         System.out.println("ActionFactory --> BoardWriteAction 등록처리 호출");
	    }else if (command.equals("board_view")) {
	         action = new BoardViewAction();
	         System.out.println("ActionFactory --> BoardViewAction 상세정보보기 호출");
	    }else if (command.equals("board_check_pass_form")) {
	    	System.out.println("체크폼 요청");
	         action = new BoardCheckPassFormAction();
	    }else if(command.equals("board_check_pass")) {
	    	action = new BoardCheckPassAction();
	    	System.out.println("체크패스 요청");
	    }else if(command.equals("board_update_form")) {
	    	action = new BoardUpdateFormAction();
	    	System.out.println("업데이트 폼 요청");
	    }else if(command.equals("board_update")) {
	    	action = new BoardUpdateAcion();
	    	System.out.println("업데이트 처리요청");
	    }else if(command.equals("board_delete")) {
	    	action = new BoardDeleteAction();
	    	System.out.println("삭제");
	    }
		return action;
	}
		
}
