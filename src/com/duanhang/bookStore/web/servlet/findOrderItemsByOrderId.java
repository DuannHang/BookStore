package com.duanhang.bookStore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duanhang.bookStore.domain.Order;
import com.duanhang.bookStore.service.OrderService;

@WebServlet("/findOrderItemsByOrderId")
public class findOrderItemsByOrderId extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		������������
		String orderid = request.getParameter("orderid");
//		����ҵ���߼�
		OrderService os = new OrderService();
		Order order = os.findOrdersByOrderId(orderid);
		
//			�ַ�ת��
		request.setAttribute("order", order);
		request.getRequestDispatcher("/orderInfo.jsp").forward(request, response);

	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
