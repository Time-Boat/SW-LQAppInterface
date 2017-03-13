package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class LqshaifenjieguoView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer llid;    
	private String llysb;	
	private String llg1;	
	private String llg2;		
	private String llg3;
	private String llg4;
	private String llg5;
	private String llg6;
	private String llg7;
	private String llf1;
	private String llf2;
	private String lllq;	
	private String lltjj;
	private String llshebeibianhao;
	private String lilunname;
	private String llbuwei;	
	private String llshijian;	
	private String llmoren;	
	private String llbeizhu;

	private Integer id;
	private String xiangmubuminchen;
	private String banhezhanminchen;
	private String jianchen;
	private String gongkongleixin;
	private String gprsbianhao;
	private String shebeichanjia;
	private String jiekouleixin;
	private String gongkongruanjian;
	private String shujukuleixin;
	private String beizhu;
	private String shuliang;
	private String shebeileixin;
	private String simhao;
	private String shoujihao;
	private String botelu;
	private String taocan;
	private String smsbaojin;
	private String smssettype;
	private String smstype;
	private String sendtype;
	private String panshu;
	private String ambegin;
	private String amend;
	private String pmbegin;
	private String pmend;
	private Integer biaoduanid;
	private Integer xiangmubuid;
	private Integer zuoyeduiid;
	private String simpwd;
	
	@Id
	private int jieguoid;
	private Integer lqbianhao;
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
	private Integer sjglsf1;
	private Integer sjglsf2;
	private Integer sjglsf3;
	private Integer sjglsf4;
	private Integer sjglsf5;
	private Integer sjglsf6;
	private Integer sjglsf7;
	private Integer sjflsf1;
	private Integer sjflsf2;
	private String baocunshijian;
	public Integer getSjglsf6() {
		return sjglsf6;
	}
	public void setSjglsf6(Integer sjglsf6) {
		this.sjglsf6 = sjglsf6;
	}
	public Integer getSjglsf7() {
		return sjglsf7;
	}
	public void setSjglsf7(Integer sjglsf7) {
		this.sjglsf7 = sjglsf7;
	}
	public String getBaocunshijian() {
		return baocunshijian;
	}
	public void setBaocunshijian(String baocunshijian) {
		this.baocunshijian = baocunshijian;
	}
	public Integer getLlid() {
		return llid;
	}
	public void setLlid(Integer llid) {
		this.llid = llid;
	}
	public String getLlg1() {
		return llg1;
	}
	public void setLlg1(String llg1) {
		this.llg1 = llg1;
	}
	public String getLlg2() {
		return llg2;
	}
	public void setLlg2(String llg2) {
		this.llg2 = llg2;
	}
	public String getLlg3() {
		return llg3;
	}
	public void setLlg3(String llg3) {
		this.llg3 = llg3;
	}
	public String getLlg4() {
		return llg4;
	}
	public void setLlg4(String llg4) {
		this.llg4 = llg4;
	}
	public String getLlg5() {
		return llg5;
	}
	public void setLlg5(String llg5) {
		this.llg5 = llg5;
	}
	public String getLlf1() {
		return llf1;
	}
	public void setLlf1(String llf1) {
		this.llf1 = llf1;
	}
	public String getLlf2() {
		return llf2;
	}
	public void setLlf2(String llf2) {
		this.llf2 = llf2;
	}
	public String getLlshebeibianhao() {
		return llshebeibianhao;
	}
	public void setLlshebeibianhao(String llshebeibianhao) {
		this.llshebeibianhao = llshebeibianhao;
	}
	public String getLlbuwei() {
		return llbuwei;
	}
	public void setLlbuwei(String llbuwei) {
		this.llbuwei = llbuwei;
	}
	public String getLlmoren() {
		return llmoren;
	}
	public void setLlmoren(String llmoren) {
		this.llmoren = llmoren;
	}
	public String getLlbeizhu() {
		return llbeizhu;
	}
	public void setLlbeizhu(String llbeizhu) {
		this.llbeizhu = llbeizhu;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getXiangmubuminchen() {
		return xiangmubuminchen;
	}
	public void setXiangmubuminchen(String xiangmubuminchen) {
		this.xiangmubuminchen = xiangmubuminchen;
	}
	public String getBanhezhanminchen() {
		return banhezhanminchen;
	}
	public void setBanhezhanminchen(String banhezhanminchen) {
		this.banhezhanminchen = banhezhanminchen;
	}
	public String getJianchen() {
		return jianchen;
	}
	public void setJianchen(String jianchen) {
		this.jianchen = jianchen;
	}
	public String getGongkongleixin() {
		return gongkongleixin;
	}
	public void setGongkongleixin(String gongkongleixin) {
		this.gongkongleixin = gongkongleixin;
	}
	public String getGprsbianhao() {
		return gprsbianhao;
	}
	public void setGprsbianhao(String gprsbianhao) {
		this.gprsbianhao = gprsbianhao;
	}
	public String getShebeichanjia() {
		return shebeichanjia;
	}
	public void setShebeichanjia(String shebeichanjia) {
		this.shebeichanjia = shebeichanjia;
	}
	public String getJiekouleixin() {
		return jiekouleixin;
	}
	public void setJiekouleixin(String jiekouleixin) {
		this.jiekouleixin = jiekouleixin;
	}
	public String getGongkongruanjian() {
		return gongkongruanjian;
	}
	public void setGongkongruanjian(String gongkongruanjian) {
		this.gongkongruanjian = gongkongruanjian;
	}
	public String getShujukuleixin() {
		return shujukuleixin;
	}
	public void setShujukuleixin(String shujukuleixin) {
		this.shujukuleixin = shujukuleixin;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getShuliang() {
		return shuliang;
	}
	public void setShuliang(String shuliang) {
		this.shuliang = shuliang;
	}
	public String getShebeileixin() {
		return shebeileixin;
	}
	public void setShebeileixin(String shebeileixin) {
		this.shebeileixin = shebeileixin;
	}
	public String getSimhao() {
		return simhao;
	}
	public void setSimhao(String simhao) {
		this.simhao = simhao;
	}
	public String getShoujihao() {
		return shoujihao;
	}
	public void setShoujihao(String shoujihao) {
		this.shoujihao = shoujihao;
	}
	public String getBotelu() {
		return botelu;
	}
	public void setBotelu(String botelu) {
		this.botelu = botelu;
	}
	public String getTaocan() {
		return taocan;
	}
	public void setTaocan(String taocan) {
		this.taocan = taocan;
	}
	public String getSmsbaojin() {
		return smsbaojin;
	}
	public void setSmsbaojin(String smsbaojin) {
		this.smsbaojin = smsbaojin;
	}
	public String getSmssettype() {
		return smssettype;
	}
	public void setSmssettype(String smssettype) {
		this.smssettype = smssettype;
	}
	public String getSmstype() {
		return smstype;
	}
	public void setSmstype(String smstype) {
		this.smstype = smstype;
	}
	public String getSendtype() {
		return sendtype;
	}
	public void setSendtype(String sendtype) {
		this.sendtype = sendtype;
	}
	public String getPanshu() {
		return panshu;
	}
	public void setPanshu(String panshu) {
		this.panshu = panshu;
	}
	public String getAmbegin() {
		return ambegin;
	}
	public void setAmbegin(String ambegin) {
		this.ambegin = ambegin;
	}
	public String getAmend() {
		return amend;
	}
	public void setAmend(String amend) {
		this.amend = amend;
	}
	public String getPmbegin() {
		return pmbegin;
	}
	public void setPmbegin(String pmbegin) {
		this.pmbegin = pmbegin;
	}
	public String getPmend() {
		return pmend;
	}
	public void setPmend(String pmend) {
		this.pmend = pmend;
	}
	public Integer getBiaoduanid() {
		return biaoduanid;
	}
	public void setBiaoduanid(Integer biaoduanid) {
		this.biaoduanid = biaoduanid;
	}
	public Integer getXiangmubuid() {
		return xiangmubuid;
	}
	public void setXiangmubuid(Integer xiangmubuid) {
		this.xiangmubuid = xiangmubuid;
	}
	public Integer getZuoyeduiid() {
		return zuoyeduiid;
	}
	public void setZuoyeduiid(Integer zuoyeduiid) {
		this.zuoyeduiid = zuoyeduiid;
	}
	public String getSimpwd() {
		return simpwd;
	}
	public void setSimpwd(String simpwd) {
		this.simpwd = simpwd;
	}
	public int getJieguoid() {
		return jieguoid;
	}
	public void setJieguoid(int jieguoid) {
		this.jieguoid = jieguoid;
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
	public String getLlysb() {
		return llysb;
	}
	public void setLlysb(String llysb) {
		this.llysb = llysb;
	}
	public String getLlg6() {
		return llg6;
	}
	public void setLlg6(String llg6) {
		this.llg6 = llg6;
	}
	public String getLlg7() {
		return llg7;
	}
	public void setLlg7(String llg7) {
		this.llg7 = llg7;
	}
	public String getLllq() {
		return lllq;
	}
	public void setLllq(String lllq) {
		this.lllq = lllq;
	}
	public String getLltjj() {
		return lltjj;
	}
	public void setLltjj(String lltjj) {
		this.lltjj = lltjj;
	}
	public String getLilunname() {
		return lilunname;
	}
	public void setLilunname(String lilunname) {
		this.lilunname = lilunname;
	}
	public String getLlshijian() {
		return llshijian;
	}
	public void setLlshijian(String llshijian) {
		this.llshijian = llshijian;
	}
	public void setLlid(int llid) {
		this.llid = llid;
	}
	public Integer getLqbianhao() {
		return lqbianhao;
	}
	public void setLqbianhao(Integer lqbianhao) {
		this.lqbianhao = lqbianhao;
	}
}
