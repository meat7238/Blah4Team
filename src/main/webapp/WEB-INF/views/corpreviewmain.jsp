<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업 리뷰 읽기</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/layout/header.jsp"%>

	<input type="button" value="소개"
		onclick="location.href='corpreviewintro?corpnum=${corplist.corpreview_corpnum}'" />
	<input type="button" value="리뷰"
		onclick="location.href='corpreviewmain?corpreviewnum=${corplist.corpreview_corpnum}'" />
	<input type="button" value="리뷰 쓰기"
		onclick="location.href='corpreviewwriteform'" />


	<div class="company-infomation-row basic-infomation left">
		<h2 class="header">기업리뷰</h2>
		<div
			class="company-infomation-container basic-infomation-container left">
			<table class="table-basic-infomation-primary">
				<colgroup>
					<col class="col-label">
					<col class="col-value">
					<col class="col-label">
					<col class="col-value">
				</colgroup>
				<tbody>
					<tr class="field">
						<th class="field-label">항목별 평가</th>
						<td class="field-value">
							<div class="value-container">
								<div class="value"></div>
							</div>
						</td>
					<tr class="field">
						<th class="field-label">커리어 향상</th>
						<td class="field-value">
							<div class="value-container">
								<div class="value">${corplist.corpreview_starpoint1}</div>
							</div>
						</td>

					</tr>

					<tr class="field">
						<th class="field-label">급여 및 복지</th>
						<td class="field-value">
							<div class="value-container">
								<div class="value">${corplist.corpreview_starpoint2}</div>
							</div>
						</td>

					</tr>

					<tr class="field">
						<th class="field-label">업무와 삶의 균형</th>
						<td class="field-value">
							<div class="value-container">
								<div class="value">${corplist.corpreview_starpoint3}</div>
							</div>
						</td>

					</tr>

					<tr class="field">
						<th class="field-label">사내문화</th>
						<td class="field-value">
							<div class="value-container">
								<div class="value">${corplist.corpreview_starpoint4}</div>
							</div>
						</td>

					</tr>

					<tr class="field">
						<th class="field-label">경영진</th>
						<td class="field-value">
							<div class="value-container">
								<div class="value">${corplist.corpreview_starpoint5}</div>
							</div>
						</td>

					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="company-infomation-row basic-infomation right">

		<div
			class="company-infomation-container basic-infomation-container right">
			<table class="table-basic-infomation-primary">
				<colgroup>
					<col class="col-label">
					<col class="col-value">
					<col class="col-label">
					<col class="col-value">
				</colgroup>
				<tbody>
					<tr class="field">
						<th class="field-label">기업 세부 리뷰</th>
						<td class="field-value">
							<div class="value-container">
								<div class="value"></div>
							</div>
						</td>

					</tr>
					<tr class="field">
						<th class="field-label">한줄평</th>
						<td class="field-value">
							<div class="value-container">
								<div class="value">${corplist.corpreview_conclusion}</div>
							</div>
						</td>

					</tr>

					<tr class="field">
						<th class="field-label">장점</th>
						<td class="field-value">
							<div class="value-container">
								<div class="value">${corplist.corpreview_pros}</div>
							</div>
						</td>

					</tr>

					<tr class="field">
						<th class="field-label">단점</th>
						<td class="field-value">
							<div class="value-container">
								<div class="value">${corplist.corpreview_cons}</div>
							</div>
						</td>

					</tr>

					<tr class="field">
						<th class="field-label">평점</th>
						<td class="field-value">
							<div class="value-container">
								<div class="value">${(corplist.corpreview_starpoint1+corplist.corpreview_starpoint2+corplist.corpreview_starpoint3+corplist.corpreview_starpoint4+corplist.corpreview_starpoint5)/5}</div>
							</div>
						</td>

					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>
</html>