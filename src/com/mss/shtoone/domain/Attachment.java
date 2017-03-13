package com.mss.shtoone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Attachment implements Serializable{
	
  private static final long serialVersionUID = -492122176065646799L;
  
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int bhzid;
  private String bhzNo;
  private String bhzName;
  private String fileName;
  private String uploadPath;
  private String caozuoyuan;
  private String caozuoyuandianhua;
  private String caozuoriqi;
  private String FileType;
  private String remark;
  
  

public Attachment() {
	super();
}

public Attachment(int id) {
	super();
	this.id = id;
}



public int getId() {
	return id;
}



public void setId(int id) {
	this.id = id;
}



public int getBhzid() {
	return bhzid;
}

public void setBhzid(int bhzid) {
	this.bhzid = bhzid;
}

public String getBhzNo() {
	return bhzNo;
}
public void setBhzNo(String bhzNo) {
	this.bhzNo = bhzNo;
}
public String getBhzName() {
	return bhzName;
}
public void setBhzName(String bhzName) {
	this.bhzName = bhzName;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public String getUploadPath() {
	return uploadPath;
}
public void setUploadPath(String uploadPath) {
	this.uploadPath = uploadPath;
}
public String getCaozuoyuan() {
	return caozuoyuan;
}
public void setCaozuoyuan(String caozuoyuan) {
	this.caozuoyuan = caozuoyuan;
}
public String getCaozuoyuandianhua() {
	return caozuoyuandianhua;
}
public void setCaozuoyuandianhua(String caozuoyuandianhua) {
	this.caozuoyuandianhua = caozuoyuandianhua;
}
public String getCaozuoriqi() {
	return caozuoriqi;
}
public void setCaozuoriqi(String caozuoriqi) {
	this.caozuoriqi = caozuoriqi;
}
public String getFileType() {
	return FileType;
}
public void setFileType(String fileType) {
	FileType = fileType;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
  
  
  
}
