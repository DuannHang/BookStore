package com.duanhang.bookStore.service;

import java.sql.SQLException;
import java.util.List;

import com.duanhang.bookStore.dao.ProductDao;
import com.duanhang.bookStore.domain.PageBean;
import com.duanhang.bookStore.domain.Product;

public class ProductService {
	// ����һ��Dao����
	ProductDao productDao = new ProductDao();
/**
 * �����鼮�ķ�ҳ��Ϣ����һҳ ��һҳ ������ҳ��
 * @param currentPage
 * @param pageSize
 * @param category
 * @return
 */
	public PageBean findBooksPage(int currentPage, int pageSize, String category) {
		int count;
		try {
			// �õ��ܼ�¼��
			count = productDao.count(category);
			int totalPage = (int) Math.ceil(count * 1.0 / pageSize); // �����ҳ��
			// ���ص�ǰҳ���鼮��װ��Ϣ
			List<Product> products = productDao.findBooks(currentPage, pageSize, category);

			// ��5��������װ��PageBean�У���Ϊ����ֵ
			PageBean pb = new PageBean();
			pb.setProducts(products);
			pb.setCount(count);
			pb.setCurrentPage(currentPage);
			pb.setPageSize(pageSize);
			pb.setTotalPage(totalPage);
			// ��pageBean��������ԣ����ڵ����һҳ����һҳʱʹ��
			pb.setCategory(category);
			return pb;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
/**
 * �����鼮����Ϣ�������鼮��ϸ���չʾ
 * @param id
 * @return
 * @throws SQLException 
 */
	public Product findBookInfoByid(String id) throws SQLException {
		return productDao.findBookInfoByid(id);
	}

}
