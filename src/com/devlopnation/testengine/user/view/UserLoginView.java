package com.devlopnation.testengine.user.view;

import static com.devlopnation.testengine.utils.ApplicationStatusConstants.FAIL;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.ADMINSUCCESS;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.USERSUCCESS;
import javax.swing.JFrame;

import com.devlopnation.testengine.question.view.AddQuestion;
import com.devlopnation.testengine.question.view.QuestionTest;
import com.devlopnation.testengine.user.dao.UserDAO;
import com.devlopnation.testengine.user.dto.UserDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Font;
import java.awt.event.ActionEvent;
import static com.devlopnation.testengine.utils.MessageBundleReader.getValue;
import java.awt.Color;

public class UserLoginView extends JFrame {
	/**
	 * 
	 */
	private JTextField txtUsername;
	private JPasswordField passwordField;
	JButton btnLogin = new JButton(getValue("login.loginbutton"));

	public static void main(String[] args) {
					UserLoginView frame = new UserLoginView();
					frame.setVisible(true);
					frame.setResizable(false);
	}

	public UserLoginView() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Login User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 272);
		getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel(getValue("login.heading"));
		lblLogin.setBounds(200, 12, 70, 15);
		getContentPane().add(lblLogin);
		
		JLabel lblUsername = new JLabel(getValue("login.userlabel"));
		lblUsername.setBounds(41, 66, 101, 15);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel(getValue("login.passlabel"));
		lblPassword.setBounds(41, 134, 101, 15);
		getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Lato", Font.BOLD, 16));
		txtUsername.setText("");
		txtUsername.setBounds(200, 50, 178, 45);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Lato", Font.BOLD, 16));
		passwordField.setBounds(200, 134, 178, 45);
		getContentPane().add(passwordField);
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					userLogin();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
				
		btnLogin.setBounds(153, 211, 117, 25);
		getContentPane().add(btnLogin);
	}
	private void clear(){
		txtUsername.setText("");
		passwordField.setText("");
	}
	private void userLogin() throws ClassNotFoundException {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserid(txtUsername.getText());
		String password = new String(passwordField.getPassword());
		userDTO.setPassword(password);
		UserDAO dao = new UserDAO();
		try{
			String message = dao.login(userDTO).toLowerCase();
			//System.out.println(message);
			if(message.equals(ADMINSUCCESS)){
				//System.out.println("inside admin " + message);
				JOptionPane.showMessageDialog(this,"Admin login Sucess");
				AddQuestion addquestion = new AddQuestion();
				addquestion.setVisible(true);
				this.dispose();
			}else if(message.equals(USERSUCCESS)){
				JOptionPane.showMessageDialog(this,"user login Sucess");
				QuestionTest questionTest = new QuestionTest();
				questionTest.setVisible(true);
				this.dispose();
			}else if(message.equals(FAIL)){
				JOptionPane.showMessageDialog(this,"login Fail");
			}else if(message.equals(ERROR)){
				JOptionPane.showMessageDialog(this,"ERROR");
			}
		}catch(IOException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Send this to Company Log or LOG4J");
		}finally{
			clear();
		}
	}
}
