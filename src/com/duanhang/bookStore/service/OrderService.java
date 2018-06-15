package com.duanhang.bookStore.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.duanhang.bookStore.dao.OrderDao;
import com.duanhang.bookStore.dao.OrderItemDao;
import com.duanhang.bookStore.dao.ProductDao;
import com.duanhang.bookStore.domain.Order;
import com.duanhang.bookStore.domain.OrderItem;
import com.duanhang.bookStore.domain.Product;
import com.duanhang.bookStore.exception.OrderException;
import com.duanhang.bookStore.utils.C3P0Util;
import com.duanhang.bookStore.utils.ManagerThreadLocal;
import com.mysql.jdbc.ResultSet;

public class OrderService {

	OrderDao orderDao = new OrderDao();
	OrderItemDao orderItemDao = new OrderItemDao();
	ProductDao productDao = new ProductDao();

	/**
	 * ��Ӷ���
	 * 
	 * @param order
	 */
	public void addOrder(Order order) {
		try {
			// �������������������ﰲȫ�����⡣�������������ɹ�����Ȼ��ʧ��
			ManagerThreadLocal.startTransacation();
			orderDao.addOrder(order);
			orderItemDao.addOrderItem(order);
			productDao.updateProductNum(order);
			ManagerThreadLocal.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			ManagerThreadLocal.rollback();
		}
	}

	/**
	 * �����û���id��ѯorders����
	 * 
	 * @param id
	 * @return
	 */
	public List<Order> findOrdersByUserId(int id) {
		try {
			return orderDao.findOrders(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ���ݶ���orderid��ѯ������Ϣ
	 * 
	 * @param orderid
	 * @return
	 * @throws SQLException 
	 */
	public Order findOrdersByOrderId(String orderid) {
		try {
			return orderDao.findOrdersByOrderId(orderid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void modifyOrderState(String orderid) throws OrderException {
		try {
			orderDao.modifyOrderState(orderid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("�޸�ʧ��");
		}
}

}
