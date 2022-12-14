<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap core CSS -->
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
   crossorigin="anonymous">

<title>블라블라 :: 로그인</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Raleway:wght@400;800&display=swap');

.login {
   display: block;
   margin: auto;
   margin-top: 150px;
   padding: 50px 10px;
   width: 480px;
   border-radius: 5px;
   box-shadow: 5px 5px 40px gray;
}

.btn-dark {
   background-color: $gray-800;
}

a:link, a:visited, a:active {
   text-decoration: none;
   color: $gray-500;
}

a:hover {
   text-decoration: none;
   color: $gray-800;
}

.row {
   margin-bottom: 10px;
}

.error {
   margin-bottom: 0;
   height: 30px;
   color: red;
}

.id {
   border-radius: 5px 5px 0 0;
   margin-bottom: 0;
}
.pwd {
   border-radius: 0 0 5px 5px;
   border-top: 0;
}

.form-control {
   height: 50px;

}

</style>


</head>
<body class="text-center">
   <div class="container login">
      <form:form name="f" action="${root}login" method="POST">
         
         <div class="row logo justify-content-center">
            <div class="col-lg-10">
               <a href="${path}/"> 
                  <img class="mb-4" src="${path}/resources/images/logo.png" alt="블라블라" width="auto" height="auto">
               </a>
            </div>
         </div>
         <div class="row id justify-content-center">
            <div class="col-lg-10">
               <input type="text" class="form-control id" name="user_id" placeholder="아이디" required autocomplete="off">
            </div>

         </div>
         <div class="row pwd justify-content-center">
            <div class="col-lg-10">
               <input type="password" class="form-control pwd" name="user_password" placeholder="비밀번호" required autocomplete="off"> 
            </div>
         </div>
         <div class="row error justify-content-center">
            <div class="col-lg-10">
            <c:if test="${param.error != null}">
               <p>아이디와 비밀번호를 확인해주세요</p>
               ${prevPage}
            </c:if>
            <c:if test="${param.error == null}">
            </c:if>
            </div>
            
         </div>
         <div class="row button justify-content-center">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <div class="col-lg-10">
               <button class="w-100 btn btn-lg btn-dark" type="submit">로그인</button>
            </div>
         </div>
         <div class="row justify-content-center">
            <div class="col-lg-10">
               <span><a class="link" href="<c:url value="/join" />">회원가입</a></span>
               <%-- <span>|</span>
               <span><a class="link" href="<c:url value="/loginUpdatePwd" />">비밀번호 찾기</a></span> --%>
            </div>
         </div>
         

      </form:form>
   </div>

   <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
      crossorigin="anonymous"></script>
</body>


</html>

<%-- 
      
      <p>
         <label for="username">아이디</label> <input type="text" id="user_id"
            name="user_id" />
      </p>
      <p>
         <label for="password">비밀번호</label> <input type="password"
            id="user_password" name="user_password" />
      </p>
      <input type="hidden" name="${_csrf.parameterName}"
         value="${_csrf.token}" />
      <button type="submit" class="btn">로그인</button>


   <br />
   <a href="<c:url value="/join" />">회원가입</a> --%>