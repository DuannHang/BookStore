package com.duanhang.bookStore.service;

import java.sql.SQLException;
import java.util.List;

import com.duanhang.bookStore.dao.ProductDao;
import com.duanhang.bookStore.domain.PageBean;
import com.duanhang.bookStore.domain.Product;

public class ProductService {
	// 创建一个Dao对象
	ProductDao productDao = new ProductDao();
/**
 * 查找书籍的分页信息（上一页 下一页 共多少页）
 * @param currentPage
 * @param pageSize
 * @param category
 * @return
 */
	public PageBean findBooksPage(int currentPage, int pageSize, String category) {
		int count;
		try {
			// 得到总记录数
			count = productDao.count(category);
			int totalPage = (int) Math.ceil(count * 1.0 / pageSize); // 求出总页数
			// 返回当前页的书籍封装信息
			List<Product> products = productDao.findBooks(currentPage, pageSize, category);

			// 把5个变量封装到PageBean中，做为返回值
			PageBean pb = new PageBean();
			pb.setProducts(products);
			pb.setCount(count);
			pb.setCurrentPage(currentPage);
			pb.setPageSize(pageSize);
			pb.setTotalPage(totalPage);
			// 在pageBean中添加属性，用于点击上一页或下一页时使用
			pb.setCategory(category);
			return pb;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
/**
 * 查找书籍的信息，用于书籍详细情况展示
 * @param id
 * @return
 * @throws SQLException 
 */
	public Product findBookInfoByid(String id) throws SQLException {
		return productDao.findBookInfoByid(id);
	}

}
