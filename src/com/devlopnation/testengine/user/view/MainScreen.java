package com.devlopnation.testengine.user.view;

import static com.devlopnation.testengine.utils.MessageBundleReader.getConfigValue;
import static com.devlopnation.testengine.utils.MessageBundleReader.getValue;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.LOGIN;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.REGISTER;
import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame {
		
	public static void main(String[] args) {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public MainScreen() {
		getContentPane().setBackground(Color.WHITE);
		setBackground(SystemColor.menu);
		setTitle(getValue("mainscreen.title"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 319);
		getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton(getValue("mainscreen.loginbutton"));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchFrame(getValue("mainscreen.loginbutton").toLowerCase());
			}
		});
		btnLogin.setBounds(306, 33, 117, 25);
		getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton(getValue("mainscreen.regbutton"));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchFrame(getValue("mainscreen.regbutton").toLowerCase());
			}
		});
		btnRegister.setBounds(177, 33, 117, 25);
		getContentPane().add(btnRegister);
		
		Icon image = new ImageIcon(getConfigValue("imagespath")+"/welcome.png");
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(image);
		lblNewLabel.setBounds(12, 93, 430, 188);
		getContentPane().add(lblNewLabel);
	}
	
	private void switchFrame(String buttonDo){
		if(buttonDo.equals(LOGIN)){
			// login code
			UserLoginView login = new UserLoginView();
			login.setVisible(true);
		}else if(buttonDo.equals(REGISTER)){
			// register code
			UserRegisterView register = new UserRegisterView();
			register.setVisible(true);
			
		}
		this.dispose();
	}
}
