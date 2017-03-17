package util;

import java.awt.Container;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public abstract class ParUtil {
		//文本域
		public JTextField msgTextField;
		public JTextArea showTextArea;
		
		//主界面
		public JFrame mainJFrame;
		public JButton sentButton;
		public JScrollPane JSPane;
		public JPanel panel;//嵌板
		public Container container; // 容器
		
		public Thread thread = null;
		public ServerSocket serverSocket ;
		public Socket connectToClientSocket;
		public DataInputStream inFromClient;
		public DataOutputStream outToClient;
}
