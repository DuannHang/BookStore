package com.duanhang.bookStore.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duanhang.bookStore.dao.ProductDao;

@WebServlet("/findProductName")
public class findProductName extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
//		name = new String(name.getBytes("iso-8859-1"),"UTF-8");
		ProductDao pd = new ProductDao();
		try {
			List<Object>list = pd.findBookByName(name);
//			System.out.println("list的输出："+list);
			//把集合中的数据转换为字符串返回到网页
			String str = "";
			for (int i = 0; i < list.size(); i++) {
				if(i>0){
					str+=",";
				}
				str+=list.get(i);
			}
			
//			System.out.println(str);
			//把数据响应到客户端
			response.getWriter().write(str);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
