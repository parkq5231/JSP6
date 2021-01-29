package co.micol.board.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.board.web.BoardDelete;
import co.micol.board.web.BoardEditForm;
import co.micol.board.web.BoardForm;
import co.micol.board.web.BoardInsert;
import co.micol.board.web.BoardList;
import co.micol.board.web.BoardUpdate;
import co.micol.board.web.BoardView;
import co.micol.board.web.MainCommand;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
	// HaspMap의 Key는 String Value는 Command 이름은 map이라는 소리 = 인스턴스 객체 생성 및 초기화

	public FrontController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new MainCommand());// 메인 호출
		map.put("/boardList.do", new BoardList()); // 자유게시판
		map.put("/boardView.do", new BoardView()); // 상세보기
		map.put("/boardDelete.do", new BoardDelete());// 상세보기-삭제
		map.put("/boardEditForm.do", new BoardEditForm());//상세보기-수정폼으로
		map.put("/boardUpdate.do", new BoardUpdate());// 상세보기-수정하기
		map.put("/boardForm.do", new BoardForm()); // 새글작성
		map.put("/boardInsert.do", new BoardInsert());// 새글작성-추가
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 서비스 객체 찾기
		request.setCharacterEncoding("utf-8");
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		String path = uri.substring(contextPath.length()); // 실제 요청한 주소

		Command command = map.get(path); // new Command()와 같음 // path찾아옴
		String viewPage = command.exec(request, response); // command 객체 실행하여 페이지를 넘겨옴
		// view resolve
		if (!viewPage.endsWith(".do"))
			viewPage = "/WEB-INF/jsp/" + viewPage + ".jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);	// 요청한 값을 다른 자원으로 넘긴다는 의미
	}

}
