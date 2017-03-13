package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class UserTestView implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2582770081477356141L;

	@Id
	private String TestNo;
	
	private String UserTestID;
	
	private String TestDate;
	
	private String Operator;
	
	private String TestName;


	public String getTestNo() {
		return TestNo;
	}

	public void setTestNo(String testNo) {
		TestNo = testNo;
	}

	public String getUserTestID() {
		return UserTestID;
	}

	public void setUserTestID(String userTestID) {
		UserTestID = userTestID;
	}

	public String getTestDate() {
		return TestDate;
	}

	public void setTestDate(String testDate) {
		TestDate = testDate;
	}

	public String getOperator() {
		return Operator;
	}

	public void setOperator(String operator) {
		Operator = operator;
	}

	public String getTestName() {
		return TestName;
	}

	public void setTestName(String testName) {
		TestName = testName;
	}
	

}
