/**
 * Copyright (c) 2010-2013 by ACTS
 * All rights reserved.
 */
package com.itstyle.es.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *@Function: TODO
 *@Class Name: test
 *@Author: lxz
 *@Date: 2013-7-11
 *@Modifications:
 *@Modifier Name; Date; The Reason for Modifying
 *
 */
public class JDBCConnect {

	private static Connection connection;
	public static Connection getConnect(String url, String username, String password) {
		try {
			connection = DriverManager.getConnection(url, username,
					password);
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		return connection;
	}

	public static void closeCon(){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
