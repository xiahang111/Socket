package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;

import javax.swing.JFrame;

import util.ParUtil;

public class RegistServer extends ParUtil{
	
	private RegistDB registDB;
	
	public RegistServer(){
		//设置界面，包含容器
		mainJFrame = new JFrame("用户注册（服务器端）");
		container = mainJFrame.getContentPane();
		
		//主界面
		mainJFrame.setSize(500, 400);
		mainJFrame.setVisible(true);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			serverSocket = new ServerSocket(9997);
			connectToClientSocket = serverSocket.accept();
			inFromClient = new DataInputStream(connectToClientSocket.getInputStream());
			outToClient = new DataOutputStream(connectToClientSocket.getOutputStream());
	
			String username = inFromClient.readUTF();
			String password = inFromClient.readUTF();
			System.out.println(username+"~~~"+password);
	
			registDB = new RegistDB();
	
			String 	i = registDB.Regist(username, password);
	
	
			System.out.println(i);
			outToClient.writeUTF(i);
			outToClient.flush();
			//new ChatServer();
	
		} catch (Exception e) {
			e.printStackTrace();
			}
	}

	public static void main(String[] args) {
		new RegistServer();
	}
}


