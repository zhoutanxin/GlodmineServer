package com.doadway.framework.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class DBConnection {
	public static DBConnection DB_CONNECTION=getInstance();
	
	private SqlSessionTemplate st;
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/config/dwcms-servlet-admin.xml");
		st =(SqlSessionTemplate)ac.getBean("sqlSession");
	}	
	private Connection connection = SqlSessionUtils.getSqlSession(
            st.getSqlSessionFactory(), st.getExecutorType(),
            st.getPersistenceExceptionTranslator()).getConnection();
	
	private DBConnection() { }
	public static DBConnection getInstance(){
	        if(null == DB_CONNECTION){
	        	DB_CONNECTION = new DBConnection();
	        }
	        return DB_CONNECTION;
	 }
	    


	
	
	public SqlSessionTemplate getSt() {
		return st;
	}
	public void setSt(SqlSessionTemplate st) {
		this.st = st;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public void  execute(){
		try {
			PreparedStatement preStatement = DBConnection.DB_CONNECTION.getConnection().prepareStatement("show columns from dc_channel_txt;");
			System.out.println(preStatement.execute());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void  showColumnsFromChannelTxt(){
		try {
			String showSql="show columns from dc_content_txt;";
			PreparedStatement preStatement = DBConnection.DB_CONNECTION.getConnection().prepareStatement(showSql);
			ResultSet rs = preStatement.executeQuery(showSql);
			while(rs.next()){
				String s = rs.getString(1);
				System.out.println(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
