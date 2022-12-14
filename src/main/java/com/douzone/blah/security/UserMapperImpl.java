package com.douzone.blah.security;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
	
	@Autowired
	private SqlSession sqlSession;


	// 회원가입
	@Override
	public int insertUser(Map<String, String> map) {
		return sqlSession.insert("insertUser", map);
	}
	
	// 로그인
	@Override
	public Map<String, Object> selectUser(String user_id) {
		return sqlSession.selectOne("selectUser", user_id);
	}

}
