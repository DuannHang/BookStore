package com.duanhang.bookStore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duanhang.bookStore.domain.PageBean;
import com.duanhang.bookStore.service.ProductService;

@WebServlet("/showProductByPage")
public class showProductByPage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取页面的category值
		String category = req.getParameter("category");
		if (category == null) {
			category = "";
		}
		int pageSize = 4;

		int currentPage = 1;// 当前页
		String currPage = req.getParameter("currentPage");// 从上一页或下一页得到的数据
		if (currPage != null && !"".equals(currPage)) {// 第一次访问资源时，currPage可能是null
			currentPage = Integer.parseInt(currPage);
		}
		
		ProductService bs = new ProductService();
		// 分页查询，并返回PageBean对象
		PageBean pb = bs.findBooksPage(currentPage, pageSize, category);

		req.setAttribute("pb", pb);
		req.getRequestDispatcher("/product_list.jsp").forward(req, resp);


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
