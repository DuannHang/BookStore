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
		// ��ȡҳ���categoryֵ
		String category = req.getParameter("category");
		if (category == null) {
			category = "";
		}
		int pageSize = 4;

		int currentPage = 1;// ��ǰҳ
		String currPage = req.getParameter("currentPage");// ����һҳ����һҳ�õ�������
		if (currPage != null && !"".equals(currPage)) {// ��һ�η�����Դʱ��currPage������null
			currentPage = Integer.parseInt(currPage);
		}
		
		ProductService bs = new ProductService();
		// ��ҳ��ѯ��������PageBean����
		PageBean pb = bs.findBooksPage(currentPage, pageSize, category);

		req.setAttribute("pb", pb);
		req.getRequestDispatcher("/product_list.jsp").forward(req, resp);


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
