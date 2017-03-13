package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Runstate implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -793121307663867684L;

	@Id
	private String statesbbh;

	private String statebcsj;
	
	private String statextsj;
	
	private String statecjsj;
	
	private String stateclsj;
	
	private String statexxbh;

	public String getStatesbbh() {
		return statesbbh;
	}

	public void setStatesbbh(String statesbbh) {
		this.statesbbh = statesbbh;
	}

	public String getStatebcsj() {
		return statebcsj;
	}

	public void setStatebcsj(String statebcsj) {
		this.statebcsj = statebcsj;
	}

	public String getStatextsj() {
		return statextsj;
	}

	public void setStatextsj(String statextsj) {
		this.statextsj = statextsj;
	}

	public String getStatecjsj() {
		return statecjsj;
	}

	public void setStatecjsj(String statecjsj) {
		this.statecjsj = statecjsj;
	}

	public String getStateclsj() {
		return stateclsj;
	}

	public void setStateclsj(String stateclsj) {
		this.stateclsj = stateclsj;
	}

	public String getStatexxbh() {
		return statexxbh;
	}

	public void setStatexxbh(String statexxbh) {
		this.statexxbh = statexxbh;
	}
	
	

}
