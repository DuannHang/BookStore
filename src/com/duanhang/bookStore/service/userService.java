package com.duanhang.bookStore.service;

import java.sql.SQLException;

import com.duanhang.bookStore.dao.userDao;
import com.duanhang.bookStore.domain.User;
import com.duanhang.bookStore.exception.UserException;
import com.duanhang.bookStore.utils.SendJMail;

public class userService {

	userDao ud = new userDao();

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void register(User user) throws Exception {
		try {
			ud.addUser(user);
			String emailMsg = "<h3>这是一封来自Duann.写的BookStore的注册邮件，恭喜您注册成功了！</h3><br>"
					+ "<h3>请<a href='http://www.product.com/activeServlet?activeCode=" + user.getActiveCode()
					+ "'>激活</a>后登陆。</h3>";
			SendJMail.sendMail(user.getEmail(), emailMsg);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("注册失败！");
		}
	}

	/**
	 * 用户激活
	 * 
	 * @param activeCode
	 * @throws UserException
	 */
	public void activeUser(String activeCode) throws UserException {
		User user = new User();
		try {
			user = ud.findUserByActiveCode(activeCode);
			// 根据激活码查找用户
			if (user != null) {
				ud.activeCode(activeCode);
				return;
			}
			throw new UserException("激活失败！");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("激活失败！");
		}

	}

	/**
	 * 用户登录
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
				throw new UserException("该用户不存在！");
			}
			if (user.getState() == 0) {
				throw new UserException("该用户还没有激活！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @throws SQLException 
	 */
	public void modifyUserinfo(User user) throws SQLException {
		ud.modifyUserinfo(user);
		
	}

}
