package com.douzone.blah.controller;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.douzone.blah.dao.User2DAO;
import com.douzone.blah.model.User2DTO;

@Controller
public class UserController {

  @Resource
  private User2DAO user2;

  @RequestMapping("/admin")
  public String adminHandler(HttpServletRequest request) {


      return "admin/admin";
  }


  @RequestMapping("/admin/edit")
  public String list(HttpServletRequest request) {

    HashMap map = new HashMap();

    List<User2DTO> list = user2.getAllUserList(map);
    request.setAttribute("list", list);

    return "admin/editMember";
  }
  @RequestMapping("/admin/edit/delete")
  public String delete(HttpServletRequest request,User2DTO dto) {
    String num = request.getParameter("num");
    int cnt =user2.deleteUser2(Integer.parseInt(num));
    String res = "redirect:/admin/edit";
    if(cnt ==0) {
      res = "fail";
    }
    return res;
  }

}
