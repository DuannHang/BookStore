package com.duanhang.bookStore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.duanhang.bookStore.domain.User;
import com.duanhang.bookStore.utils.C3P0Util;

public class userDao {
	C3P0Util c3p0util = new C3P0Util();

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @throws SQLException
	 */
	public void addUser(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(c3p0util.dataSource);
		String sql = "INSERT INTO USER(username,PASSWORD,gender,email,telephone,introduce,activecode,state,registtime) "
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		qr.update(sql, user.getUsername(), user.getPassword(), user.getGender(), user.getEmail(), user.getTelephone(),
				user.getIntroduce(), user.getActiveCode(), user.getState(), user.getRegistTime());
	}

	/**
	 * 根据激活码查找用户
	 * 
	 * @param activeCode
	 * @return
	 * @throws SQLException
	 */
	public User findUserByActiveCode(String activeCode) throws SQLException {
		QueryRunner qr = new QueryRunner(c3p0util.dataSource);
		String sql = "select * from user where activeCode=?";
		return qr.query(sql, new BeanHandler<User>(User.class), activeCode);

	}

	/**
	 * 激活用户
	 * 
	 * @param activeCode
	 * @throws SQLException
	 */
	public void activeCode(String activeCode) throws SQLException {
		QueryRunner qr = new QueryRunner(c3p0util.dataSource);
		String sql = "update user set state=1 where activecode=?";
		qr.update(sql, activeCode);

	}

	/**
	 * 根据名称和密码查找用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	public User fingUserByUsernameAndPassword(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(c3p0util.dataSource);
		String sql = "select * from user where username=? and password=?";
		return qr.query(sql, new BeanHandler<User>(User.class), username,password);
	
	}
/**
 * 修改用户信息
 * @param user
 * @throws SQLException 
 */
	public void modifyUserinfo(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(c3p0util.dataSource);
		String sql = "update user set password=?,gender=?,telephone=? where id=?";
		qr.update(sql, user.getPassword(),user.getGender(),user.getTelephone(),user.getId());
		
	}

}
