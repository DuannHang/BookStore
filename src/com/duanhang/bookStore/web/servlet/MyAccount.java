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
//		��ȡsession�е�user����
		User user = (User) req.getSession().getAttribute("user");
//		����ҵ��˻���������Ѿ���¼�ˣ�����ת��myAccount��
//		����ǹ���Ա������ת�������������
//		�����û�е�¼������ת��login.jsp
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
