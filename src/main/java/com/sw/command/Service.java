package com.sw.command;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;

public interface Service {
	public int insertProd(MemberDto mdto) throws ServletException, IOException;
	public ResultSet searchProd(String type, String info) throws ServletException, IOException;
	public int changePrise(String name, String price) throws ServletException, IOException;
}
