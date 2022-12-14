<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/admin.css"
	rel="stylesheet" />


<meta charset="UTF-8">
<title>admin Home</title>
</head>
<body>
	<div class="wrapper">
		<header>
			<%@ include file="/WEB-INF/views/layout/header.jsp"%>
		</header>
		<div class="main-content">
<!-- ------------------------------------------------------------------------------------------------------------- -->

			<div class="tabs">
				<i font-weight="bold"><h2>Admin Page</h2></i><br />
				<div class="tab-header">
					<div class="active">
						<i class="fa fa-code"></i> π νμμ λ³΄ κ΄λ¦¬
					</div>
					<div>
						<i class="fa fa-pencil-square"></i> π κ³΅μ§μ¬ν­ κ΄λ¦¬
					</div>
					<div>
						<i class="fa fa-bar-chart"></i> β μ κ³  κ΄λ¦¬
					</div>
					<div>
						<i class="fa fa-envelope"></i> π’ κ³΅κ³  μλ ₯
					</div>
				</div>
				<div class="tab-indicator"></div>
				<div class="tab-content">

					<div class="active">
						<i><h2>π</h2></i>
						<h1>
							<a href="${pageContext.request.contextPath}/admin/edit"> νμμ λ³΄ κ΄λ¦¬</a>
						</h1>
						</br>
						<p>
							νμμ λ³΄λ₯Ό κ΄λ¦¬νλ κ³΅κ°μλλ€.<br> μ μ λ₯Ό κ²μν  μ μμΌλ©°, <br> μ΅κ·Ό κ°μν μ μ  μμΌλ‘
							μ λ ¬νμ¬ νμ΄μ§μ²λ¦¬λμ΄μμ΄ κ°λμ±μ΄ μ’μ΅λλ€.<br> μ£Όμ κΈ°λ₯μΌλ‘λ μ μ λ₯Ό μ­μ νκ±°λ κΆνμ κ΄λ¦¬μλ‘ νΉμ
							λ€μ μ μ λ‘ λ³κ²½ν  μ μμ΅λλ€.
						</p>
					</div>

					<div>
						<i><h2>π</h2></i>
						<h1>
							<a href="${pageContext.request.contextPath}/admin/notice"> κ³΅μ§μ¬ν­ κ΄λ¦¬</a>
						</h1>
						<p>
						</br>κ΄λ¦¬μκ° κ³΅μ§μ¬ν­μ μμ±ν  μ μλ κ³΅κ°μλλ€. μμ±λ κ³΅μ§μ¬ν­μ μ½μ μ μμ΅λλ€.
						</p>
					</div>

					<div>
						<i><h2>β</h2></i>
						<h1>
							<a href="${pageContext.request.contextPath}/admin/ManageReport"> μ κ³  κ΄λ¦¬</a>
						</h1>
						</br>
						<p>κ²μνμ κ³ , κ²μνλκΈμ κ³ , κΈ°μλ¦¬λ·° μ κ³ λ±μ μ’ν©μ μΌλ‘ κ΄λ¦¬νλ κ³΅κ°μλλ€.</p>
					</div>

					<div>
						<i><h2>π’</h2></i>
						<h1>
							<a href="${pageContext.request.contextPath}/admin/recruit"> κ³΅κ³  μλ ₯ </a>
						</h1>
						</br>
						<p>κ΄λ¦¬μλ DBμ μλ κΈ°μμ λ³΄λ₯Ό ν λλ‘ ν΄λΉ κΈ°μμ κ³΅κ³ λ₯Ό μλ ₯ ν  μ μμ΅λλ€.</p>
					</div>

				</div>
			</div>


			<%-- 	</br> λ‘κ·ΈμΈ λ κ΄λ¦¬μ :
	<sec:authentication property="principal.username" />

	<form action="adminLogout" method="post">
		<input type="submit" value="λ‘κ·Έμμ">
	</form>
 --%>
			<script type="text/javascript">
				function _class(name) {
					return document.getElementsByClassName(name);
				}

				let tabPanes = _class("tab-header")[0]
						.getElementsByTagName("div");

				for (let i = 0; i < tabPanes.length; i++) {
					tabPanes[i]
							.addEventListener(
									"click",
									function() {
										_class("tab-header")[0]
												.getElementsByClassName("active")[0].classList
												.remove("active");
										tabPanes[i].classList.add("active");

										_class("tab-indicator")[0].style.top += `${i*30}px`;

										_class("tab-content")[0]
												.getElementsByClassName("active")[0].classList
												.remove("active");
										_class("tab-content")[0]
												.getElementsByTagName("div")[i].classList
												.add("active");

									});
				}
			</script>
<!-- ------------------------------------------------------------------------------------------------------------- -->
		</div>
		<footer>
			<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
		</footer>
	</div>

</body>
</html>