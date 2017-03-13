package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Shaifenshiyan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -47913122128256838L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String shebeibianhao;
	private String ziduanminchen;
	
	private String moren;
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
	private String newtime;   //新建时间
	private String updatetime;  //修改时间
	private String llid;    //关联理论配合比
	//最大值
	private String maxpassper1;
	private String maxpassper2;
	private String maxpassper3;
	private String maxpassper4;
	private String maxpassper5;
	private String maxpassper6;
	private String maxpassper7;
	private String maxpassper8;
	private String maxpassper9;
	private String maxpassper10;
	private String maxpassper11;
	private String maxpassper12;
	private String maxpassper13;
	private String maxpassper14;
	private String maxpassper15;
	//最小值
	private String minpassper1;
	private String minpassper2;
	private String minpassper3;
	private String minpassper4;
	private String minpassper5;
	private String minpassper6;
	private String minpassper7;
	private String minpassper8;
	private String minpassper9;
	private String minpassper10;
	private String minpassper11;
	private String minpassper12;
	private String minpassper13;
	private String minpassper14;
	private String minpassper15;
	//目标配合比筛分通过率
	private String standPassper1;
	private String standPassper2;
	private String standPassper3;
	private String standPassper4;
	private String standPassper5;
	private String standPassper6;
	private String standPassper7;
	private String standPassper8;
	private String standPassper9;
	private String standPassper10;
	private String standPassper11;
	private String standPassper12;
	private String standPassper13;
	private String standPassper14;
	private String standPassper15;
	public String getStandPassper1() {
		return standPassper1;
	}
	public void setStandPassper1(String standPassper1) {
		this.standPassper1 = standPassper1;
	}
	public String getStandPassper2() {
		return standPassper2;
	}
	public void setStandPassper2(String standPassper2) {
		this.standPassper2 = standPassper2;
	}
	public String getStandPassper3() {
		return standPassper3;
	}
	public void setStandPassper3(String standPassper3) {
		this.standPassper3 = standPassper3;
	}
	public String getStandPassper4() {
		return standPassper4;
	}
	public void setStandPassper4(String standPassper4) {
		this.standPassper4 = standPassper4;
	}
	public String getStandPassper5() {
		return standPassper5;
	}
	public void setStandPassper5(String standPassper5) {
		this.standPassper5 = standPassper5;
	}
	public String getStandPassper6() {
		return standPassper6;
	}
	public void setStandPassper6(String standPassper6) {
		this.standPassper6 = standPassper6;
	}
	public String getStandPassper7() {
		return standPassper7;
	}
	public void setStandPassper7(String standPassper7) {
		this.standPassper7 = standPassper7;
	}
	public String getStandPassper8() {
		return standPassper8;
	}
	public void setStandPassper8(String standPassper8) {
		this.standPassper8 = standPassper8;
	}
	public String getStandPassper9() {
		return standPassper9;
	}
	public void setStandPassper9(String standPassper9) {
		this.standPassper9 = standPassper9;
	}
	public String getStandPassper10() {
		return standPassper10;
	}
	public void setStandPassper10(String standPassper10) {
		this.standPassper10 = standPassper10;
	}
	public String getStandPassper11() {
		return standPassper11;
	}
	public void setStandPassper11(String standPassper11) {
		this.standPassper11 = standPassper11;
	}
	public String getStandPassper12() {
		return standPassper12;
	}
	public void setStandPassper12(String standPassper12) {
		this.standPassper12 = standPassper12;
	}
	public String getStandPassper13() {
		return standPassper13;
	}
	public void setStandPassper13(String standPassper13) {
		this.standPassper13 = standPassper13;
	}
	public String getStandPassper14() {
		return standPassper14;
	}
	public void setStandPassper14(String standPassper14) {
		this.standPassper14 = standPassper14;
	}
	public String getStandPassper15() {
		return standPassper15;
	}
	public void setStandPassper15(String standPassper15) {
		this.standPassper15 = standPassper15;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShebeibianhao() {
		return shebeibianhao;
	}
	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}
	public String getZiduanminchen() {
		return ziduanminchen;
	}
	public void setZiduanminchen(String ziduanminchen) {
		this.ziduanminchen = ziduanminchen;
	}
	public String getMoren() {
		return moren;
	}
	public void setMoren(String moren) {
		this.moren = moren;
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
	public String getNewtime() {
		return newtime;
	}
	public void setNewtime(String newtime) {
		this.newtime = newtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getLlid() {
		return llid;
	}
	public void setLlid(String llid) {
		this.llid = llid;
	}
	public String getMaxpassper1() {
		return maxpassper1;
	}
	public void setMaxpassper1(String maxpassper1) {
		this.maxpassper1 = maxpassper1;
	}
	public String getMaxpassper2() {
		return maxpassper2;
	}
	public void setMaxpassper2(String maxpassper2) {
		this.maxpassper2 = maxpassper2;
	}
	public String getMaxpassper3() {
		return maxpassper3;
	}
	public void setMaxpassper3(String maxpassper3) {
		this.maxpassper3 = maxpassper3;
	}
	public String getMaxpassper4() {
		return maxpassper4;
	}
	public void setMaxpassper4(String maxpassper4) {
		this.maxpassper4 = maxpassper4;
	}
	public String getMaxpassper5() {
		return maxpassper5;
	}
	public void setMaxpassper5(String maxpassper5) {
		this.maxpassper5 = maxpassper5;
	}
	public String getMaxpassper6() {
		return maxpassper6;
	}
	public void setMaxpassper6(String maxpassper6) {
		this.maxpassper6 = maxpassper6;
	}
	public String getMaxpassper7() {
		return maxpassper7;
	}
	public void setMaxpassper7(String maxpassper7) {
		this.maxpassper7 = maxpassper7;
	}
	public String getMaxpassper8() {
		return maxpassper8;
	}
	public void setMaxpassper8(String maxpassper8) {
		this.maxpassper8 = maxpassper8;
	}
	public String getMaxpassper9() {
		return maxpassper9;
	}
	public void setMaxpassper9(String maxpassper9) {
		this.maxpassper9 = maxpassper9;
	}
	public String getMaxpassper10() {
		return maxpassper10;
	}
	public void setMaxpassper10(String maxpassper10) {
		this.maxpassper10 = maxpassper10;
	}
	public String getMaxpassper11() {
		return maxpassper11;
	}
	public void setMaxpassper11(String maxpassper11) {
		this.maxpassper11 = maxpassper11;
	}
	public String getMaxpassper12() {
		return maxpassper12;
	}
	public void setMaxpassper12(String maxpassper12) {
		this.maxpassper12 = maxpassper12;
	}
	public String getMaxpassper13() {
		return maxpassper13;
	}
	public void setMaxpassper13(String maxpassper13) {
		this.maxpassper13 = maxpassper13;
	}
	public String getMaxpassper14() {
		return maxpassper14;
	}
	public void setMaxpassper14(String maxpassper14) {
		this.maxpassper14 = maxpassper14;
	}
	public String getMaxpassper15() {
		return maxpassper15;
	}
	public void setMaxpassper15(String maxpassper15) {
		this.maxpassper15 = maxpassper15;
	}
	public String getMinpassper1() {
		return minpassper1;
	}
	public void setMinpassper1(String minpassper1) {
		this.minpassper1 = minpassper1;
	}
	public String getMinpassper2() {
		return minpassper2;
	}
	public void setMinpassper2(String minpassper2) {
		this.minpassper2 = minpassper2;
	}
	public String getMinpassper3() {
		return minpassper3;
	}
	public void setMinpassper3(String minpassper3) {
		this.minpassper3 = minpassper3;
	}
	public String getMinpassper4() {
		return minpassper4;
	}
	public void setMinpassper4(String minpassper4) {
		this.minpassper4 = minpassper4;
	}
	public String getMinpassper5() {
		return minpassper5;
	}
	public void setMinpassper5(String minpassper5) {
		this.minpassper5 = minpassper5;
	}
	public String getMinpassper6() {
		return minpassper6;
	}
	public void setMinpassper6(String minpassper6) {
		this.minpassper6 = minpassper6;
	}
	public String getMinpassper7() {
		return minpassper7;
	}
	public void setMinpassper7(String minpassper7) {
		this.minpassper7 = minpassper7;
	}
	public String getMinpassper8() {
		return minpassper8;
	}
	public void setMinpassper8(String minpassper8) {
		this.minpassper8 = minpassper8;
	}
	public String getMinpassper9() {
		return minpassper9;
	}
	public void setMinpassper9(String minpassper9) {
		this.minpassper9 = minpassper9;
	}
	public String getMinpassper10() {
		return minpassper10;
	}
	public void setMinpassper10(String minpassper10) {
		this.minpassper10 = minpassper10;
	}
	public String getMinpassper11() {
		return minpassper11;
	}
	public void setMinpassper11(String minpassper11) {
		this.minpassper11 = minpassper11;
	}
	public String getMinpassper12() {
		return minpassper12;
	}
	public void setMinpassper12(String minpassper12) {
		this.minpassper12 = minpassper12;
	}
	public String getMinpassper13() {
		return minpassper13;
	}
	public void setMinpassper13(String minpassper13) {
		this.minpassper13 = minpassper13;
	}
	public String getMinpassper14() {
		return minpassper14;
	}
	public void setMinpassper14(String minpassper14) {
		this.minpassper14 = minpassper14;
	}
	public String getMinpassper15() {
		return minpassper15;
	}
	public void setMinpassper15(String minpassper15) {
		this.minpassper15 = minpassper15;
	}
}
