package com.duanhang.bookStore.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.duanhang.bookStore.domain.Order;
import com.duanhang.bookStore.domain.OrderItem;
import com.duanhang.bookStore.domain.Product;
import com.duanhang.bookStore.domain.User;
import com.duanhang.bookStore.service.OrderService;

/**
 * Servlet implementation class createOrderServlet
 */
@WebServlet("/createOrderServlet")
public class createOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1����װOrder����
		Order order = new Order();
		try {
			BeanUtils.populate(order, request.getParameterMap());
			order.setId(UUID.randomUUID().toString());// �������������ɵ�
			order.setUser((User) request.getSession().getAttribute("user"));// ��session�����е��û���Ϣ���浽order������
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 2����ȡsession�����еĹ��ﳵ����
		Map<Product, String> cart = (Map<Product, String>) request.getSession().getAttribute("cart");

		// 3���������ﳵ�е��鼮���ݣ����ӵ�orderItem�����У�ͬʱ�Ѷ��orderItem���ӵ�list������
		List<OrderItem> list = new ArrayList<OrderItem>();
		for (Product p : cart.keySet()) {
			OrderItem oi = new OrderItem();
			oi.setOrder(order);// ��Order�������ӵ�OrderItem��
			oi.setP(p); // �ѹ��ﳵ�е���Ʒ�������ӵ�OrderItem��
			oi.setBuynum(Integer.parseInt(cart.get(p)));// ���ﳵ�е���Ʒ����
			list.add(oi);// ��ÿ�����������ӵ�������
		}

		// 4���Ѽ��Ϸ��뵽Order������
		order.setOrderItems(list);

		// ���� ҵ���߼�
		OrderService os = new OrderService();
		os.addOrder(order);

		//�ַ�ת��
		request.setAttribute("orderid",order.getId());
		request.setAttribute("money", order.getMoney());
		request.getRequestDispatcher("/pay.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}