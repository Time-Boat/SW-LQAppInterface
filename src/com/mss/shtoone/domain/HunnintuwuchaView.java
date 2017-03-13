package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HunnintuwuchaView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5375270635253238476L;
	private String banhezhanminchen;
	private String jianchen;
	private String smsbaojin;
	@Id
	private int bianhao;
	
	private String glw1;
	private String glw2;
	private String glw3;
	private String glw4;
	private String glw5;
	private String flw1;
	private String flw2;
	private String flw3;
	private String flw4;
	private String flw5;
	private String flw6;
	private String shw1;
	private String shw2;
	private String wjw1;
	private String wjw2;
	private String wjw3;
	private String wjw4;

	private int id;
	private String xiangmubuminchen;
	private String gongkongleixin;
	private String gprsbianhao;
	private String shebeichanjia;
	private String jiekouleixin;
	private String gongkongruanjian;
	private String shujukuleixin;
	private String beizhu;
	private String shuliang;
	private Integer biaoduanid;
	private Integer xiangmubuid;
	private Integer zuoyeduiid;

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

	public String getGongdanhao() {
		return gongdanhao;
	}

	public void setGongdanhao(String gongdanhao) {
		this.gongdanhao = gongdanhao;
	}

	public String getChaozuozhe() {
		return chaozuozhe;
	}

	public void setChaozuozhe(String chaozuozhe) {
		this.chaozuozhe = chaozuozhe;
	}

	public String getSha1_shijizhi() {
		return sha1_shijizhi;
	}

	public void setSha1_shijizhi(String sha1Shijizhi) {
		sha1_shijizhi = sha1Shijizhi;
	}

	public String getShi1_shijizhi() {
		return shi1_shijizhi;
	}

	public void setShi1_shijizhi(String shi1Shijizhi) {
		shi1_shijizhi = shi1Shijizhi;
	}

	public String getShi2_shijizhi() {
		return shi2_shijizhi;
	}

	public void setShi2_shijizhi(String shi2Shijizhi) {
		shi2_shijizhi = shi2Shijizhi;
	}

	public String getSha2_shijizhi() {
		return sha2_shijizhi;
	}

	public void setSha2_shijizhi(String sha2Shijizhi) {
		sha2_shijizhi = sha2Shijizhi;
	}

	public String getGuliao5_shijizhi() {
		return guliao5_shijizhi;
	}

	public void setGuliao5_shijizhi(String guliao5Shijizhi) {
		guliao5_shijizhi = guliao5Shijizhi;
	}

	public String getShuini1_shijizhi() {
		return shuini1_shijizhi;
	}

	public void setShuini1_shijizhi(String shuini1Shijizhi) {
		shuini1_shijizhi = shuini1Shijizhi;
	}

	public String getShuini2_shijizhi() {
		return shuini2_shijizhi;
	}

	public void setShuini2_shijizhi(String shuini2Shijizhi) {
		shuini2_shijizhi = shuini2Shijizhi;
	}

	public String getKuangfen3_shijizhi() {
		return kuangfen3_shijizhi;
	}

	public void setKuangfen3_shijizhi(String kuangfen3Shijizhi) {
		kuangfen3_shijizhi = kuangfen3Shijizhi;
	}

	public String getFeimeihui4_shijizhi() {
		return feimeihui4_shijizhi;
	}

	public void setFeimeihui4_shijizhi(String feimeihui4Shijizhi) {
		feimeihui4_shijizhi = feimeihui4Shijizhi;
	}

	public String getFenliao5_shijizhi() {
		return fenliao5_shijizhi;
	}

	public void setFenliao5_shijizhi(String fenliao5Shijizhi) {
		fenliao5_shijizhi = fenliao5Shijizhi;
	}

	public String getFenliao6_shijizhi() {
		return fenliao6_shijizhi;
	}

	public void setFenliao6_shijizhi(String fenliao6Shijizhi) {
		fenliao6_shijizhi = fenliao6Shijizhi;
	}

	public String getShui1_shijizhi() {
		return shui1_shijizhi;
	}

	public void setShui1_shijizhi(String shui1Shijizhi) {
		shui1_shijizhi = shui1Shijizhi;
	}

	public String getShui2_shijizhi() {
		return shui2_shijizhi;
	}

	public void setShui2_shijizhi(String shui2Shijizhi) {
		shui2_shijizhi = shui2Shijizhi;
	}

	public String getWaijiaji1_shijizhi() {
		return waijiaji1_shijizhi;
	}

	public void setWaijiaji1_shijizhi(String waijiaji1Shijizhi) {
		waijiaji1_shijizhi = waijiaji1Shijizhi;
	}

	public String getWaijiaji2_shijizhi() {
		return waijiaji2_shijizhi;
	}

	public void setWaijiaji2_shijizhi(String waijiaji2Shijizhi) {
		waijiaji2_shijizhi = waijiaji2Shijizhi;
	}

	public String getWaijiaji3_shijizhi() {
		return waijiaji3_shijizhi;
	}

	public void setWaijiaji3_shijizhi(String waijiaji3Shijizhi) {
		waijiaji3_shijizhi = waijiaji3Shijizhi;
	}

	public String getWaijiaji4_shijizhi() {
		return waijiaji4_shijizhi;
	}

	public void setWaijiaji4_shijizhi(String waijiaji4Shijizhi) {
		waijiaji4_shijizhi = waijiaji4Shijizhi;
	}

	public String getChuliaoshijian() {
		return chuliaoshijian;
	}

	public void setChuliaoshijian(String chuliaoshijian) {
		this.chuliaoshijian = chuliaoshijian;
	}

	public String getSha1_lilunzhi() {
		return sha1_lilunzhi;
	}

	public void setSha1_lilunzhi(String sha1Lilunzhi) {
		sha1_lilunzhi = sha1Lilunzhi;
	}

	public String getShi1_lilunzhi() {
		return shi1_lilunzhi;
	}

	public void setShi1_lilunzhi(String shi1Lilunzhi) {
		shi1_lilunzhi = shi1Lilunzhi;
	}

	public String getSha2_lilunzhi() {
		return sha2_lilunzhi;
	}

	public void setSha2_lilunzhi(String sha2Lilunzhi) {
		sha2_lilunzhi = sha2Lilunzhi;
	}

	public String getGuliao5_lilunzhi() {
		return guliao5_lilunzhi;
	}

	public void setGuliao5_lilunzhi(String guliao5Lilunzhi) {
		guliao5_lilunzhi = guliao5Lilunzhi;
	}

	public String getShuini1_lilunzhi() {
		return shuini1_lilunzhi;
	}

	public void setShuini1_lilunzhi(String shuini1Lilunzhi) {
		shuini1_lilunzhi = shuini1Lilunzhi;
	}

	public String getShuini2_lilunzhi() {
		return shuini2_lilunzhi;
	}

	public void setShuini2_lilunzhi(String shuini2Lilunzhi) {
		shuini2_lilunzhi = shuini2Lilunzhi;
	}

	public String getKuangfen3_lilunzhi() {
		return kuangfen3_lilunzhi;
	}

	public void shijizhi(String kuangfen3Lilunzhi) {
		kuangfen3_lilunzhi = kuangfen3Lilunzhi;
	}

	public String getFeimeihui4_lilunzhi() {
		return feimeihui4_lilunzhi;
	}

	public void setFeimeihui4_lilunzhi(String feimeihui4Lilunzhi) {
		feimeihui4_lilunzhi = feimeihui4Lilunzhi;
	}

	public String getFenliao5_lilunzhi() {
		return fenliao5_lilunzhi;
	}

	public void setFenliao5_lilunzhi(String fenliao5Lilunzhi) {
		fenliao5_lilunzhi = fenliao5Lilunzhi;
	}

	public String getFenliao6_lilunzhi() {
		return fenliao6_lilunzhi;
	}

	public void setFenliao6_lilunzhi(String fenliao6Lilunzhi) {
		fenliao6_lilunzhi = fenliao6Lilunzhi;
	}

	public String getShui1_lilunzhi() {
		return shui1_lilunzhi;
	}

	public void setShui1_lilunzhi(String shui1Lilunzhi) {
		shui1_lilunzhi = shui1Lilunzhi;
	}

	public String getShui2_lilunzhi() {
		return shui2_lilunzhi;
	}

	public void setShui2_lilunzhi(String shui2Lilunzhi) {
		shui2_lilunzhi = shui2Lilunzhi;
	}

	public String getWaijiaji1_lilunzhi() {
		return waijiaji1_lilunzhi;
	}

	public void setWaijiaji1_lilunzhi(String waijiaji1Lilunzhi) {
		waijiaji1_lilunzhi = waijiaji1Lilunzhi;
	}

	public String getWaijiaji2_lilunzhi() {
		return waijiaji2_lilunzhi;
	}

	public void setWaijiaji2_lilunzhi(String waijiaji2Lilunzhi) {
		waijiaji2_lilunzhi = waijiaji2Lilunzhi;
	}

	public String getWaijiaji3_lilunzhi() {
		return waijiaji3_lilunzhi;
	}

	public void setWaijiaji3_lilunzhi(String waijiaji3Lilunzhi) {
		waijiaji3_lilunzhi = waijiaji3Lilunzhi;
	}

	public String getWaijiaji4_lilunzhi() {
		return waijiaji4_lilunzhi;
	}

	public void setWaijiaji4_lilunzhi(String waijiaji4Lilunzhi) {
		waijiaji4_lilunzhi = waijiaji4Lilunzhi;
	}

	public String getGongchengmingcheng() {
		return gongchengmingcheng;
	}

	public void setGongchengmingcheng(String gongchengmingcheng) {
		this.gongchengmingcheng = gongchengmingcheng;
	}

	public String getSigongdidian() {
		return sigongdidian;
	}

	public void setSigongdidian(String sigongdidian) {
		this.sigongdidian = sigongdidian;
	}

	public String getJiaozuobuwei() {
		return jiaozuobuwei;
	}

	public void setJiaozuobuwei(String jiaozuobuwei) {
		this.jiaozuobuwei = jiaozuobuwei;
	}

	public String getShuinipingzhong() {
		return shuinipingzhong;
	}

	public void setShuinipingzhong(String shuinipingzhong) {
		this.shuinipingzhong = shuinipingzhong;
	}

	public String getWaijiajipingzhong() {
		return waijiajipingzhong;
	}

	public void setWaijiajipingzhong(String waijiajipingzhong) {
		this.waijiajipingzhong = waijiajipingzhong;
	}

	public String getPeifanghao() {
		return peifanghao;
	}

	public void setPeifanghao(String peifanghao) {
		this.peifanghao = peifanghao;
	}

	public String getQiangdudengji() {
		return qiangdudengji;
	}

	public void setQiangdudengji(String qiangdudengji) {
		this.qiangdudengji = qiangdudengji;
	}

	public String getJiaobanshijian() {
		return jiaobanshijian;
	}

	public void setJiaobanshijian(String jiaobanshijian) {
		this.jiaobanshijian = jiaobanshijian;
	}

	public String getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}

	public String getBaocunshijian() {
		return baocunshijian;
	}

	public void setBaocunshijian(String baocunshijian) {
		this.baocunshijian = baocunshijian;
	}

	public String getBiaoshi() {
		return biaoshi;
	}

	public void setBiaoshi(String biaoshi) {
		this.biaoshi = biaoshi;
	}

	public String getCaijishijian() {
		return caijishijian;
	}

	public void setCaijishijian(String caijishijian) {
		this.caijishijian = caijishijian;
	}

	private String gongdanhao;	


	private String chaozuozhe;	

	private String sha1_shijizhi;		

	private String shi1_shijizhi;
	
	private String shi2_shijizhi;
	
	private String sha2_shijizhi;
	
	private String guliao5_shijizhi;
	
	private String shuini1_shijizhi;
	
	private String shuini2_shijizhi;
	
	private String kuangfen3_shijizhi;
	
	private String feimeihui4_shijizhi;	

	private String fenliao5_shijizhi;
	
	private String fenliao6_shijizhi;
	
	private String shui1_shijizhi;
	
	private String shui2_shijizhi;	
	
	private String waijiaji1_shijizhi;	
	
	private String waijiaji2_shijizhi;	
	
	private String waijiaji3_shijizhi;	
	
	private String waijiaji4_shijizhi;	
	
	private String chuliaoshijian;	
	
	private String sha1_lilunzhi;
	
	private String shi1_lilunzhi;	
	
	private String sha2_lilunzhi;
	
	private String shi2_lilunzhi;
	
	private String guliao5_lilunzhi;
	
	private String shuini1_lilunzhi;
	
	private String shuini2_lilunzhi;
	
	private String kuangfen3_lilunzhi;
	
	private String feimeihui4_lilunzhi;
	
	private String fenliao5_lilunzhi;
	
	private String fenliao6_lilunzhi;
	
	private String shui1_lilunzhi;
	
	private String shui2_lilunzhi;
	
	private String waijiaji1_lilunzhi;
	
	private String waijiaji2_lilunzhi;
	
	private String waijiaji3_lilunzhi;
	
	private String waijiaji4_lilunzhi;
	
	private String gongchengmingcheng;
	
	private String sigongdidian;
	
	private String jiaozuobuwei;
	
	private String shuinipingzhong;
	
	private String waijiajipingzhong;
	
	private String peifanghao;
	
	private String qiangdudengji;
	
	private String jiaobanshijian;
	
	private String shebeibianhao;
	
	private String baocunshijian;
	
	private String biaoshi;
	
	private String caijishijian;
	
	private String gujifangshu;

	public String getShi2_lilunzhi() {
		return shi2_lilunzhi;
	}

	public void setShi2_lilunzhi(String shi2Lilunzhi) {
		shi2_lilunzhi = shi2Lilunzhi;
	}

	public void setKuangfen3_lilunzhi(String kuangfen3Lilunzhi) {
		kuangfen3_lilunzhi = kuangfen3Lilunzhi;
	}

	public String getJianchen() {
		return jianchen;
	}

	public void setJianchen(String jianchen) {
		this.jianchen = jianchen;
	}

	public String getSmsbaojin() {
		return smsbaojin;
	}

	public void setSmsbaojin(String smsbaojin) {
		this.smsbaojin = smsbaojin;
	}

	public String getGlw1() {
		return glw1;
	}

	public void setGlw1(String glw1) {
		this.glw1 = glw1;
	}

	public String getGlw2() {
		return glw2;
	}

	public void setGlw2(String glw2) {
		this.glw2 = glw2;
	}

	public String getGlw3() {
		return glw3;
	}

	public void setGlw3(String glw3) {
		this.glw3 = glw3;
	}

	public String getGlw4() {
		return glw4;
	}

	public void setGlw4(String glw4) {
		this.glw4 = glw4;
	}

	public String getGlw5() {
		return glw5;
	}

	public void setGlw5(String glw5) {
		this.glw5 = glw5;
	}

	public String getFlw1() {
		return flw1;
	}

	public void setFlw1(String flw1) {
		this.flw1 = flw1;
	}

	public String getFlw2() {
		return flw2;
	}

	public void setFlw2(String flw2) {
		this.flw2 = flw2;
	}

	public String getFlw3() {
		return flw3;
	}

	public void setFlw3(String flw3) {
		this.flw3 = flw3;
	}

	public String getFlw4() {
		return flw4;
	}

	public void setFlw4(String flw4) {
		this.flw4 = flw4;
	}

	public String getFlw5() {
		return flw5;
	}

	public void setFlw5(String flw5) {
		this.flw5 = flw5;
	}

	public String getFlw6() {
		return flw6;
	}

	public void setFlw6(String flw6) {
		this.flw6 = flw6;
	}

	public String getShw1() {
		return shw1;
	}

	public void setShw1(String shw1) {
		this.shw1 = shw1;
	}

	public String getShw2() {
		return shw2;
	}

	public void setShw2(String shw2) {
		this.shw2 = shw2;
	}

	public String getWjw1() {
		return wjw1;
	}

	public void setWjw1(String wjw1) {
		this.wjw1 = wjw1;
	}

	public String getWjw2() {
		return wjw2;
	}

	public void setWjw2(String wjw2) {
		this.wjw2 = wjw2;
	}

	public String getWjw3() {
		return wjw3;
	}

	public void setWjw3(String wjw3) {
		this.wjw3 = wjw3;
	}

	public String getWjw4() {
		return wjw4;
	}

	public void setWjw4(String wjw4) {
		this.wjw4 = wjw4;
	}

	public String getGujifangshu() {
		return gujifangshu;
	}

	public void setGujifangshu(String gujifangshu) {
		this.gujifangshu = gujifangshu;
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
	
	
}
