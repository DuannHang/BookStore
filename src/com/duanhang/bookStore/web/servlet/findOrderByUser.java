package com.duanhang.bookStore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duanhang.bookStore.dao.userDao;
import com.duanhang.bookStore.domain.Order;
import com.duanhang.bookStore.domain.User;
import com.duanhang.bookStore.service.OrderService;

//		订单信息查询的servlet
@WebServlet("/findOrderByUser")
public class findOrderByUser extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		OrderService os = new OrderService();
		List<Order> orders = os.findOrdersByUserId(user.getId());
		if(orders==null) {
			request.setAttribute("error_msg", "订单表空空如也啊！");
		}
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
