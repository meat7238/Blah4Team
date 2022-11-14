package com.douzone.blah.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.douzone.blah.dao.User2DAO;
import com.douzone.blah.model.User2DTO;
import com.douzone.blah.service.UserService;
import com.douzone.blah.service.mail.MailHandler;
import com.douzone.blah.service.mail.TempKey;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UserController {

   @Resource
   private User2DAO user2DAOImpl;

   @Autowired
   UserService userService;

   @Autowired
   JavaMailSender mailSender;

   private BCryptPasswordEncoder passwordEncoder;

   public UserController(BCryptPasswordEncoder passwordEncoder) {
      super();
      this.passwordEncoder = passwordEncoder;
   }

   @RequestMapping("/admin")
   public String adminHandler(HttpServletRequest request) {
      return "admin/admin";
   }

   // 유저 편집 페이지 - 유저목록, 페이징처리, 게시글작성 수 등등
   @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
   public String editList(HttpServletRequest request) {
     int pg = 1;
     String strPg = request.getParameter("pg");

     if (strPg != null) {
       pg = Integer.parseInt(strPg);
     }
     int rowSize = 10;
     int start = (pg * rowSize) - (rowSize - 1);
     int end = pg * rowSize;

     int total = user2DAOImpl.getUserCount(); //총 게시물수
     System.out.println("시작 : "+start +" 끝:"+end);
     System.out.println("글의 수 : "+total);


     int allPage = (int) Math.ceil(total/(double)rowSize); //페이지수
     //int totalPage = total/rowSize + (total%rowSize==0?0:1);
     System.out.println("페이지수 : "+ allPage);

     int block = 3; //한페이지에 보여줄  범위
     int fromPage = ((pg-1)/block*block)+1;  //보여줄 페이지의 시작
     //((1-1)/10*10)
     int toPage = ((pg-1)/block*block)+block; //보여줄 페이지의 끝
     if(toPage> allPage){ // 예) 20>17
         toPage = allPage;
     }
      HashMap map = new HashMap();
      map.put("start", start);
      map.put("end", end);
      List<User2DTO> list = user2DAOImpl.getAllUserList(map);
      List<Integer> ali = new ArrayList<Integer>();
      for(int i=0; i<list.size();i++) {
        System.out.println(list.get(i).getUser_num());
        ali.add(user2DAOImpl.getUserPostCount(list.get(i).getUser_num()));
        System.out.println(ali);
      }
      request.setAttribute("user_countlist", ali);
      request.setAttribute("list", list);
      request.setAttribute("pg",pg);
      request.setAttribute("allPage",allPage);
      request.setAttribute("block",block);
      request.setAttribute("fromPage",fromPage);
      request.setAttribute("toPage",toPage);
      System.out.println("갖고온 리스트는 :"+list);
      return "admin/editMember";
   }
//   권한변경
   @RequestMapping(value = "/admin/edit/authority", method = RequestMethod.GET)
   public String authorityUpdate(HttpServletRequest request) {
     String user_num = request.getParameter("num");
     String authority = request.getParameter("authority");
     Map<String, String> map = new HashMap<>();
     map.put("user_num", user_num);
     map.put("authority", authority);

     int result = user2DAOImpl.updateAuthority(map);
     if (result == 0) {
       return "fail";
     }
     return "redirect:/admin/edit";
   }


   //유저 검색
   @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
   public String editList(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String column = request.getParameter("column");
      String keyvalue = request.getParameter("keyvalue");
      System.out.println(column + " / " + keyvalue);

      Map<String, String> map = new HashMap<>();
      map.put("column", column);
      map.put("keyvalue", keyvalue);

      List<User2DTO> list = user2DAOImpl.getSearchList(map);
      if (list != null) {
         request.setAttribute("list", list);
         System.out.println(list);
         return "admin/editMember";
      } else {
         return "fail";
      }
   }


   // 유저 삭제
   @RequestMapping("/admin/edit/delete")
   public String delete(HttpServletRequest request, User2DTO dto) {
      String num = request.getParameter("num");
      System.out.println(num);
      int cnt = user2DAOImpl.deleteUser2(num);
      String res = "redirect:/admin/edit";
      if (cnt == 0) {
         res = "fail";
      }
      return res;
   }

   //회원탈퇴
   @RequestMapping("/member/delete")
   public String memberDelete(HttpServletRequest request, User2DTO dto) {
      String num = request.getParameter("user_num");
      System.out.println("/member/delete에서의 num값 : "+num);
      int cnt = user2DAOImpl.deleteUser2(num);
      String res = "redirect:/admin/edit";
      if (cnt == 0) {
         res = "fail";
      }
      return res;
   }


   // 회원가입 페이지로 이동
   @RequestMapping("/join")
   public String join() {
      return "join/join";
   }

   // 회원가입 처리
   @PostMapping("/joinAction")
   public String insertUser(@RequestParam("user_id") String user_id,
         @RequestParam("user_password") String user_password,
         @RequestParam("user_password2") String user_password2,
         @RequestParam("user_email") String user_email,
         @RequestParam("user_jobgroup") String user_jobgroup,
         @RequestParam("user_workspace") String user_workspace,
         User2DTO user2dto) throws Exception {

      // 정규표현식 확인 로직
      String patternCheckResult = userService.patternCheck(user2dto);
      if(patternCheckResult.equals("emailError")) return "join/emilError";
      else if(patternCheckResult.equals("IdError")) return "join/idError";
      else if(patternCheckResult.equals("passwordError")) return "join/pwdError";

      // 아이디, 이메일 중복 확인 로직
      if(userService.dupleCheck(user2dto).equals("idDuple")) return "join/idDuple";
      else if(userService.dupleCheck(user2dto).equals("emailDuple")) return "join/emailDuple";

      // 비밀번호 확인 로직
      if(!user_password.equals(user_password2))  return "join/pwdUnmatched";

      // 이메일 인증 키 삽입 로직
      String user_email_key = new TempKey().getKey(false, 30);
      Map<String, String> map = new HashMap<>();
      map.put("user_id", user_id);
      map.put("user_password", passwordEncoder.encode(user_password)); // 비밀번호 암호화
      map.put("user_email", user_email);
      map.put("user_jobgroup", user_jobgroup);
      map.put("user_workspace", user_workspace);
      map.put("user_email_key", user_email_key);

      // 회원가입 완료
      int result = user2DAOImpl.insertUser(map);

      // 이메일 발송
      MailHandler sendMail = new MailHandler(mailSender);
      sendMail.setSubject("[BlaBlah] 현직자 인증 메일입니다");
      sendMail.setText(
            "<h1>BlahBlah 현직자 인증 이메일입니다.</h1>" +
            "<br>회원이 되신 걸 환영합니다!" +
            "<br>아래 이메일 인증 확인을 눌러 현직자 인증 절차를 완료해주세요." +
            "<br>인증이 완료되면 사이트를 이용하실 수 있습니다." +
            "<br><br><a href='http://localhost:8080/blah/join/registerEmail?user_email=" + user_email +
            "&user_email_key=" + user_email_key +
            "'target='_balnk'>이메일 인증 확인</a>");
            System.out.println(user_email_key);
      sendMail.setForm("blahblahteammanager@gmail.com", "블라블라");
      sendMail.setTo(user_email);
      sendMail.send();
      log.warn("메일 전송 완료 ******** " + user_email);
      return "/join/joinSuccess";

   }

   // 이메일 인증 후
   @GetMapping("/join/registerEmail")
   public String verify(@RequestParam Map<String, Object> map) throws Exception {
      log.warn(map);
      user2DAOImpl.updateMailAuth(map);
      return "redirect:/";
   }

   // 로그인 페이지 요청
   @RequestMapping("/loginForm")
   public String loginView(HttpServletRequest request) {

      // 요청 시점의 사용자 URI 정보를 Session의 Attribute에 담아서 전달(잘 지워줘야 함)
      // 로그인이 틀려서 다시 하면 요청 시점의 URI가 로그인 페이지가 되므로 조건문 설정
      String uri = request.getHeader("Referer");
      if (!uri.contains("/login/loginForm")) {
         request.getSession().setAttribute("prevPage", request.getHeader("Referer"));
      }
      return "/login/loginForm";
   }


   // 마이페이지 회원 정보 페이지로 이동, 회원 정보 조회
   @GetMapping("/member")
   public String memberInfoSelect(Principal principal, HttpServletRequest request, User2DTO user2dto) {
      String user_id = principal.getName();

      Map<String, Object> userInfoMap = user2DAOImpl.showMemberInfo(user_id);
      log.warn(userInfoMap);
      request.setAttribute("user_id", userInfoMap.get("USER_ID"));
      request.setAttribute("user_point", userInfoMap.get("USER_POINT"));
      request.setAttribute("user_email", userInfoMap.get("USER_EMAIL"));
      request.setAttribute("user_startdate", userInfoMap.get("USER_STARTDATE"));
      request.setAttribute("user_enddate", userInfoMap.get("USER_ENDDATE"));
      request.setAttribute("user_jobgroup", userInfoMap.get("USER_JOBGROUP"));
      request.setAttribute("user_workspace", userInfoMap.get("USER_WORKSPACE"));
      return "member/member";
   }

   // 마이페이지 기본 회원 정보 수정 처리
   // 비밀번호 반드시 암호화!
   @PostMapping("/member-edit")
   public String memberInfoUpdate(@RequestParam("user_id") String user_id,
         @RequestParam("user_password") String user_password,
         @RequestParam("user_password2") String user_password2,
         User2DTO user2dto) {

	  if(!user_password.equals(user_password2)) return "join/pwdUnmatched";

	  user2dto.setUser_password(user_password);
	  if(!userService.passwordPatternCheck(user2dto)) return "join/pwdError";


      Map<String, String> userInfoMap = new HashMap<String, String>();
      userInfoMap.put("user_id", user_id);
      userInfoMap.put("user_password", passwordEncoder.encode(user_password));

      log.warn(userInfoMap);
      int result = user2DAOImpl.editMemberInfo(userInfoMap);
      if (result == 1)
         log.warn(userInfoMap);
      return "redirect:/member";
   }

}