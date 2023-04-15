package online.icode.jvm.demo.login;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.*;
import java.awt.Point;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JCheckBox;
public class JframeQQ extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_password;
	private JTextField textField_username;
	private JSeparator separator_1;
	private JSeparator separator_2;
	Point pressedPoint;//��¼������ĵط�
	private JSeparator separator;
	private JPanel panel_1;
	private JLabel lblNewLabel_bg;
	private JButton btn_exit;
	private JButton btn_Min;
	private JButton btn_set;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeQQ frame = new JframeQQ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JframeQQ() {
		//setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		//setTitle("TQchatting");
		
		
	
		setBounds(754, 363, 430, 330);
		contentPane = new JPanel();
		setUndecorated(true);//ȡ����������Ч��
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		
		Border border = BorderFactory.createEtchedBorder();
		contentPane.setBorder(border);

		
		setAlwaysOnTop(true);
		//contentPane.setBackground(new Color(255, 255, 255));
		//contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		
		


		
		/**
		 * ��������ƶ��¼�
		 */
		contentPane.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { //��갴���¼�
				setAlwaysOnTop(true);
				pressedPoint = e.getPoint(); //��¼�������
			}
		});
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) { // �����ק�¼�
				Point point = e.getPoint();// ��ȡ��ǰ����
				Point locationPoint = getLocation();// ��ȡ��������
				int x = locationPoint.x + point.x - pressedPoint.x;// �����ƶ����������
				int y = locationPoint.y + point.y - pressedPoint.y;
				setLocation(x, y);// �ı䴰��λ��
			}
		});
		contentPane.setLayout(null);
		
		textField_password = new JTextField();
		textField_password.setBounds(107, 198, 200, 32);
		textField_password.setFont(new Font("Gadugi", Font.PLAIN, 18));
		textField_password.setBorder(new EmptyBorder(0, 0, 0, 0));//ȥ���߿�
		contentPane.add(textField_password);
		textField_password.setColumns(10);
		
		separator = new JSeparator();
		separator.setBounds(87, 197, 240, 1);
		contentPane.add(separator);
		
		textField_username = new JTextField();
		textField_username.setBounds(107, 165, 200, 32);
		textField_username.setFont(new Font("Gadugi", Font.PLAIN, 18));
		textField_username.setColumns(10);
		textField_username.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(textField_username);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(87, 231, 240, 1);
		contentPane.add(separator_2);
		
		JButton btn_login = new JButton("\u767B\u5F55");
		btn_login.setBounds(87, 272, 240, 35);
		btn_login.setForeground(new Color(255, 255, 255));
		btn_login.setBackground(new Color(0, 204, 255));
		btn_login.setBorder(null);
		contentPane.add(btn_login);
		 ImageIcon image;
		 //gifͼ�Լ��ҡ��������õľ���·��
	     image = new ImageIcon("D:\\lAHPDhJzs8isFofM8M0Bqg_426_240.gif");
	     // image.setImage(image.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT));
	     
	     //gif����ͼƬ�ü�
	     Image img = image.getImage();
	     img = img.getScaledInstance(430,128, Image.SCALE_DEFAULT);
	     image.setImage(img);
	     separator_1 = new JSeparator();
	     separator_1.setBounds(0, 128, 430, 1);
	     contentPane.add(separator_1);
	     
	     panel_1 = new JPanel();
	     panel_1.setBounds(0, 0, 430, 128);
	     contentPane.add(panel_1);
	     lblNewLabel_bg = new JLabel("");
	     lblNewLabel_bg.setBounds(0, 0, 430, 128);
	     lblNewLabel_bg.setIcon(image);
	     panel_1.setOpaque(false);
	     panel_1.setLayout(null);
	     lblNewLabel_bg.setVerticalAlignment(SwingConstants.CENTER);
	     lblNewLabel_bg.setHorizontalAlignment(SwingConstants.CENTER);
	     lblNewLabel_bg.setBorder(new EmptyBorder(0, 0, 0, 0));
	     panel_1.add(lblNewLabel_bg);
	     
	     btn_exit = new JButton("\u00D7");
	     btn_exit.setForeground(Color.WHITE);
	     btn_exit.setContentAreaFilled(false);//�����ޱ���ɫ
	     //�������¼�
	     btn_exit.addMouseListener(new MouseAdapter() {
	     	@Override
	     	public void mouseEntered(MouseEvent e) {
	     		btn_exit.setBackground(new Color(255, 84, 57));
	     		btn_exit.setContentAreaFilled(true);
	     	}
	     	@Override
	     	public void mouseExited(MouseEvent e) {
	     		btn_exit.setContentAreaFilled(false);//�����ޱ���ɫ
	     	}
	     	@Override
	     	public void mouseClicked(MouseEvent e) {
	     		System.exit(0);
	     	}
	     });
	     btn_exit.setFont(new Font("����", Font.PLAIN, 19));
	     btn_exit.setBounds(397, 0, 33, 33);
	     btn_exit.setBorder(null);
	     
	     btn_exit.setFocusPainted(false);
	     panel_1.add(btn_exit);
	     getLayeredPane().add(btn_exit,new Integer(Integer.MAX_VALUE));
	     
	     btn_Min = new JButton("\u2212");
	     btn_Min.setForeground(Color.WHITE);
	     btn_Min.addMouseListener(new MouseAdapter() {
	     	@Override
	     	public void mouseEntered(MouseEvent e) {
	     		btn_Min.setBackground(new Color(173,222,250));
	     		btn_Min.setContentAreaFilled(true);
	     	}
	     	@Override
	     	public void mouseExited(MouseEvent e) {
	     		btn_Min.setContentAreaFilled(false);//�����ޱ���ɫ
	     	}
	     	@Override
	     	public void mouseClicked(MouseEvent e) {
	     		setState(ICONIFIED);
	     	}
	     });
	     btn_Min.setVerticalAlignment(SwingConstants.TOP);
	     btn_Min.setFont(new Font("Verdana Pro Cond Light", Font.PLAIN, 21));
	     btn_Min.setFocusPainted(false);
	     btn_Min.setContentAreaFilled(false);
	     btn_Min.setBorder(null);
	     btn_Min.setBounds(364, 0, 33, 33);
	     panel_1.add(btn_Min);
	     getLayeredPane().add(btn_Min,new Integer(Integer.MAX_VALUE));
	     
	     btn_set = new JButton("\u25A3");
	     btn_set.setContentAreaFilled(false);
	     btn_set.addMouseListener(new MouseAdapter() {
	     	@Override
	     	public void mouseEntered(MouseEvent e) {
	     		btn_set.setBackground(new Color(173,222,250));
	     		btn_set.setContentAreaFilled(true);
	     	}
	     	@Override
	     	public void mouseExited(MouseEvent e) {
	     		btn_set.setContentAreaFilled(false);//�����ޱ���ɫ
	     	}
	     	@Override
	     	public void mouseClicked(MouseEvent e) {
	     	}
	     });
	     btn_set.setForeground(Color.WHITE);
	     btn_set.setFont(new Font("Dialog", Font.PLAIN, 36));
	     btn_set.setFocusPainted(false);
	     btn_set.setBorder(null);
	     btn_set.setBounds(331, 0, 33, 33);
	     panel_1.add(btn_set);
	     getLayeredPane().add(btn_set,new Integer(Integer.MAX_VALUE));
	     
	     JCheckBox CheckBox = new JCheckBox("\u81EA\u52A8\u767B\u5F55");
	     CheckBox.setFont(new Font("����", Font.PLAIN, 11));
	     CheckBox.setBounds(87, 240, 71, 24);
	     
	     CheckBox.setBorder(new EmptyBorder(0, 0, 0, 0));
	     CheckBox.setContentAreaFilled(false);
	     CheckBox.setFocusPainted(false);
	     contentPane.add(CheckBox);
	}
}