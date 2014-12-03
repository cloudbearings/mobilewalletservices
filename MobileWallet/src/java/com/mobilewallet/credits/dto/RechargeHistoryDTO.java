/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.credits.dto;

/**
 *
 * @author Gopi
 */
public class RechargeHistoryDTO {

    private long did;
    private String desc;
    private float amount;
    private String code;
    private String status;
    private String dTime;

    public RechargeHistoryDTO() {
    }

    public RechargeHistoryDTO(long did, String desc, float amount, String code, String status, String dTime) {
        this.did = did;
        this.desc = desc;
        this.amount = amount;
        this.code = code;
        this.status = status;
        this.dTime = dTime;
    }

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getdTime() {
        return dTime;
    }

    public void setdTime(String dTime) {
        this.dTime = dTime;
    }

}
