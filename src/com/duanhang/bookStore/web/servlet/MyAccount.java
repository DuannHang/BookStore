package com.duanhang.bookStore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duanhang.bookStore.domain.User;

@WebServlet("/MyAccount")
public class MyAccount extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		获取session中的user对象
		User user = (User) req.getSession().getAttribute("user");
//		点击我的账户，如果是已经登录了，则跳转到myAccount，
//		如果是管理员，则跳转到订单管理界面
//		如果还没有登录，则跳转到login.jsp
		String path = "/myAccount.jsp";
		if(user==null) {
			path = "/login.jsp";
		}else if("admin".equals(user.getUsername())) {
			path = "/admin/login/home.jsp";
		}
		req.getRequestDispatcher(path).forward(req, resp);
		
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req, resp);
	}
       
  

}
