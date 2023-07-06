package com.sw.command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDaoImpl implements MemberDao {

	@Override
	public int insertProd(MemberDto mdto) {
		String sql = "insert into inventory (name, category, price) values (?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int returnVal = 0;
		try {
			conn = getConnection();
			System.out.println("connected");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getCategory());
			pstmt.setString(3, mdto.getPrice());
			pstmt.executeUpdate();
			System.out.println("inserted");
			returnVal = 1;
		} catch(SQLException e){
			System.out.println("connection, insert error");
			System.out.println(e.getMessage());
			e.printStackTrace();
			returnVal = -1;
		} finally {
			closeConnection(null, pstmt, conn);
		}
		return returnVal;
	}

	@Override
	public ResultSet searchProd(String type, String info) {
		String sql = null;
		ResultSet resultSet = null;
		
		if(type.equals("name")) {
			sql = "select * from inventory where name = ?";
		} else if(type.equals("category")) {
			sql = "select * from inventory where category = ?";
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			System.out.println("connected");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, info);
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				System.out.println(resultSet.getString("name"));
				System.out.println(resultSet.getString("category"));
				System.out.println(resultSet.getString("price"));
			}
			System.out.println("data searched");
		} catch(SQLException e) {
			System.out.println("connection, insert error");
			System.out.println(e.getMessage());
			e.printStackTrace();
			//리턴값으로 ResultSet타입을 리턴할 게획인데
			//SQLException이 발생한 경우 리턴값을 어떠헤 지정해줘야하는가
		} finally {
			closeConnection(resultSet, pstmt, conn);
			System.out.println("connection closed");
		}
		
		return resultSet;
	}

	@Override
	public int changeProdPrice(String name, String price) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Connection getConnection() {
		Connection conn = null;
		String DBName = "sales";
		String dbURL = "jdbc:mysql://localhost:3306/" + DBName;
		String sslStr = "?useSSL=false";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC driver load success");
			
			conn = DriverManager.getConnection(dbURL+sslStr, "root", "rlsRms5244");
			System.out.println("DB connection success");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver load fail");
		} catch (SQLException e) {
			System.out.println("DB connection fail");
		}
		return conn;
	}

	@Override
	public void closeConnection(ResultSet set, PreparedStatement pstmt, Connection connection) {
		// TODO Auto-generated method stub
		
	}
	
}
