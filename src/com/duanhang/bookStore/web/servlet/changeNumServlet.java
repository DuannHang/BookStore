package com.duanhang.bookStore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.duanhang.bookStore.domain.Product;

/**
 * Servlet implementation class changeNumServlet
 */
@WebServlet("/changeNumServlet")
public class changeNumServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String num = request.getParameter("num");
		//注：只能重写id的hashcode
		Product b = new Product();
		b.setId(id);
		
		HttpSession session = request.getSession();
		Map<Product, String> cart = (Map<Product, String>) session.getAttribute("cart");
		//如果商品数据为0，就删除对象
		if("0".equals(num)){
			cart.remove(b);
		}
		//判断如果找到与id相同的书，
		if(cart.containsKey(b)){
			cart.put(b, num);
		}
		
//		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
		

		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeNumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

}
