package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Userlog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3667342734647518139L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logid;
	
	private Integer loguserid;

	private String logname;
	
	private String logdate;
	
	private String logoperate;
	
	private String logip;

	public int getLogid() {
		return logid;
	}

	public void setLogid(int logid) {
		this.logid = logid;
	}


	public String getLogdate() {
		return logdate;
	}

	public void setLogdate(String logdate) {
		this.logdate = logdate;
	}

	public String getLogoperate() {
		return logoperate;
	}

	public void setLogoperate(String logoperate) {
		this.logoperate = logoperate;
	}

	public String getLogip() {
		return logip;
	}

	public void setLogip(String logip) {
		this.logip = logip;
	}

	public Integer getLoguserid() {
		return loguserid;
	}

	public void setLoguserid(Integer loguserid) {
		this.loguserid = loguserid;
	}

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}
}
