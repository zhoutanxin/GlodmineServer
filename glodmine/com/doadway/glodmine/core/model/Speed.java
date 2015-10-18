package com.doadway.glodmine.core.model;

import java.io.Serializable;
import java.util.Date;

public class Speed implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5491744750471773388L;

	private Integer id;

    private Date idate;

    private Integer isource;

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