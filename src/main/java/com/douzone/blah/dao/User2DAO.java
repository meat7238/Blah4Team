package com.douzone.blah.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.douzone.blah.model.User2DTO;

public interface User2DAO {
	// 회원에 대한 전체조회,추가,수정,삭제
	public void insertUser2(User2DTO dto); // 추가

	public List<User2DTO> getAllUserList(HashMap map);// 전체조회

	public int updateUser2(User2DTO dto); // 수정

	public int deleteUser2(String user_num); // 삭제

	public List<User2DTO> getSearchList(Map<String, String> map);

	// 회원가입 처리
	public int insertUser(Map<String, String> map);

	// 로그인 처리
	public Map<String, Object> selectUser(String user_id);
	
	// 마이페이지 회원 정보 조회
	public Map<String, Object> showMemberInfo(String user_id);
	
	// 마이페이지 회원 정보 수정
	public int editMemberInfo(Map<String, String> map);
	// 게시글 작성한 회원 ID얻기
	public String getUserID(int post_num);

	// userId -> userNum
	public String userId(String user);
	
	// 전송한 이메일 키 저장 
	int updateMailKey(Map<String, Object> map) throws Exception;
	// 이메일 인증 후 계정 활성화
	int updateMailAuth(Map<String, Object> map) throws Exception;
	// 인증 안 한 아이디 거르기
	int emailAuthFail(String id) throws Exception;
	
	// 아이디 중복 확인
	public int idDupleCheck(String user_id);
	// 이메일 중복 확인
	public int emailDupleCheck(String user_email);
}

