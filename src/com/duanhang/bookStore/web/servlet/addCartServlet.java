package com.duanhang.bookStore.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.duanhang.bookStore.domain.Product;
import com.duanhang.bookStore.service.ProductService;

/**
 * Servlet implementation class addCartServlet
 */
@WebServlet("/addCartServlet")
public class addCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// 获取表单数据
		String id = request.getParameter("id");
		// 处理业务逻辑
		ProductService bs = new ProductService();

		
		try {
			Product book = bs.findBookInfoByid(id);
			// 从session中的购物车取出 来
			HttpSession session = request.getSession();
//			product:书籍bean string:书籍的个数
			Map<Product, String> cart = (Map<Product, String>) session.getAttribute("cart");
			int num = 1;
			// 如何是第一次访问，没有购物车对象，我们就创建 一个购物车对象
			if (cart == null) {
				cart = new HashMap<Product, String>();

			}
			// 查看当前集合中是否存在book这本书,如果有就把数据取出来加1;
			if (cart.containsKey(book)) {
				num = Integer.parseInt(cart.get(book)) + 1;
			}
			// 把图书放入购物车
			cart.put(book, num + "");

			// 把cart对象放回到session作用域中
			session.setAttribute("cart", cart);

			// 分发转向
			out.print("<a href='" + request.getContextPath() + "/showProductByPage'>继续购物</a>，<a href='" + request.getContextPath()
					+ "/cart.jsp'>查看购物车</a>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
