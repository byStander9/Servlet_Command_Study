package com.sw.command;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;

public class InsertProdImpl implements Service {
	@Override
	public int insertProd(MemberDto mdto) throws ServletException, IOException {
		MemberDao mdao = new MemberDaoImpl();
		int returnVal = mdao.insertProd(mdto);
		
		return returnVal;
	}
	public ResultSet searchProd(String type, String info) throws ServletException, IOException {
		
		return null;
	}
	public int changePrise(String name, String price) throws ServletException, IOException {
		
		return 1;
	}
}
