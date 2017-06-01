package com.devlopnation.testengine.question.view;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.devlopnation.testengine.question.dao.QuestionDAO;
import com.devlopnation.testengine.question.dto.QuestionDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import static com.devlopnation.testengine.utils.MessageBundleReader.getValue;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.SUCCESS;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.FAIL;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AddQuestion extends JFrame {
	private JTextField txtAnswerA;
	private JTextField txtAnswerB;
	private JTextField txtAnswerC;
	private JTextField txtAnswerD;
	private JTextArea txtQuestion;
	ButtonGroup bg = new ButtonGroup();
	JRadioButton rdbtnA = new JRadioButton(getValue("addQuestion.optnArb"),false);
	JRadioButton rdbtnB = new JRadioButton(getValue("addQuestion.optnBrb"),false);
	JRadioButton rdbtnC = new JRadioButton(getValue("addQuestion.optnCrb"),false);
	JRadioButton rdbtnD = new JRadioButton(getValue("addQuestion.optnDrb"),false);

	public static void main(String[] args) {
		
			AddQuestion frame = new AddQuestion();
			frame.setVisible(true);
	}

	public AddQuestion() {
		setTitle(getValue("addQuestion.title"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		getContentPane().setLayout(null);
		
		txtQuestion = new JTextArea();
		txtQuestion.setBounds(33, 39, 515, 166);
		getContentPane().add(txtQuestion);
		
		txtAnswerA = new JTextField();
		txtAnswerA.setBounds(109, 243, 114, 19);
		getContentPane().add(txtAnswerA);
		txtAnswerA.setColumns(10);
		
		txtAnswerB = new JTextField();
		txtAnswerB.setColumns(10);
		txtAnswerB.setBounds(109, 286, 114, 19);
		getContentPane().add(txtAnswerB);
		
		txtAnswerC = new JTextField();
		txtAnswerC.setColumns(10);
		txtAnswerC.setBounds(109, 332, 114, 19);
		getContentPane().add(txtAnswerC);
		
		txtAnswerD = new JTextField();
		txtAnswerD.setColumns(10);
		txtAnswerD.setBounds(109, 374, 114, 19);
		getContentPane().add(txtAnswerD);
		
		JLabel lblOptionA = new JLabel(getValue("addQuestion.optnAlabel"));
		lblOptionA.setBounds(12, 247, 70, 15);
		getContentPane().add(lblOptionA);
		
		JLabel lblOptionB = new JLabel(getValue("addQuestion.optnBlabel"));
		lblOptionB.setBounds(12, 288, 70, 15);
		getContentPane().add(lblOptionB);
		
		JLabel lblOptionC = new JLabel(getValue("addQuestion.optnClabel"));
		lblOptionC.setBounds(12, 334, 70, 15);
		getContentPane().add(lblOptionC);
		
		JLabel lblOptionD = new JLabel(getValue("addQuestion.optnDlabel"));
		lblOptionD.setBounds(12, 376, 70, 15);
		getContentPane().add(lblOptionD);
		
		
		rdbtnA.setBounds(379, 241, 149, 23);
		getContentPane().add(rdbtnA);
		
		
		rdbtnB.setBounds(379, 284, 149, 23);
		getContentPane().add(rdbtnB);
		
		
		rdbtnC.setBounds(379, 330, 149, 23);
		getContentPane().add(rdbtnC);
		
		
		rdbtnD.setBounds(379, 372, 149, 23);
		getContentPane().add(rdbtnD);
		
		bg.add(rdbtnA);
		bg.add(rdbtnB);
		bg.add(rdbtnC);
		bg.add(rdbtnD);
		
		JButton btnSubmit = new JButton(getValue("addQuestion.submitbtn"));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!blank()){
				addQuestion();
				}else{
					showMessage();
				}
			}

		});
		btnSubmit.setBounds(242, 413, 117, 25);
		getContentPane().add(btnSubmit);
	}
	
	private boolean blank(){
		if(txtQuestion.getText().length()!=0 && txtAnswerA.getText().length()!=0 && txtAnswerB.getText().length()!=0 
				&& txtAnswerC.getText().length()!=0 && txtAnswerD.getText().length()!=0){
			return false;
		}
		return true;
	}
	
	private void showMessage(){
		JOptionPane.showMessageDialog(this,"Field Error");
	}
	
	private void clear(){
		txtQuestion.setText("");
		txtAnswerA.setText("");
		txtAnswerB.setText("");
		txtAnswerC.setText("");
		txtAnswerD.setText("");
	}
	
	private void addQuestion() {
		// TODO Auto-generated method stub
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setQuestion(txtQuestion.getText());
		questionDTO.setOptionA(txtAnswerA.getText());
		questionDTO.setOptionB(txtAnswerB.getText());
		questionDTO.setOptionC(txtAnswerC.getText());
		questionDTO.setOptionD(txtAnswerD.getText());
		if(rdbtnA.isSelected()){
			questionDTO.setAnswer(rdbtnA.getText());
		}else if(rdbtnB.isSelected()){
			questionDTO.setAnswer(rdbtnB.getText());
		}else if(rdbtnC.isSelected()){
			questionDTO.setAnswer(rdbtnC.getText());
		}else if(rdbtnD.isSelected()){
			questionDTO.setAnswer(rdbtnD.getText());
		}
		QuestionDAO questionDAO = new QuestionDAO();
		try {
			String message = questionDAO.addQuestion(questionDTO);
			if(message.equals(SUCCESS)){
				JOptionPane.showMessageDialog(this, "User Register SuccessFully !");
				// message for success
			}else if(message.equals(FAIL)){
				JOptionPane.showMessageDialog(this, "User Register fail!");
				// message for Fail
			}else if(message.equals(ERROR)){
				JOptionPane.showMessageDialog(this, "Error !");
				// message for Error
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			clear();
		}
	}
	
}
