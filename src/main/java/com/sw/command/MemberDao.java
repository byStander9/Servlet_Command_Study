package com.sw.command;

import java.sql.*;

public interface MemberDao {
	public int insertProd(MemberDto mdto);
	public ResultSet searchProd(String type, String info);
	public int changeProdPrice(String name, String price);
	public Connection getConnection();
	public void closeConnection(ResultSet set, PreparedStatement pstmt, Connection connection);
}
