package com.doadway.glodmine.core.model;

import java.io.Serializable;
import java.util.Date;

public class Income implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8796590910433489214L;

	private Integer id;

    private Date idate;

    private Integer isource;
    
    private String icategory;
    
    private Float imoney;

    private String imemo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getIdate() {
        return idate;
    }

    public void setIdate(Date idate) {
        this.idate = idate;
    }

    public Integer getIsource() {
        return isource;
    }

    public String getIcategory() {
		return icategory;
	}

	public void setIcategory(String icategory) {
		this.icategory = icategory;
	}

	public void setIsource(Integer isource) {
        this.isource = isource;
    }

    public Float getImoney() {
        return imoney;
    }

    public void setImoney(Float imoney) {
        this.imoney = imoney;
    }

    public String getImemo() {
        return imemo;
    }

    public void setImemo(String imemo) {
        this.imemo = imemo == null ? null : imemo.trim();
    }
}