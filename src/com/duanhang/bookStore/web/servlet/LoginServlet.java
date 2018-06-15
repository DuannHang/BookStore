package com.duanhang.bookStore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duanhang.bookStore.domain.User;
import com.duanhang.bookStore.exception.UserException;
import com.duanhang.bookStore.service.userService;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		userService us = new userService();
		try {
			User user = us.login(username,password);
//			登录成功，跳转到主页
			String path = "/index.jsp";
			if("admin".equals(user.getRole())) {
				path = "/admin/login/home.jsp";
			}
			req.getSession().setAttribute("user", user);
			req.getRequestDispatcher(path).forward(req, resp);
		} catch (UserException e) {
//			e.printStackTrace();
//			登录失败，调回到登录界面
			req.setAttribute("user_msg", e.getMessage());
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	

}
