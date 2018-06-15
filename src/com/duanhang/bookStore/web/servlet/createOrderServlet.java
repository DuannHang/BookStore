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
		// 1、封装Order对象
		Order order = new Order();
		try {
			BeanUtils.populate(order, request.getParameterMap());
			order.setId(UUID.randomUUID().toString());// 订单编号随机生成的
			order.setUser((User) request.getSession().getAttribute("user"));// 把session对象中的用户信息保存到order对象中
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 2、获取session对象中的购物车数据
		Map<Product, String> cart = (Map<Product, String>) request.getSession().getAttribute("cart");

		// 3、遍历购物车中的书籍数据，添加到orderItem对象中，同时把多个orderItem添加到list集合中
		List<OrderItem> list = new ArrayList<OrderItem>();
		for (Product p : cart.keySet()) {
			OrderItem oi = new OrderItem();
			oi.setOrder(order);// 把Order对象添加到OrderItem中
			oi.setP(p); // 把购物车中的商品对象添加到OrderItem中
			oi.setBuynum(Integer.parseInt(cart.get(p)));// 购物车中的商品数量
			list.add(oi);// 把每个定单项添加到集合中
		}

		// 4、把集合放入到Order对象中
		order.setOrderItems(list);

		// 调用 业务逻辑
		OrderService os = new OrderService();
		os.addOrder(order);

		//分发转向
		request.setAttribute("orderid",order.getId());
		request.setAttribute("money", order.getMoney());
		request.getRequestDispatcher("/pay.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
