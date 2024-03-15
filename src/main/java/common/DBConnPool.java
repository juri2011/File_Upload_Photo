package common;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
	public Connection con;
	public PreparedStatement pstmt;
	
	public DBConnPool() {
		Context initCtx;
		try {
			initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource source = (DataSource) ctx.lookup("jdbc/oracle");
			
			con = source.getConnection();
			
			System.out.println("Connection Pool 생성!");
			
		} catch (Exception e) {
			System.out.println("Connection Pool 연결 실패");
			e.printStackTrace();
		}
	}
}
