package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Xiangxixxsms implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7103529989300148619L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bianhao;
	
	private Integer panshu;
	
	private Integer leiji;

	private String shebeibianhao;

	public int getBianhao() {
		return bianhao;
	}

	public void setBianhao(int bianhao) {
		this.bianhao = bianhao;
	}

	public Integer getPanshu() {
		return panshu;
	}

	public void setPanshu(Integer panshu) {
		this.panshu = panshu;
	}

	public Integer getLeiji() {
		return leiji;
	}

	public void setLeiji(Integer leiji) {
		this.leiji = leiji;
	}

	public String getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}	

}
