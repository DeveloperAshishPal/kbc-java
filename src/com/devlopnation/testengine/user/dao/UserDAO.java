package com.devlopnation.testengine.user.dao;

import static com.devlopnation.testengine.utils.MessageBundleReader.getConfigValue;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.ERROR;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.FAIL;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.SUCCESS;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.ADMINSUCCESS;
import static com.devlopnation.testengine.utils.ApplicationStatusConstants.USERSUCCESS;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.devlopnation.testengine.user.dto.UserDTO;

public class UserDAO {
	
	public ArrayList<UserDTO> fetchData() throws IOException, ClassNotFoundException{
		// array list is kind of dynamic array
		ArrayList<UserDTO> userList = new ArrayList<>();
		String message = ERROR;
		FileInputStream fs = null;
		ObjectInputStream os = null;
		String filePath = getConfigValue("userfilepath");
		try{
				fs = new FileInputStream(filePath);
				os = new ObjectInputStream(fs);
				while(true){
					try{
						UserDTO dto = (UserDTO) os.readObject();
						userList.add(dto);
					}catch(EOFException e){
						message = FAIL;
						return userList;
					}
				}
				
		}
		catch(EOFException e){
			return userList;
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
	
	public String register(UserDTO userDTO) throws IOException, ClassNotFoundException{
		String message = ERROR;
		FileOutputStream fs = null;
		ObjectOutputStream os = null;
		String filePath = getConfigValue("userfilepath");
		File file = new File(filePath);
		try{
		if(!file.exists()){
			if(file.createNewFile()){
				return message;
			}
		}
		if(file.exists()){
			ArrayList<UserDTO> userList = this.fetchData();
			userList.add(userDTO);
			fs = new FileOutputStream(file);
			os = new ObjectOutputStream(fs);
			for(UserDTO user : userList){
				os.writeObject(user);
			}
			message = SUCCESS;
		}
		}
		finally{
			if(os!=null){
				os.close();
			}
			if(fs!=null){
				fs.close();
			}
		}
		return message;
	}
	
	public String login(UserDTO userDTO) throws IOException, ClassNotFoundException{
		String message = ERROR;
		FileInputStream fs = null;
		ObjectInputStream os = null;
		String filePath = getConfigValue("userfilepath");
		try{
				fs = new FileInputStream(filePath);
				os = new ObjectInputStream(fs);
				while(true){
					try{
						UserDTO dto = (UserDTO) os.readObject();
						String accountType = dto.getAccountType();
						if(userDTO.equals(dto)){
							if(accountType.equals("ADMIN")){
								message = ADMINSUCCESS;	
							}
							if(accountType.equals("USER")){
								message = USERSUCCESS;
							}
							return message;
						}
					}catch(EOFException e){
						message = FAIL;
						return message;
					}
				}
				
		}finally{
			if(os!=null){
				os.close();
			}
			if(fs!=null){
				fs.close();
			}
		}	
	}
}
