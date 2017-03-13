package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Shuiwenxixxjieguo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8109534041418581400L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jieguoid;
	private Integer swbianhao;
	private String jieguoshijian;	
	private String sjg1;
	private String sjg2;
	private String sjg3;	
	private String sjg4;
	private String sjg5;
	private String sjg6;	
	private String sjf1;	
	private String sjf2;	
	private String sjshui;	
	private String beiy1;	
	private String beiy2;	
	private String beiy3;	
	private Integer leiji;
	private String leixing;
	//施工单位
	private String wentiyuanyin;
	private String chulishijian;
	private String chulijieguo;
	private String chulifangshi;
	private String chuliren;
	//监理
	private String jianlishenpi;  //监理意见
	private String shenpidate;    //见证时间
	private String jianliren;     //监理人
	//业主
	private String yezhuyijian;   //业主意见
	private String confirmdate;   //处理时间
	private String yezhuren;      //业主人
	
	private String filepath;
	private String beizhu;
	private String jianliresult;   //暂时未使用字段
	public String getJianliren() {
		return jianliren;
	}

	public void setJianliren(String jianliren) {
		this.jianliren = jianliren;
	}

	public String getYezhuyijian() {
		return yezhuyijian;
	}

	public void setYezhuyijian(String yezhuyijian) {
		this.yezhuyijian = yezhuyijian;
	}

	public String getYezhuren() {
		return yezhuren;
	}

	public void setYezhuren(String yezhuren) {
		this.yezhuren = yezhuren;
	}

	public String getWentiyuanyin() {
		return wentiyuanyin;
	}

	public void setWentiyuanyin(String wentiyuanyin) {
		this.wentiyuanyin = wentiyuanyin;
	}

	public String getChulishijian() {
		return chulishijian;
	}

	public void setChulishijian(String chulishijian) {
		this.chulishijian = chulishijian;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getChulifangshi() {
		return chulifangshi;
	}

	public void setChulifangshi(String chulifangshi) {
		this.chulifangshi = chulifangshi;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getConfirmdate() {
		return confirmdate;
	}

	public void setConfirmdate(String confirmdate) {
		this.confirmdate = confirmdate;
	}

	public String getJianliresult() {
		return jianliresult;
	}

	public void setJianliresult(String jianliresult) {
		this.jianliresult = jianliresult;
	}

	public String getJianlishenpi() {
		return jianlishenpi;
	}

	public void setJianlishenpi(String jianlishenpi) {
		this.jianlishenpi = jianlishenpi;
	}

	public String getShenpidate() {
		return shenpidate;
	}

	public void setShenpidate(String shenpidate) {
		this.shenpidate = shenpidate;
	}

	public String getChuliren() {
		return chuliren;
	}

	public void setChuliren(String chuliren) {
		this.chuliren = chuliren;
	}

	public void setSwbianhao(Integer swbianhao) {
		this.swbianhao = swbianhao;
	}

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	public int getJieguoid() {
		return jieguoid;
	}

	public void setJieguoid(int jieguoid) {
		this.jieguoid = jieguoid;
	}

	public int getSwbianhao() {
		return swbianhao;
	}

	public void setSwbianhao(int swbianhao) {
		this.swbianhao = swbianhao;
	}

	public String getJieguoshijian() {
		return jieguoshijian;
	}

	public void setJieguoshijian(String jieguoshijian) {
		this.jieguoshijian = jieguoshijian;
	}

	public String getSjg1() {
		return sjg1;
	}

	public void setSjg1(String sjg1) {
		this.sjg1 = sjg1;
	}

	public String getSjg2() {
		return sjg2;
	}

	public void setSjg2(String sjg2) {
		this.sjg2 = sjg2;
	}

	public String getSjg3() {
		return sjg3;
	}

	public void setSjg3(String sjg3) {
		this.sjg3 = sjg3;
	}

	public String getSjg4() {
		return sjg4;
	}

	public void setSjg4(String sjg4) {
		this.sjg4 = sjg4;
	}

	public String getSjg5() {
		return sjg5;
	}

	public void setSjg5(String sjg5) {
		this.sjg5 = sjg5;
	}

	public String getSjg6() {
		return sjg6;
	}

	public void setSjg6(String sjg6) {
		this.sjg6 = sjg6;
	}

	public String getSjf1() {
		return sjf1;
	}

	public void setSjf1(String sjf1) {
		this.sjf1 = sjf1;
	}

	public String getSjf2() {
		return sjf2;
	}

	public void setSjf2(String sjf2) {
		this.sjf2 = sjf2;
	}

	public String getSjshui() {
		return sjshui;
	}

	public void setSjshui(String sjshui) {
		this.sjshui = sjshui;
	}

	public String getBeiy1() {
		return beiy1;
	}

	public void setBeiy1(String beiy1) {
		this.beiy1 = beiy1;
	}

	public String getBeiy2() {
		return beiy2;
	}

	public void setBeiy2(String beiy2) {
		this.beiy2 = beiy2;
	}

	public String getBeiy3() {
		return beiy3;
	}

	public void setBeiy3(String beiy3) {
		this.beiy3 = beiy3;
	}

	public Integer getLeiji() {
		return leiji;
	}

	public void setLeiji(Integer leiji) {
		this.leiji = leiji;
	}

	public String getChulijieguo() {
		return chulijieguo;
	}

	public void setChulijieguo(String chulijieguo) {
		this.chulijieguo = chulijieguo;
	}	

}
