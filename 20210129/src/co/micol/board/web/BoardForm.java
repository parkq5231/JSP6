package co.micol.board.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.board.common.Command;

public class BoardForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//새글작성
		return "board/boardForm";
	}

}
