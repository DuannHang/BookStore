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
		// ��ȡ������
		String id = request.getParameter("id");
		// ����ҵ���߼�
		ProductService bs = new ProductService();

		
		try {
			Product book = bs.findBookInfoByid(id);
			// ��session�еĹ��ﳵȡ�� ��
			HttpSession session = request.getSession();
//			product:�鼮bean string:�鼮�ĸ���
			Map<Product, String> cart = (Map<Product, String>) session.getAttribute("cart");
			int num = 1;
			// ����ǵ�һ�η��ʣ�û�й��ﳵ�������Ǿʹ��� һ�����ﳵ����
			if (cart == null) {
				cart = new HashMap<Product, String>();

			}
			// �鿴��ǰ�������Ƿ����book�Ȿ��,����оͰ�����ȡ������1;
			if (cart.containsKey(book)) {
				num = Integer.parseInt(cart.get(book)) + 1;
			}
			// ��ͼ����빺�ﳵ
			cart.put(book, num + "");

			// ��cart����Żص�session��������
			session.setAttribute("cart", cart);

			// �ַ�ת��
			out.print("<a href='" + request.getContextPath() + "/showProductByPage'>��������</a>��<a href='" + request.getContextPath()
					+ "/cart.jsp'>�鿴���ﳵ</a>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
