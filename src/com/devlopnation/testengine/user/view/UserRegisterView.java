package com.devlopnation.testengine.user.view;

import javax.swing.JFrame;
import com.devlopnation.testengine.user.dao.UserDAO;
import com.devlopnation.testengine.user.dto.UserDTO;

import static com.devlopnation.testengine.utils.MessageBundleReader.*;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;

public class UserRegisterView extends JFrame {

	/**
	 * 
	 */
	private JTextField txtUsername;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JPasswordField passwordField;

	public static void main(String[] args) {
		UserRegisterView frame = new UserRegisterView();
					frame.setVisible(true);
	}

	public UserRegisterView() {
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setTitle(getValue("regform.title"));
		setBounds(100, 100, 550, 511);
		this.getContentPane().setLayout(null);
		
		JLabel lblUserRegisterHere = new JLabel(getValue("regform.heading"));
		lblUserRegisterHere.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserRegisterHere.setBounds(181, 54, 233, 15);
		getContentPane().add(lblUserRegisterHere);
		
		JLabel lblUsername = new JLabel(getValue("regform.userlabel"));
		lblUsername.setBounds(68, 139, 106, 43);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel(getValue("regform.passlabel"));
		lblPassword.setBounds(68, 221, 98, 43);
		getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Lato", Font.BOLD, 16));
		txtUsername.setText("");
		txtUsername.setBounds(297, 137, 178, 45);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblCity = new JLabel(getValue("regform.acctypelabel"));
		lblCity.setBounds(69, 343, 105, 42);
		getContentPane().add(lblCity);
		
		comboBox.setBounds(316, 340, 159, 45);
		comboBox.addItem(getValue("regform.comboUser"));
		comboBox.addItem(getValue("regform.comboAdmin"));
		getContentPane().add(comboBox);
		
		JButton btnRegister = new JButton(getValue("regform.regbutton"));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(txtUsername.getText().length()!=0 && passwordField.getText().length()!=0){
						register();	
					}else{
						showMessage();
					}
					clear();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		btnRegister.setMnemonic('R');
		btnRegister.setBounds(218, 430, 119, 56);
		getContentPane().add(btnRegister);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Lato", Font.BOLD, 16));
		passwordField.setBounds(297, 233, 178, 45);
		getContentPane().add(passwordField);
	}
	
	private void showMessage(){
		JOptionPane.showMessageDialog(this,"Field Error");
	}
	
	private void clear(){
		txtUsername.setText("");
		passwordField.setText("");
	}
	
	@SuppressWarnings("deprecation")
	private void register() throws ClassNotFoundException{
		UserDTO userDTO = new UserDTO();
		userDTO.setUserid(txtUsername.getText());
		String password = new String(passwordField.getPassword());
		userDTO.setPassword(password);
		userDTO.setAccountType((String)comboBox.getSelectedItem());
		UserDAO dao = new UserDAO();
		try{
			String message = dao.register(userDTO);
			if(message.equals(SUCCESS)){
				//JOptionPane.showMessageDialog(this,userDTO.getAccountType().toString() + " Register Sucess");
				/*int reply = JOptionPane.showConfirmDialog(this, userDTO.getAccountType().toString() + " Register Sucess", "Test Engine", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					
				}*/
				int reply = JOptionPane.showOptionDialog(this, 
						userDTO.getAccountType().toString() + " Register Sucess", 
				        "Do You Want ?", 
				        JOptionPane.OK_CANCEL_OPTION, 
				        JOptionPane.INFORMATION_MESSAGE, 
				        null, 
				        new String[]{"Login", "Register"}, // this is the array
				        "default");
				if(reply == JOptionPane.OK_OPTION){
					UserLoginView loginFrame = new UserLoginView();
					loginFrame.setVisible(true);
					this.dispose();
				}
			}else if(message.equals(FAIL)){
				JOptionPane.showMessageDialog(this,"User Register Fail");
			}else if(message.equals(ERROR)){
				JOptionPane.showMessageDialog(this,"ERROR");
			}
		}catch(IOException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Send this to Company Log or LOG4J");
		}
	}
}
