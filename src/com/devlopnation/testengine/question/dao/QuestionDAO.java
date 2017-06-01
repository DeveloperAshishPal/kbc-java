package com.devlopnation.testengine.question.dao;

import com.devlopnation.testengine.question.dto.QuestionDTO;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.ERROR;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.FAIL;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.SUCCESS;
import static com.devlopnation.testengine.utils.MessageBundleReader.getConfigValue;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class QuestionDAO {
	
	public ArrayList<QuestionDTO> fetchData() throws IOException, ClassNotFoundException{
		// array list is kind of dynamic array
				ArrayList<QuestionDTO> questionList = new ArrayList<>();
				String message = ERROR;
				FileInputStream fs = null;
				ObjectInputStream os = null;
				String filePath = getConfigValue("questionfilepath");
				try{
						fs = new FileInputStream(filePath);
						os = new ObjectInputStream(fs);
						while(true){
							try{
								QuestionDTO dto = (QuestionDTO) os.readObject();
								questionList.add(dto);
							}catch(EOFException e){
								message = FAIL;
								return questionList;
							}
						}
						
				}
				catch(EOFException e){
					return questionList;
				}
				finally{
					if(os!=null){
						os.close();
					}
					if(fs!=null){
						fs.close();
					}
				}
	}
	
	public String addQuestion(QuestionDTO questionDTO) throws IOException, ClassNotFoundException{
		String message = ERROR;
		FileOutputStream fo = null;
		ObjectOutputStream os = null;
		String filePath = getConfigValue("questionfilepath");
		File file = new File(filePath);
		try{
		if(!file.exists()){
			if(file.createNewFile()){
				return message;
			}
		}
		if(file.exists()){
			ArrayList<QuestionDTO> questionList = this.fetchData();
			questionList.add(questionDTO);
			fo = new FileOutputStream(file);
			os = new ObjectOutputStream(fo);
			for(QuestionDTO question : questionList){
				os.writeObject(question);
			}
			message = SUCCESS;
		}
		}
		finally{
			if(os!=null){
				os.close();
			}
			if(fo!=null){
				fo.close();
			}
		}
		return message;
	}
}
