package co.micol.board.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.board.common.Command;
import co.micol.board.dao.BoardDao;
import co.micol.board.vo.BoardVo;

public class BoardUpdate implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 상세보기-수정
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();

		vo.setbContent(request.getParameter("bContent"));
		vo.setbId(Integer.parseInt(request.getParameter("bId")));
		
		int n = dao.update(vo);
		String viewPage = "boardList.do";

		if (n == 0) {
			viewPage = " ";//실패한 페이지 보내면 됨
		}

		return viewPage;
	}

}
