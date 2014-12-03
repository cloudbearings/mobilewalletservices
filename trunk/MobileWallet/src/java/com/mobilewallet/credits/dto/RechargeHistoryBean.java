/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.credits.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gopi
 */
@XmlRootElement
public class RechargeHistoryBean {

    private List<RechargeHistoryDTO> dtrList;
    private int count;
    private int begin;
    private int end;
    private String non;

    public List<RechargeHistoryDTO> getDtrList() {
        return dtrList;
    }

    public void setDtrList(List<RechargeHistoryDTO> dtrList) {
        this.dtrList = dtrList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getNon() {
        return non;
    }

    public void setNon(String non) {
        this.non = non;
    }

}
