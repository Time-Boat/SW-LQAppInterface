package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Shoupan implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -492122176065646799L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String banhezhanminchen;//拌和机名称
	private int bianhao; //开机的编号
	private int panshu;//开机之后的，同一个机的统计盘数 前两盘不报警
	private String shebeibianhao;//设备编号
	private String yesorno;//off: 就是关闭开盘前两盘不报警， on：打开开盘前两盘不报警
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBanhezhanminchen() {
		return banhezhanminchen;
	}
	public void setBanhezhanminchen(String banhezhanminchen) {
		this.banhezhanminchen = banhezhanminchen;
	}
	
	public int getBianhao() {
		return bianhao;
	}
	public void setBianhao(int bianhao) {
		this.bianhao = bianhao;
	}
	public int getPanshu() {
		return panshu;
	}
	public void setPanshu(int panshu) {
		this.panshu = panshu;
	}
	public String getShebeibianhao() {
		return shebeibianhao;
	}
	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}
	public String getYesorno() {
		return yesorno;
	}
	public void setYesorno(String yesorno) {
		this.yesorno = yesorno;
	}
	
	
	

}
