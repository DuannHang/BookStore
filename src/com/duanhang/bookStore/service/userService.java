package com.duanhang.bookStore.service;

import java.sql.SQLException;

import com.duanhang.bookStore.dao.userDao;
import com.duanhang.bookStore.domain.User;
import com.duanhang.bookStore.exception.UserException;
import com.duanhang.bookStore.utils.SendJMail;

public class userService {

	userDao ud = new userDao();

	/**
	 * �û�ע��
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void register(User user) throws Exception {
		try {
			ud.addUser(user);
			String emailMsg = "<h3>����һ������Duann.д��BookStore��ע���ʼ�����ϲ��ע��ɹ��ˣ�</h3><br>"
					+ "<h3>��<a href='http://www.product.com/activeServlet?activeCode=" + user.getActiveCode()
					+ "'>����</a>���½��</h3>";
			SendJMail.sendMail(user.getEmail(), emailMsg);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("ע��ʧ�ܣ�");
		}
	}

	/**
	 * �û�����
	 * 
	 * @param activeCode
	 * @throws UserException
	 */
	public void activeUser(String activeCode) throws UserException {
		User user = new User();
		try {
			user = ud.findUserByActiveCode(activeCode);
			// ���ݼ���������û�
			if (user != null) {
				ud.activeCode(activeCode);
				return;
			}
			throw new UserException("����ʧ�ܣ�");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("����ʧ�ܣ�");
		}

	}

	/**
	 * �û���¼
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws UserException
	 */
	public User login(String username, String password) throws UserException {
		User user = null;
		try {
			user = ud.fingUserByUsernameAndPassword(username, password);
			if (user == null) {
				throw new UserException("���û������ڣ�");
			}
			if (user.getState() == 0) {
				throw new UserException("���û���û�м��");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}

	/**
	 * �޸��û���Ϣ
	 * 
	 * @param user
	 * @throws SQLException 
	 */
	public void modifyUserinfo(User user) throws SQLException {
		ud.modifyUserinfo(user);
		
	}

}
