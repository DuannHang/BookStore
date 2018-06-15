package com.duanhang.bookStore.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duanhang.bookStore.domain.Product;
import com.duanhang.bookStore.service.ProductService;

@WebServlet("/findBookInfoServlet")
public class findBookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		��ȡ����product_list��id
		String id = req.getParameter("id");
//		����ҵ���߼���
		ProductService pd = new ProductService();
		Product book;
		try {
			book = pd.findBookInfoByid(id);
//		�ַ�ת��
			req.setAttribute("book",book);
			req.getRequestDispatcher("/product_info.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
 
}
