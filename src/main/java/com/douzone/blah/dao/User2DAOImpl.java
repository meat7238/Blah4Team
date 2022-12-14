package com.douzone.blah.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.douzone.blah.model.User2DTO;

@Component
public class User2DAOImpl implements User2DAO {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// 유저 추가
	@Override
	public void insertUser2(User2DTO dto) {
		// TODO Auto-generated method stub

	}

	// 유저 정보 수정
	@Override
	public int updateUser2(User2DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 유저 삭제
	@Override
	public int deleteUser2(String user_num) {
		return sqlSession.delete("deleteUser2", user_num);
	}

	// 유저 전체 조회
	@Override
	public List<User2DTO> getAllUserList(HashMap map) {
		return sqlSession.getMapper(User2DAO.class).getAllUserList(map);
	}

	// 특정유저조회
	@Override
	public List<User2DTO> getSearchList(Map<String, String> map) {
		return sqlSession.getMapper(User2DAO.class).getSearchList(map);
	}

	// 회원가입
	@Override
	public int insertUser(Map<String, String> map) {
		return sqlSession.insert("insertUser", map);
	}

	// 회원가입 아이디 중복 확인
	@Override
	public int idDupleCheck(String user_id) {
		return sqlSession.selectOne("idDupleCheck", user_id);
	}

	// 회원가입 이메일 중복 확인
	@Override
	public int emailDupleCheck(String user_email) {
		return sqlSession.selectOne("emailDupleCheck", user_email);
	}

	// 이메일 인증 키 부여
	@Override
	public int updateMailKey(Map<String, Object> map) throws Exception {
		return sqlSession.update("updateMailKey", map);
	}

	// 이메일 인증 후 계정 활성화
	@Override
	public int updateMailAuth(Map<String, Object> map) throws Exception {
		return sqlSession.update("updateMailAuth", map);
	}

	// 이메일 인증 안한 계정 처리
	@Override
	public int emailAuthFail(String user_id) throws Exception {
		return sqlSession.selectOne("emailAuthFail", user_id);
	}

	// 로그인
	@Override
	public Map<String, Object> selectUser(String user_id) {
		return sqlSession.selectOne("selectUser", user_id);
	}

	// 마이페이지 회원 정보 조회
	@Override
	public Map<String, Object> showMemberInfo(String user_id) {
		return sqlSession.selectOne("showMemberInfo", user_id);
	}

	// 마이페이지 회원 정보 수정
	@Override
	public int editMemberInfo(Map<String, String> map) {
		return sqlSession.update("editMemberInfo", map);
	}

	@Override
	public String getUserID(int post_num) {
		User2DAO user2DAO = sqlSession.getMapper(User2DAO.class);
		return user2DAO.getUserID(post_num);
	}

	@Override
	public String getUserNum(String user) {
		User2DAO user2DAO = sqlSession.getMapper(User2DAO.class);
		return user2DAO.getUserNum(user);
	}

	// 유저수 카운트
	@Override
	public int getUserCount() {
		User2DAO user2DAO = sqlSession.getMapper(User2DAO.class);
		return user2DAO.getUserCount();
	}

	// 특정회원이 읽은 게시글 수 카운트
	@Override
	public int getUserPostCount(String user_num) {
		User2DAO user2DAO = sqlSession.getMapper(User2DAO.class);
		return user2DAO.getUserPostCount(user_num);
	}

	// 권한 변경
	@Override
	public int updateAuthority(Map<String, String> map) {
		User2DAO user2DAO = sqlSession.getMapper(User2DAO.class);
		return user2DAO.updateAuthority(map);
	}

  @Override
  public String getUserIdByString(String str) {
    User2DAO user2DAO = sqlSession.getMapper(User2DAO.class);
    return user2DAO.getUserIdByString(str);
  }

}
