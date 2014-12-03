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
public class WalletHistoryDTO {

    private long cid;
    private String desc;
    private float amount;
    private String cTime;

    public WalletHistoryDTO() {
    }

    public WalletHistoryDTO(long cid, String desc, float amount, String cTime) {
        this.cid = cid;
        this.desc = desc;
        this.amount = amount;
        this.cTime = cTime;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
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

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }
}
