<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Footer</title>
<link href="${path}/resources/css/footer.css" rel="stylesheet" />

</head>
<body>
	<footer id="footer">
		<div class="container">
			<div class="row foot">
				<div class="footer">
					<ul>
						<li><a href="${pageContext.request.contextPath}/notice">공지사항</a></li>
						<li><a href="#">사이트 도움말</a></li>
						<li><a href="https://kr.teamblind.com/setting/term">사이트
								이용약관</a></li>
						<li><a href="https://kr.teamblind.com/setting/privacy">개인정보취급방침</a></li>
						<li><a href="javascript:popup(this.form)" >신고가이드</a></li>

						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<li><a href="${pageContext.request.contextPath}/admin">관리자
									페이지</a></li>
						</sec:authorize>
					</ul>
					<address>
						<a href="${pageContext.request.contextPath}">Copyright &copy;
							blahblah All Rights Reserved </a>
					</address>
				</div>
			</div>
		</div>
	</footer>
</body>
<script type="text/javascript">
	function popup() {
		var url = "${pageContext.request.contextPath}/home/stipulation";
		var name = "info/stipulation";
		var option = "width = 800px, height=600px, top=300px, left=300px, scrollbars=yes fullscreen=yes";
		window.open(url, name, option);
	}
</script>
</html>