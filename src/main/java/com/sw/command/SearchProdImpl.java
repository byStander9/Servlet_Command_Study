package com.sw.command;

import java.sql.*;

import java.io.IOException;

import javax.servlet.ServletException;

public class SearchProdImpl implements Service {
	public int insertProd(MemberDto mdto) throws ServletException, IOException {
		
		return 1;
	}
	public ResultSet searchProd(String type, String info) throws ServletException, IOException {
		MemberDao mdao = new MemberDaoImpl();
		ResultSet resultSet = null;
		resultSet = mdao.searchProd(type, info);
		return resultSet;
	}
	public int changePrise(String name, String price) throws ServletException, IOException {
		
		return 1;
	}
}
