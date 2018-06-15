package com.duanhang.bookStore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.mbeans.UserMBean;
import org.apache.commons.beanutils.BeanUtils;

import com.duanhang.bookStore.domain.User;
import com.duanhang.bookStore.exception.UserException;
import com.duanhang.bookStore.service.userService;


@WebServlet("/modifyuserinfo")
public class modifyuserinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		��ȡsession�е����ݣ�Ȼ���ó������ص�jsp��
	User user = new User();
	userService us = new userService();
	try {
		BeanUtils.populate(user, req.getParameterMap());
		us.modifyUserinfo(user);
		req.getSession().invalidate();
		resp.sendRedirect(req.getContextPath()+"/modifyUserInfoSuccess.jsp");
	} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().println("�޸��û���Ϣʧ����");
			req.getRequestDispatcher("/modifyuserinfo.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
 
}
