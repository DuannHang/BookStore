package com.duanhang.bookStore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.duanhang.bookStore.domain.Order;
import com.duanhang.bookStore.domain.OrderItem;
import com.duanhang.bookStore.domain.Product;
import com.duanhang.bookStore.utils.C3P0Util;
import com.duanhang.bookStore.utils.ManagerThreadLocal;

public class ProductDao {
	C3P0Util c3p0util = new C3P0Util();

	/**
	 * 查询catergery的总记录数
	 * 
	 * @param category
	 * @return
	 * @throws SQLException
	 */
	public int count(String category) throws SQLException {
		QueryRunner qr = new QueryRunner(c3p0util.dataSource);
		String sql = "select count(*) from products";
		// 如果category不是空，就把条件加上
		if (!"".equals(category)) {
			sql += " where category='" + category + "'";
		}
		long l = (Long) qr.query(sql, new ScalarHandler(1));
		return (int) l;
	}

	/**
	 * 查找每一页展示的书籍（分页查找书籍的信息）
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param category
	 * @return
	 * @throws SQLException
	 */
	public List<Product> findBooks(int currentPage, int pageSize, String category) throws SQLException {
		QueryRunner qr = new QueryRunner(c3p0util.dataSource);
		String sql = "select * from products where 1=1";
		List list = new ArrayList();
		if (!"".equals(category)) {
			sql += " and category=?";
			list.add(category);
		}
		sql += " limit ?,?";
		// select * from products where 1=1 and category=? limit ?,?;
		list.add((currentPage - 1) * pageSize);
		list.add(pageSize);

		return qr.query(sql, new BeanListHandler<Product>(Product.class), list.toArray());
	}

	/**
	 * 根據id查找书籍的信息
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Product findBookInfoByid(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(c3p0util.dataSource);
		String sql = "select * from products where id=?";
		return qr.query(sql, new BeanHandler<Product>(Product.class), id);
	}

	/**
	 * 修改库存书籍的数量
	 * 
	 * @param order
	 * @throws SQLException 
	 */
	public void updateProductNum(Order order) throws SQLException {
		List<OrderItem> orderItems = order.getOrderItems();
		QueryRunner qr = new QueryRunner();

		Object[][] params = new Object[orderItems.size()][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[] { orderItems.get(i).getBuynum(), orderItems.get(i).getP().getId() };
		}
		qr.batch(ManagerThreadLocal.getConnection(), "UPDATE products SET pnum=pnum-? WHERE id=?", params);

	}

	/**
	 * 搜索标签中根据搜索内容返回书的名字集合
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public List<Object> findBookByName(String name) throws SQLException {
		QueryRunner qr = new QueryRunner(c3p0util.dataSource);
//		String sql = "select name from products where name=?";
//		String name1="%"+name+"%";
		 return qr.query("select name from products where name like ?", new ColumnListHandler(),"%"+name+"%");
	}

}
