package client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import server.ChatServer;
import server.GetUP;
import util.ParUtil;

public class Login extends ParUtil implements ActionListener{
	
	String status;//接收从服务器传回来的状态
	
	//文本域
		JTextField msgUsername;
		JTextField msgPassword;
		
		/**
		 * 
		 */
		public Login(){
			mainJFrame = new JFrame("客户登录端");
			
			container = mainJFrame.getContentPane();
			
			msgUsername = new JTextField(30);
			msgUsername.addActionListener(this);
			msgUsername.setText("用户名");
			
			sentButton = new JButton("登录");
			sentButton.addActionListener(this);
			
			msgPassword = new JTextField(30);
			msgPassword.addActionListener(this);
			msgPassword.setText("密码");
			
			panel = new JPanel();
			panel.setLayout(new FlowLayout());
			panel.add(msgUsername);
			panel.add(sentButton);
			panel.add(msgPassword);
			
			
			container.add(panel,BorderLayout.CENTER);
			
			mainJFrame.setSize(500, 400);
			mainJFrame.setVisible(true);
			mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			try {
				connectToClientSocket = new Socket("localhost",9998);
				inFromClient = new DataInputStream(connectToClientSocket.getInputStream());
				outToClient = new DataOutputStream(connectToClientSocket.getOutputStream());
				System.out.println("1111");
				
				while(true){
					System.out.println(inFromClient.readUTF());
					new ChatClient(msgUsername.getText());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
		}
		
		public static void main(String[] args) {
			new Login();
		}

	

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				outToClient.writeUTF(msgUsername.getText());
				outToClient.writeUTF(msgPassword.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
}













