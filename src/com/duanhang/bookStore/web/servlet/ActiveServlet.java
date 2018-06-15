package com.duanhang.bookStore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duanhang.bookStore.exception.UserException;
import com.duanhang.bookStore.service.userService;

@WebServlet("/activeServlet")
public class ActiveServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String activeCode = req.getParameter("activeCode");
		userService userService = new userService();
		try {
			userService.activeUser(activeCode);
		} catch (UserException e) {
			e.printStackTrace();
			resp.getWriter().println(e.getMessage());//向客户端打印激活失败的信息
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
		
	}
       
  

}
