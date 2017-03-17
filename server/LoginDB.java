package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import client.Login;


public class LoginDB{
	
	public String login(String username,String password){
		
		String i = "error";
		
		Connection con = null;
		PreparedStatement ps;
		ResultSet rs;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql:///socket?user=root&password=root");
			
			String sql = "select * from user_socket where username = ? and password = ?";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1,username);
			ps.setString(2,password);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				System.out.println("登录成功");
				i = "ok";
				return i;
				
			}else{
				
				return i;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return i;
		}
	}

	
	

}
