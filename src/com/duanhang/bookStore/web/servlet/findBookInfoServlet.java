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
//		获取来自product_list的id
		String id = req.getParameter("id");
//		处理业务逻辑：
		ProductService pd = new ProductService();
		Product book;
		try {
			book = pd.findBookInfoByid(id);
//		分发转向
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
