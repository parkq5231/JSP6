<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 보기</title>

<script type="text/javascript">
	function deleteAlert() {
		var yn = confirm("정말 삭제하시겠습니까?");
		if (yn) {
			frm.action = "boardDelete.do";
			frm.submit();
		}
	}//end of func
	function editAlert() {
		frm.action = "boardEditForm.do";
		frm.submit();
	}
</script>
</head>
<body>
	<jsp:include page="../main/menu.jsp" />

	<!-- Page content -->
	<div class="w3-content" style="max-width: 2000px; margin-top: 46px">
		<div align="center">
			<div>
				<h1>게시글 상세 보기</h1>
			</div>
			<form id="frm" name="frm" method="post">
				<div>
					<table border="1">
						<tr>
							<th width="100">글번호</th>
							<td width="100"><input type="text" id="bId" name="bId"
								value="${vo.bId}" readonly="readonly"></td>
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
							<td colspan="7"><textarea rows="7" cols="120">${vo.bContent}</textarea></td>
						</tr>
					</table>
					<br>
					<div>
						<c:if test="${not empty list }">
							<c:forEach var="rvo" items="${list}">
								<table border="1">
									<tr>
										<td width="500">${rvo.subject}</td>
										<td width="100">${rvo.rdate }</td>
									</tr>
								</table>
							</c:forEach>
						</c:if>
					</div>
				</div><br>
				<div>
					<button type="button" onclick="editAlert()">수 정</button>
					&nbsp;&nbsp;
					<button type="button" onclick="deleteAlert()">삭 제</button>
					&nbsp;&nbsp;
					<button type="button" onclick="location.href='boardList.do'">목
						록</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>