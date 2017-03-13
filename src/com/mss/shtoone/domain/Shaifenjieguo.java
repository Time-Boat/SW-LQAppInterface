package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Shaifenjieguo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jieguoid;
	private Integer swbianhao;
	private String passper1;
	private String passper2;
	private String passper3;
	private String passper4;
	private String passper5;
	private String passper6;
	private String passper7;
	private String passper8;
	private String passper9;
	private String passper10;
	private String passper11;
	private String passper12;
	private String passper13;
	private String passper14;
	private String passper15;
	private Integer sjglsf1;
	private Integer sjglsf2;
	private Integer sjglsf3;
	private Integer sjglsf4;
	private Integer sjglsf5;
	private Integer sjflsf1;
	private Integer sjflsf2;
	public int getJieguoid() {
		return jieguoid;
	}
	public void setJieguoid(int jieguoid) {
		this.jieguoid = jieguoid;
	}
	public Integer getSwbianhao() {
		return swbianhao;
	}
	public void setSwbianhao(Integer swbianhao) {
		this.swbianhao = swbianhao;
	}
	public String getPassper1() {
		return passper1;
	}
	public void setPassper1(String passper1) {
		this.passper1 = passper1;
	}
	public String getPassper2() {
		return passper2;
	}
	public void setPassper2(String passper2) {
		this.passper2 = passper2;
	}
	public String getPassper3() {
		return passper3;
	}
	public void setPassper3(String passper3) {
		this.passper3 = passper3;
	}
	public String getPassper4() {
		return passper4;
	}
	public void setPassper4(String passper4) {
		this.passper4 = passper4;
	}
	public String getPassper5() {
		return passper5;
	}
	public void setPassper5(String passper5) {
		this.passper5 = passper5;
	}
	public String getPassper6() {
		return passper6;
	}
	public void setPassper6(String passper6) {
		this.passper6 = passper6;
	}
	public String getPassper7() {
		return passper7;
	}
	public void setPassper7(String passper7) {
		this.passper7 = passper7;
	}
	public String getPassper8() {
		return passper8;
	}
	public void setPassper8(String passper8) {
		this.passper8 = passper8;
	}
	public String getPassper9() {
		return passper9;
	}
	public void setPassper9(String passper9) {
		this.passper9 = passper9;
	}
	public String getPassper10() {
		return passper10;
	}
	public void setPassper10(String passper10) {
		this.passper10 = passper10;
	}
	public String getPassper11() {
		return passper11;
	}
	public void setPassper11(String passper11) {
		this.passper11 = passper11;
	}
	public String getPassper12() {
		return passper12;
	}
	public void setPassper12(String passper12) {
		this.passper12 = passper12;
	}
	public String getPassper13() {
		return passper13;
	}
	public void setPassper13(String passper13) {
		this.passper13 = passper13;
	}
	public String getPassper14() {
		return passper14;
	}
	public void setPassper14(String passper14) {
		this.passper14 = passper14;
	}
	public String getPassper15() {
		return passper15;
	}
	public void setPassper15(String passper15) {
		this.passper15 = passper15;
	}
	public Integer getSjglsf1() {
		return sjglsf1;
	}
	public void setSjglsf1(Integer sjglsf1) {
		this.sjglsf1 = sjglsf1;
	}
	public Integer getSjglsf2() {
		return sjglsf2;
	}
	public void setSjglsf2(Integer sjglsf2) {
		this.sjglsf2 = sjglsf2;
	}
	public Integer getSjglsf3() {
		return sjglsf3;
	}
	public void setSjglsf3(Integer sjglsf3) {
		this.sjglsf3 = sjglsf3;
	}
	public Integer getSjglsf4() {
		return sjglsf4;
	}
	public void setSjglsf4(Integer sjglsf4) {
		this.sjglsf4 = sjglsf4;
	}
	public Integer getSjglsf5() {
		return sjglsf5;
	}
	public void setSjglsf5(Integer sjglsf5) {
		this.sjglsf5 = sjglsf5;
	}
	public Integer getSjflsf1() {
		return sjflsf1;
	}
	public void setSjflsf1(Integer sjflsf1) {
		this.sjflsf1 = sjflsf1;
	}
	public Integer getSjflsf2() {
		return sjflsf2;
	}
	public void setSjflsf2(Integer sjflsf2) {
		this.sjflsf2 = sjflsf2;
	}
}
