package online.icode.jvm.demo.login;

import java.awt.*;
import javax.swing.*;

public class Login {
	JTextField textField = null;
	JPasswordField pwdField = null;
	ClientReadAndPrint.LoginListen listener=null;

	// 构造函数
	public Login() {
		init();
	}


	void init() {
		JFrame jf = new JFrame("登录");
		//设置背景
		ImageIcon bg =new ImageIcon("F:\\Saved Pictures\\717234.jpg");
		JLabel label =new JLabel(bg);
		int width = bg.getIconWidth();
//		int width = 4000;
		int heigth =bg.getIconHeight();
//		int heigth =2200;
		label.setSize(width,heigth);
		//jf.getLayeredPane().add(label,Integer.valueOf(Integer.MIN_VALUE));
		JPanel backgrond =(JPanel)jf.getContentPane();
    	backgrond.setLayout(new FlowLayout());
		backgrond.setOpaque(false);
		//backgrond.setLayout(null);
		backgrond.setBounds(754, 363, 430, 330);

		JPanel jp1 = new JPanel();
		JLabel headJLabel = new JLabel("登录界面");
		headJLabel.setBounds(107, 198, 200, 32);
		headJLabel.setFont(new Font(null, 0, 35));  // 设置文本的字体类型、样式 和 大小
		backgrond.add(headJLabel);

//		JSeparator separator = new JSeparator();
//		separator.setBounds(87, 197, 240, 1);
//		backgrond.add(separator);

		JPanel jp2 = new JPanel();
		JLabel nameJLabel = new JLabel("用户名：");
		textField = new JTextField(20);
		JLabel pwdJLabel = new JLabel("密码：    ");
		pwdField = new JPasswordField(20);

		JButton loginButton = new JButton("登录");
		JButton registerButton = new JButton("注册");  // 没设置功能
		backgrond.add(nameJLabel);
		backgrond.add(textField);
		backgrond.add(pwdJLabel);
		backgrond.add(pwdField);
		backgrond.add(loginButton);
		backgrond.add(registerButton);


		JPanel jp = new JPanel(new BorderLayout());  // BorderLayout布局
		jp.add(jp1, BorderLayout.NORTH);
		jp.add(jp2, BorderLayout.CENTER);



		// 设置监控
		listener = new ClientReadAndPrint().new LoginListen();  // 新建监听类
		listener.setJTextField(textField);  // 调用PoliceListen类的方法
		listener.setJPasswordField(pwdField);
		listener.setJFrame(jf);
		pwdField.addActionListener(listener);  // 密码框添加监听
		loginButton.addActionListener(listener);  // 按钮添加监听

		jf.setSize(width,heigth);
		jf.setResizable(false);  // 设置是否缩放
		jf.getLayeredPane().add(label,Integer.valueOf(Integer.MIN_VALUE));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 设置关闭图标作用
		jf.setVisible(true);  // 设置可见

	}
}
