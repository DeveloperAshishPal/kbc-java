package com.devlopnation.testengine.question.view;

import static com.devlopnation.testengine.utils.ApplicationStatusConstants.OPTIONA;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.OPTIONB;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.OPTIONC;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.OPTIOND;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import com.devlopnation.testengine.question.dao.QuestionDAO;
import com.devlopnation.testengine.question.dto.QuestionDTO;
import com.devlopnation.testengine.user.view.UserLoginView;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuestionTest extends JFrame {

	private ArrayList<QuestionDTO> questionList;
	ButtonGroup bg = new ButtonGroup();
	JLabel lblQuestion;
	JRadioButton rdbtnA = new JRadioButton("A",false);
	JRadioButton rdbtnB = new JRadioButton("B",false);
	JRadioButton rdbtnC = new JRadioButton("C",false);
	JRadioButton rdbtnD = new JRadioButton("D",false);
	JLabel lblQuestionNo;
	JLabel lblUnAnswered;
	JLabel lblAnswered;
	JLabel lblTotal;
	JButton btnSubmit = new JButton("Submit");
	/*JButton btnNext = new JButton("Next");
	JButton btnPrev = new JButton("Previous");*/
	private int counter = 0;
	private boolean isCorrect = false;
	private int scoreCard = 0;
	
	public static void main(String[] args) {
		QuestionTest frame = new QuestionTest();
		frame.setVisible(true);
	}

	
	public QuestionTest() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		getContentPane().setLayout(null);
		
		lblQuestion = new JLabel("Question Is the Text ?");
		lblQuestion.setVerticalAlignment(SwingConstants.TOP);
		lblQuestion.setBounds(12, 62, 576, 137);
		getContentPane().add(lblQuestion);
		
		
		rdbtnA.setBounds(36, 191, 254, 23);
		getContentPane().add(rdbtnA);
		
		rdbtnB.setBounds(36, 228, 254, 23);
		getContentPane().add(rdbtnB);
		
		rdbtnC.setBounds(36, 265, 254, 23);
		getContentPane().add(rdbtnC);
		
		rdbtnD.setBounds(36, 302, 254, 23);
		getContentPane().add(rdbtnD);
		
		bg.add(rdbtnA);
		bg.add(rdbtnB);
		bg.add(rdbtnC);
		bg.add(rdbtnD);
		
		
		JLabel lblQuestionNo = new JLabel("Question No ");
		lblQuestionNo.setBounds(445, 12, 107, 15);
		getContentPane().add(lblQuestionNo);
		btnSubmit.addActionListener(new ActionListener() {
			// create three methods.... to do this work of button click 
			
			public void actionPerformed(ActionEvent arg0) {
				submit();
				next();
				System.out.println(scoreCard);
			}
		});
		
		
		btnSubmit.setBounds(428, 227, 117, 25);
		getContentPane().add(btnSubmit);
		
		
		/*btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				next();		
			}
		});
		btnNext.setBounds(428, 264, 117, 25);
		getContentPane().add(btnNext);
		
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prev();
			}
		});
		btnPrev.setBounds(428, 301, 117, 25);
		getContentPane().add(btnPrev);*/
		
		JLabel lblUnAnswered = new JLabel("UnAnswered");
		lblUnAnswered.setBounds(300, 12, 107, 15);
		getContentPane().add(lblUnAnswered);
		
		JLabel lblAnswered = new JLabel("Answered");
		lblAnswered.setBounds(155, 12, 107, 15);
		getContentPane().add(lblAnswered);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 12, 107, 15);
		getContentPane().add(lblTotal);
		getQuestion();
	}
	
	private void next(){
		counter++;
		if(counter < questionList.size()){
			/*btnPrev.setEnabled(true);*/
			fillQuestions(counter, questionList);
		}else{
			/*btnNext.setEnabled(false);*/
			showMessage("This is Last Question");
		}
	}
	private void prev(){
		counter--;
		if(counter > 0){
			/*btnNext.setEnabled(true);*/
			fillQuestions(counter, questionList);
		}else{
			/*btnPrev.setEnabled(false);*/
			showMessage("This is First Question");
		}
	}
	private void submit(){
		if(isCorrect){
			scoreCard++;
		}
	}
	
	private void fillQuestions(int questionNumber,ArrayList<QuestionDTO> questionList){
		QuestionDTO questionDTO = questionList.get(questionNumber);
		String question = questionDTO.getQuestion();
		lblQuestion.setText(question);
		String optionC = questionDTO.getOptionC();
		rdbtnC.setText(optionC);
		String optionA = questionDTO.getOptionA();
		rdbtnA.setText(optionA);
		String optionB = questionDTO.getOptionB();
		rdbtnB.setText(optionB);
		String optionD = questionDTO.getOptionD();
		rdbtnD.setText(optionD);
		String answer = questionDTO.getAnswer();
		isCorrect(answer);
	}
	private void isCorrect(String correctAnswer){
		if(rdbtnA.isSelected() && correctAnswer.equals(OPTIONA)){
			isCorrect = true;
		}else if(rdbtnB.isSelected() && correctAnswer.equals(OPTIONB)){
			isCorrect = true;
		}else if(rdbtnC.isSelected() && correctAnswer.equals(OPTIONC)){
			isCorrect = true;
		}else if(rdbtnD.isSelected() && correctAnswer.equals(OPTIOND)){
			isCorrect = true;
		}
		isCorrect = false;
	}
	
	private void showMessage(String message){
		JOptionPane.showMessageDialog(this, message);
	}
	private void getQuestion(){
		
		questionList = new ArrayList<>();
		QuestionDAO questionDAO = new QuestionDAO();
		try {
			questionList = questionDAO.fetchData();
			System.out.println(questionList.size());
			if(questionList.isEmpty()){
				JOptionPane.showMessageDialog(this, "Question List is Empty");
			}else{
				int reply = JOptionPane.showConfirmDialog(this, "Wanna Start Test", "Test Engine", JOptionPane.OK_CANCEL_OPTION);
				if(reply == JOptionPane.OK_OPTION){
					// fill question Paper
					fillQuestions(0,questionList);
				}else{
					// go back to login screen
					UserLoginView login = new UserLoginView();
					login.setVisible(true);
					this.dispose();
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
