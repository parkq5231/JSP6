package co.micol.board.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.board.common.Command;
import co.micol.board.dao.BoardDao;
import co.micol.board.vo.BoardVo;
import co.micol.board.vo.ReplyVo;

public class BoardView implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 상세보기
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		ReplyVo rvo = new ReplyVo();
		ArrayList<ReplyVo> list = new ArrayList<ReplyVo>();

		vo.setbId(Integer.parseInt(request.getParameter("bId")));
		rvo.setBid(Integer.parseInt(request.getParameter("bId"))); // 댓글 가져오기

		// 주글 읽기
		vo = dao.select(vo); // 내 그릇에 다시 받겠다는 의미 new BoardVo와 같은 의미
		
		dao = new BoardDao();	//dao 초기화 하여 다시 동작시키겠다는 의미
		// 댓글읽기
		list = dao.replySelect(rvo);

		request.setAttribute("vo", vo); // 주 글 담기
		request.setAttribute("list", list);// 댓글 담기
		return "board/boardView";
	}

}
