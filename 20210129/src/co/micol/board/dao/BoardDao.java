package co.micol.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.micol.board.common.DAO;
import co.micol.board.vo.BoardVo;
import co.micol.board.vo.ReplyVo;

public class BoardDao extends DAO {
	private PreparedStatement psmt; // SQL실행하는 용도
	private ResultSet rs; // 결과를 담는 용도

	public ArrayList<BoardVo> selectList() {
		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
		BoardVo vo;
		String sql = "SELECT * FROM BOARD";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVo();
				vo.setbId(rs.getInt("bId"));
				vo.setbName(rs.getString("bName"));
				vo.setbTitle(rs.getString("bTitle"));
				vo.setbContent(rs.getString("bContent"));
				vo.setbDate(rs.getDate("bDate"));
				vo.setbHit(rs.getInt("bHit"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public BoardVo select(BoardVo vo) {
		String sql = "SELECT * FROM BOARD WHERE BID=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getbId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo = new BoardVo();
				vo.setbId(rs.getInt("bId"));
				vo.setbName(rs.getString("bName"));
				vo.setbTitle(rs.getString("bTitle"));
				vo.setbContent(rs.getString("bContent"));
				vo.setbDate(rs.getDate("bDate"));
				vo.setbHit(rs.getInt("bHit"));
				hitCount(rs.getInt("bId")); // 조회수 증가 method
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	public BoardVo editSelect(BoardVo vo) {
		String sql = "SELECT * FROM BOARD WHERE BID=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getbId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo = new BoardVo();
				vo.setbId(rs.getInt("bId"));
				vo.setbName(rs.getString("bName"));
				vo.setbTitle(rs.getString("bTitle"));
				vo.setbContent(rs.getString("bContent"));
				vo.setbDate(rs.getDate("bDate"));
				vo.setbHit(rs.getInt("bHit"));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	private void hitCount(int bid) {
		String sql = "UPDATE BOARD SET BHIT = BHIT+1 WHERE BID=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bid);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// end of count

	public int insert(BoardVo vo) {
		int n = 0;
		String sql = "INSERT INTO BOARD(BID,BNAME,BTITLE,BCONTENT,BDATE) VALUES(BIDSEQ.NEXTVAL,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getbName());
			psmt.setString(2, vo.getbTitle());
			psmt.setString(3, vo.getbContent());
			psmt.setDate(4, vo.getbDate());

			n = psmt.executeUpdate();
			System.out.println(n + "건 추가.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	public int update(BoardVo vo) {
		int n = 0;
		String sql = "UPDATE BOARD SET BCONTENT=? WHERE BID=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getbContent());
			psmt.setInt(2, vo.getbId());

			n = psmt.executeUpdate();
			System.out.println(n + "건 업데이트.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	public int delete(BoardVo vo) {
		int n = 0;
		String sql = "DELETE FROM BOARD WHERE BID=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getbId());

			n = psmt.executeUpdate();
			System.out.println(n + "건 삭제.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) { // 기본이 SQLexception
			e.printStackTrace();
		}
	}// end of close

	// 댓글불러오기
	public ArrayList<ReplyVo> replySelect(ReplyVo vo) {	//댓글가져오기
		ArrayList<ReplyVo> replyList = new ArrayList<ReplyVo>();
		ReplyVo rvo;
		String sql = "SELECT * FROM REPLY WHERE BID=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBid());
			rs = psmt.executeQuery();
			while (rs.next()) {
				rvo = new ReplyVo(); // vo 초기화
				rvo.setBid(rs.getInt("bid"));
				rvo.setRnum(rs.getInt("rnum"));
				rvo.setSubject(rs.getString("subject"));
				rvo.setRdate(rs.getDate("rdate"));
				replyList.add(rvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return replyList;
	}

}
