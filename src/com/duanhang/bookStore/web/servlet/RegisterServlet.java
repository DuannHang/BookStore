package com.duanhang.bookStore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.duanhang.bookStore.domain.User;
import com.duanhang.bookStore.service.userService;

/**
 * @author duanhang
 *
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 验证码检查
		String ckcode = req.getParameter("ckcode");
		String checkcode_session = (String) req.getSession().getAttribute("checkcode_session");
		if (!checkcode_session.equals(ckcode)) {
			req.setAttribute("ckcode_error", "验证码输入错误！");
			req.getRequestDispatcher("/register.jsp").forward(req, resp);

			return;
		}

		// 获取表单数据
		User user = new User();
//		手动设置激活码
		user.setActiveCode(UUID.randomUUID().toString());
		BeanUtils beanUtils = new BeanUtils();
		try {
			beanUtils.populate(user, req.getParameterMap());
			// 处理业务逻辑
			userService service = new userService();
			service.register(user);
			// 分发转向
//			req.getSession().setAttribute("user", user);
			req.getRequestDispatcher("/registersuccess.jsp").forward(req, resp);	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
