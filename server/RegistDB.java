package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegistDB {
	
	public String Regist(String username,String password){
		
		String i = "注册失败！请重试";
		int result;
		
		Connection con = null;
		PreparedStatement ps;
		ResultSet rs;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql:///socket?user=root&password=root");
			
			String sql = "INSERT INTO user_socket VALUES(?,?)";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1,username);
			ps.setString(2,password);
			
			result = ps.executeUpdate();
			
			if(result != 0){
				System.out.println("注册成功");
				i = "注册成功！请登录";
				return i;
				
			}else{
				i="注册失败！请重试";
				return i;
			}
		
		} catch (Exception e) {
			
			return i;
		}
	}

}
