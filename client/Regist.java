package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.ParUtil;

public class Regist extends ParUtil implements ActionListener{
	//文本域
			JTextField msgUsername;
			JTextField msgPassword;
			
	public Regist(){
		mainJFrame = new JFrame("客户注册端");
		
		container = mainJFrame.getContentPane();
		
		//注册信息展示框
			showTextArea = new JTextArea();
			showTextArea.setEditable(false);//不可自动编辑
			showTextArea.setLineWrap(true);//自动换行
			JSPane = new JScrollPane(showTextArea);
		
		msgUsername = new JTextField(30);
		msgUsername.addActionListener(this);
		msgUsername.setText("用户名");
		
		sentButton = new JButton("注册");
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
		container.add(JSPane,BorderLayout.SOUTH);
		
		
		mainJFrame.setSize(500, 400);
		mainJFrame.setVisible(true);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		try {
			connectToClientSocket = new Socket("localhost",9997);
			inFromClient = new DataInputStream(connectToClientSocket.getInputStream());
			outToClient = new DataOutputStream(connectToClientSocket.getOutputStream());
			
			while(true){
				showTextArea.append(inFromClient.readUTF());
			}
			
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		new Regist();
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
