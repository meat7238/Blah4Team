package com.douzone.blah.security;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

	// 로그인 처리가 성공했을 때의 코드
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		// IP, 세션 ID
		WebAuthenticationDetails web = (WebAuthenticationDetails) auth.getDetails();
		System.out.println("IP : " + web.getRemoteAddress());
		System.out.println("Session ID : " + web.getSessionId());

		// 인증 ID
		System.out.println("name : " + auth.getName());

		// 권한 리스트
		List<GrantedAuthority> authList = (List<GrantedAuthority>) auth.getAuthorities();
		System.out.print("권한 : ");
		for (int i = 0; i < authList.size(); i++) {
			System.out.print(authList.get(i).getAuthority() + " ");
		}
		System.out.println();

		// Security가 요청을 가로챈 경우 사용자가 원래 요청했던 URI 정보를 저장한 객체
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		String uri = "";
		// 있을 경우 URI 등 정보를 가져와서 사용
		if (savedRequest != null) {
			uri = savedRequest.getRedirectUrl();

			// 세션에 저장된 객체를 다 사용한 뒤에는 지워줘서 메모리 누수 방지
			requestCache.removeRequest(request, response);

			System.out.println(uri);
		}

		/* 로그인 버튼 눌러 접속했을 경우의 데이터 get */
		String prevPage = (String) request.getSession().getAttribute("prevPage");
		if (prevPage != null) {
			request.getSession().removeAttribute("prevPage");
		} else if (prevPage != null && !prevPage.equals("")) {
			uri = prevPage;
		}
		
		// 세션 Attribute 확인
		Enumeration<String> list = request.getSession().getAttributeNames();
		while (list.hasMoreElements()) {
			System.out.println(list.nextElement());
		}
		
		// 로그인 성공시 보낼 uri
		response.sendRedirect("/");
	}

}