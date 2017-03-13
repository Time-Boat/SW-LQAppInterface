package com.mss.shtoone.domain;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity  
public class Resource implements Serializable {  
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Integer id;  
      
    private String type;  
      
    private String svalue;  
    
    private String name;  
    private String remark; 
      
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSvalue() {
		return svalue;
	}

	public void setSvalue(String svalue) {
		this.svalue = svalue;
	}

      
    /** 
     * The default constructor 
     */  
    public Resource() {  
          
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}  
} 



