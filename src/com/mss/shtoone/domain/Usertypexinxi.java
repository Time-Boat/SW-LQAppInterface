package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usertypexinxi implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6489796327030567768L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String minchen;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMinchen() {
		return minchen;
	}
	public void setMinchen(String minchen) {
		this.minchen = minchen;
	}

}
