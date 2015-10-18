package com.doadway.glodmine.core.model;

import java.io.Serializable;

public class IncomeType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7160409093057545776L;

	private Integer id;

    private String icategory;

    private String says;

    private Integer memberId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcategory() {
        return icategory;
    }

    public void setIcategory(String icategory) {
        this.icategory = icategory == null ? null : icategory.trim();
    }

    public String getSays() {
        return says;
    }

    public void setSays(String says) {
        this.says = says == null ? null : says.trim();
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}