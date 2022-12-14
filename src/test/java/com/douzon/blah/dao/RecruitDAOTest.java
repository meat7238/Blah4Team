package com.douzon.blah.dao;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.douzone.blah.dao.RecruitDAO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class RecruitDAOTest {
	
	@Setter(onMethod_ = @Autowired)
	private RecruitDAO dao;
	
	@Test
	public void testGetList() throws ParseException {
		dao.selectRecruit("삼성전자").forEach(x -> log.warn(x));
	}
}
