package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EnvironmentView implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Integer fensuid;
	private String fensu;
	private String wendu;
	private String shidu;
	private String fensuno;
	private String fensushijian;
	private String biaoshi;
	private int id;
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
	public Integer getFensuid() {
		return fensuid;
	}
	public void setFensuid(Integer fensuid) {
		this.fensuid = fensuid;
	}
	public String getFensu() {
		return fensu;
	}
	public void setFensu(String fensu) {
		this.fensu = fensu;
	}
	public String getWendu() {
		return wendu;
	}
	public void setWendu(String wendu) {
		this.wendu = wendu;
	}
	public String getShidu() {
		return shidu;
	}
	public void setShidu(String shidu) {
		this.shidu = shidu;
	}
	public String getFensuno() {
		return fensuno;
	}
	public void setFensuno(String fensuno) {
		this.fensuno = fensuno;
	}
	public String getFensushijian() {
		return fensushijian;
	}
	public void setFensushijian(String fensushijian) {
		this.fensushijian = fensushijian;
	}
	public String getBiaoshi() {
		return biaoshi;
	}
	public void setBiaoshi(String biaoshi) {
		this.biaoshi = biaoshi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
}
