package com.douzone.blah.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.douzone.blah.dao.CorpDAO;
import com.douzone.blah.dao.ReportDAO;
import com.douzone.blah.dao.User2DAO;
import com.douzone.blah.model.CorpDTO;
import com.douzone.blah.model.CorpreviewDTO;
import com.douzone.blah.model.ReportCrDTO;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class CorpController {

	@Resource
	private CorpDAO corpDAOImpl;

	@Resource
	private User2DAO user2DAOImpl;

	@Resource
	private ReportDAO reportDAOImpl;

	// 기업 정보 검색 페이지 받기

	@RequestMapping(value = "/corpreviewhome", method = RequestMethod.GET)
	public String corpreviewhome(HttpServletRequest request) {
		return "corp/corpreviewhome";
	}

	// 기업 정보 검색 하기

	@RequestMapping(value = "/corpreviewhome", method = RequestMethod.POST)
	public String editList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyvalue = request.getParameter("keyvalue");
		List<CorpDTO> corplist = corpDAOImpl.searchCorp(keyvalue);

		request.setAttribute("corplist", corplist);

		return "corp/corpreviewhome";

	}

	// 기업 정보 받기

	@RequestMapping("/corpreviewintro")
	public String corpreviewintro(String corpnum, Model model) {

		CorpDTO dto = corpDAOImpl.corpDetail(corpnum);

		model.addAttribute("corplist", dto);

		return "corp/corpreviewintro";

	}

	// 기업에 대해 리뷰 확인

	@RequestMapping("/corpreviewmain")
	public String corpreviewmain(String corpreviewnum, HttpServletRequest request, Principal principal) {

		String user_id = principal.getName();

		String user_num = user2DAOImpl.getUserNum(user_id);

		request.setAttribute("corpreview_usernum", user_num);

		List<Map<String, Object>> corpReiviewList = corpDAOImpl.corpReview(corpreviewnum);

		log.warn("받아온 값 : " + corpReiviewList);
		request.setAttribute("corplist", corpReiviewList);

		// model.addAttribute("corplist", corpDAOImpl.corpReview(corpreviewnum));
		return "corp/corpreviewmain";

	}

	// 기업 리뷰 삭제

	@RequestMapping("/corpreviewdelete")
	public String corpreviewdelete(String corpreview_num, HttpServletRequest request, Principal principal) {

		corpDAOImpl.deleteCorpreview(corpreview_num);

		return "/corp/corpreviewmain";

	}

	// 기업 리뷰 신고 작성페이지

	@RequestMapping(value = "/corpreviewreportwriteform", method = RequestMethod.GET)
	public String corpreviewreportwriteform(String corpreviewnum, HttpServletRequest request, Principal principal) {

		String user_id = principal.getName();

		String user_num = user2DAOImpl.getUserNum(user_id);

		request.setAttribute("corpreview_usernum", user_num);

		String corpreview_num = request.getParameter("corpreviewnum");

		request.setAttribute("corpreview_num", corpreview_num);

		List<CorpreviewDTO> corplist = corpDAOImpl.searchReview(corpreview_num);

		request.setAttribute("corplist", corplist);
		System.out.println("유저 넘버 : " + user_num);
		System.out.println("리뷰 넘버 : " + corpreview_num);
		System.out.println("리뷰 리스트 : " + corplist);

		return "corp/corpreviewreportwriteform";

	}

//	// 기업 리뷰 신고

	@RequestMapping(value = "/corpreviewreportwrite", method = RequestMethod.POST)
	public String corpreviewreportwrite(ReportCrDTO dto, HttpServletRequest request) {

		System.out.println("여기오니 ===========> ");
		System.out.println("dto 정보" + dto);

		reportDAOImpl.insertReportCr(dto);

		System.out.println("들어갔니? ===========> ");

//		String str = request.getParameter("corpreview_corpnum");
//		System.out.println(str);

		return "corp/corpreviewreportwriteform";

	}

	// 기업에 대해 리뷰 작성 양식 받기

	@RequestMapping(value = "/corpreviewwriteform", method = RequestMethod.GET)
	public String corpreviewwriteform(String user_id, HttpServletRequest request, Principal principal) {
		String corpNum = request.getParameter("corp");
//		Map<String, String> map = new HashMap<>();
//		map.put("corpNum", corpNum);
//		System.out.println("dao진입전 map : " + map.get("corpNum"));
//		String corpName = corpDAOImpl.readcorp(map);
//		System.out.println(corpName);
//		request.setAttribute("corpName", corpName);
		user_id = principal.getName();
		System.out.println("아이디는 =========> " + user_id);

		String user_num = user2DAOImpl.getUserNum(user_id);
		log.warn("받아온 값 : " + user_num);

		request.setAttribute("corpreview_usernum", user_num);

		request.setAttribute("corpreview_corpnum", corpNum);

		System.out.println(corpNum);

		return "corp/corpreviewwriteform";
	}

	// 기업에 대해 리뷰 작성 양식 받기

	@RequestMapping(value = "/corpreview", method = RequestMethod.POST)
	public String corpreview(CorpreviewDTO dto, HttpServletRequest request) {

		System.out.println("여기오니 ===========> ");
		System.out.println("dto 정보" + dto);
//		request.setAttribute("corpreviewnum", dto.getCorpreview_corpnum());
//		System.out.println("리뷰번호!===>" + request.getAttribute("corpreviewnum"));

		corpDAOImpl.insertReview(dto);
		String str = request.getParameter("corpreview_corpnum");

		return "redirect:corpreviewintro?corpnum="+str;
	}

}