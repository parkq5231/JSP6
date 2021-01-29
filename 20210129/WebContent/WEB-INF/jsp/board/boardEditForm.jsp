<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>

</head>
<body>
	<jsp:include page="../main/menu.jsp" />

	<!-- Page content -->
	<div class="w3-content" style="max-width: 2000px; margin-top: 46px">
		<div align="center">
			<div>
				<h1>게시글 수정하기</h1>
			</div>
			<form id="frm" name="frm" action="boardUpdate.do" method="post">
				<div>
					<table border="1">
						<tr>
							<th width="100">글번호</th>
							<td width="100"><input type="text" id="bId" name="bId"
								value="${vo.bId}"></td>
							<th width="100">작성자</th>
							<td width="120">${vo.bName}</td>
							<th width="100">작성일자</th>
							<td width="120" align="center">${vo.bDate}</td>
							<th width="100">조회수</th>
							<td width="100" align="center">${vo.bHit}</td>
						</tr>
						<tr>
							<th width="100">제 목</th>
							<td colspan="7">${vo.bTitle}</td>
						</tr>
						<tr>
							<th width="100">내 용</th>
							<td colspan="7"><textarea rows="7" cols="120" id="bContent"
									name="bContent">${vo.bContent}</textarea></td>
						</tr>
					</table>
					<br>
				</div>
				<div>
					<button type="submit">수 정</button>
					&nbsp;&nbsp;
					<button type="reset">취 소</button>
					&nbsp;&nbsp;
					<button type="button" onclick="location.href='boardList.do'">목
						록</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>