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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.ParUtil;

public class ChatClient extends ParUtil implements ActionListener,Runnable{
	
	public ChatClient(String username){
		//设置界面，包含容器
		mainJFrame = new JFrame("欢迎您，"+username+"用户");
		container = mainJFrame.getContentPane();
		
		//聊天信息展示框
		showTextArea = new JTextArea();
		showTextArea.setEditable(false);//不可自动编辑
		showTextArea.setLineWrap(true);//自动换行
		JSPane = new JScrollPane(showTextArea);
		
		
		msgTextField = new JTextField();
		msgTextField.setColumns(30);//输入框长度
		msgTextField.addActionListener(this);
		
		//发送按键
		sentButton = new JButton("发送");
		sentButton.addActionListener(this);
		//画笔在嵌板内画出聊天信息输入框和发送按钮
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(msgTextField);
		panel.add(sentButton);
		
		//容器包含聊天信息展示框和嵌板
		container.add(JSPane, BorderLayout.CENTER);
		container.add(panel,BorderLayout.SOUTH);
		
		//主界面
		mainJFrame.setSize(500, 400);
		mainJFrame.setVisible(true);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//套接字编程，监听客户端
		try {
			//创建服务器套接字连接服务器
			
            connectToClientSocket = new Socket("localhost",9999);
            
			inFromClient = new DataInputStream(connectToClientSocket.getInputStream());
			
			outToClient = new DataOutputStream(connectToClientSocket.getOutputStream());
			
			showTextArea.append("连接成功，可以发送消息了"+getTime()+"\n");
			
		  
			
		} catch (Exception e) {
			showTextArea.append("对不起，不能与服务器创建连接"+getTime());
			msgTextField.setEditable(false);//不可编辑
			msgTextField.setEnabled(false);//不可见
		}
		
	}
	
	public static void main(String[] args) {
		new ChatClient("夏威");
	}
	
	
	@Override
	public void run() {
		try {
			while(true){
				showTextArea.append("吴彦祖说:("+inFromClient.readUTF()+")"+getTime()+"\n");
				thread.sleep(1000);
			}
		} catch (IOException e) {
			// TODO: handle exception
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			thread.start();
		}
		
	}
	
	//响应按钮事件，发送消息给对方
	@Override
	public void actionPerformed(ActionEvent e) {
		String string  = msgTextField.getText();
		if(string.length()>0){
			try {
				outToClient.writeUTF(string);
				outToClient.flush();
				showTextArea.append("高圆圆说:("+string+")"+getTime()+"\n" );
				msgTextField.setText(null);
			} catch (Exception e2) {
				showTextArea.append("你的消息未发送出去"+getTime()+"\n");
			}
		}
	}
	
	/*
	 * 获取当前时间
	 */
	private String getTime(){
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}

	
}











