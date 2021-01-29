<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css">
tr.row:hover {
	background-color: lightyellow;
}
</style>

<!-- 글 상세보기 시 post방법으로 데이터 전달 -->
<script type="text/javascript">
	function formSubmit(str) {
		frm.bId.value = str;
		frm.submit();
	}
</script>

<jsp:include page="../main/menu.jsp" />

<!-- Page content -->
<div class="w3-content" style="max-width: 2000px; margin-top: 46px"
	align="center">
	<div>
		<h1>게시글 목록 보기</h1>
	</div>

	<div>
		<form id="frm" name="frm" action="boardView.do" method="post">
			<input type="hidden" id="bId" name="bId">
		</form>
	</div>
	<div>
		<table border="1">
			<tr>
				<th width="100">글번호</th>
				<th width="300">제목</th>
				<th width="150">작성자</th>
				<th width="150">작성일자</th>
				<th width="100">조회수</th>
			</tr>
			<c:if test="${empty list}">
				<!-- list가 비어있을 때 즉,table에 값이 없을 때 -->
				<tr>
					<td align="center" colspan="5">게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list }">
				<c:forEach var="vo" items="${list}">
					<!-- 실행한 결과를 list에 저장 -->
					<tr class="row" onclick="formSubmit(${vo.bId})">
						<td align="center">${vo.bId}</td>
						<td align="center">${vo.bTitle}</td>
						<td align="center">${vo.bName}</td>
						<td align="center">${vo.bDate}</td>
						<td align="center">${vo.bHit}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<br>
		<div>
			<button type="button" onclick="location.href='boardForm.do'">새글작성</button>
			<!-- onclick속성에 controller로 가라는 .do를 준거 -->
		</div>
	</div>
</div>
</body>
</html>