package com.mss.shtoone.app.domain;

import java.io.Serializable;

/**沥青超标统计实体类*/
@SuppressWarnings("serial")
public class AppInterfaceChaobiaoEntity implements Serializable{
	
		private String changliang;
		private String panshu;
		private String dengji;
		private String cbps;
		private String cblv;
		private String reallv;
		private String shebeibianhao;
		private String banhezhanminchen;
		private String deptName;
		
		private String bhjCount;
		private String bhzCount;
		
		
		public String getBhjCount() {
			return bhjCount;
		}
		public void setBhjCount(String bhjCount) {
			this.bhjCount = bhjCount;
		}
		public String getBhzCount() {
			return bhzCount;
		}
		public void setBhzCount(String bhzCount) {
			this.bhzCount = bhzCount;
		}
		//组织机构ID
		private String deptId;
		public String getDeptName() {
			return deptName;
		}
		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}
		public String getDeptId() {
			return deptId;
		}
		public void setDeptId(String deptId) {
			this.deptId = deptId;
		}
		public String getReallv() {
			return reallv;
		}
		public void setReallv(String reallv) {
			this.reallv = reallv;
		}
		public String getDengji() {
			return dengji;
		}
		public void setDengji(String dengji) {
			this.dengji = dengji;
		}
		public String getBanhezhanminchen() {
			return banhezhanminchen;
		}
		public void setBanhezhanminchen(String banhezhanminchen) {
			this.banhezhanminchen = banhezhanminchen;
		}
		public String getChangliang() {
			return changliang;
		}
		public void setChangliang(String changliang) {
			this.changliang = changliang;
		}
		public String getPanshu() {
			return panshu;
		}
		public void setPanshu(String panshu) {
			this.panshu = panshu;
		}
		public String getCbps() {
			return cbps;
		}
		public void setCbps(String cbps) {
			this.cbps = cbps;
		}
		public String getCblv() {
			return cblv;
		}
		public void setCblv(String cblv) {
			this.cblv = cblv;
		}
		public String getShebeibianhao() {
			return shebeibianhao;
		}
		public void setShebeibianhao(String shebeibianhao) {
			this.shebeibianhao = shebeibianhao;
		}
	}
