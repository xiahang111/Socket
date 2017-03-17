package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JFrame;

import util.ParUtil;

public class LoginServer extends ParUtil{
	
	
	public LoginDB serverDB;
	
	
	public LoginServer(){
		//设置界面，包含容器
				mainJFrame = new JFrame("用户登录（服务器端）");
				container = mainJFrame.getContentPane();
				
				//主界面
				mainJFrame.setSize(500, 400);
				mainJFrame.setVisible(true);
				mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			serverSocket = new ServerSocket(9998);
			connectToClientSocket = serverSocket.accept();
			inFromClient = new DataInputStream(connectToClientSocket.getInputStream());
			outToClient = new DataOutputStream(connectToClientSocket.getOutputStream());
			
			String username = inFromClient.readUTF();
			String password = inFromClient.readUTF();
			System.out.println(username+"~~~"+password);
			
			serverDB = new LoginDB();
			
			String 	i = serverDB.login(username, password);
			
			
			System.out.println(i);
			outToClient.writeUTF(i);
			outToClient.flush();
			//new ChatServer();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new LoginServer();
	}
	
	
	

	

	
	

	
	
}
