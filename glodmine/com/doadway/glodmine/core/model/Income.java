package com.doadway.glodmine.core.model;

import java.util.Date;

public class Income {
    private Integer id;

    private Date idate;

    private Integer isource;

    private Float imoney;

    private String imomo;

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

    public String getImomo() {
        return imomo;
    }

    public void setImomo(String imomo) {
        this.imomo = imomo == null ? null : imomo.trim();
    }
}