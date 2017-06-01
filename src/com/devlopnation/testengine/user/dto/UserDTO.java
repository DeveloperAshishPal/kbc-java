package com.devlopnation.testengine.user.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDTO implements Serializable{
	/**
	 * 
	 */
	private String userid;
	private String password;
	private String accountType;
	/*private ArrayList<String> scoreCard; */
	
	@Override
	public boolean equals(Object o){
		if(o instanceof UserDTO){
			UserDTO userDTO = (UserDTO) o;
			if(userDTO.getUserid().equals(this.getUserid()) && userDTO.getPassword().equals(this.getPassword())){
				return true;
			}
		}
		return false;
	}
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*public ArrayList<String> getScoreCard() {
		return scoreCard;
	}
	public void setScoreCard(ArrayList<String> scoreCard) {
		this.scoreCard = scoreCard;
	}*/
	
}
