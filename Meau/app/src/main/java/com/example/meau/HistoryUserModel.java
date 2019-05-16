package com.example.meau;

import java.io.Serializable;

public class HistoryUserModel implements Serializable {
    private String uid, hiid, historyDesc;
    private int num;

    public HistoryUserModel() {
    }

    public HistoryUserModel(String uid, String hiid, String historyDesc, int num) {
        this.uid = uid;
        this.hiid = hiid;
        this.historyDesc = historyDesc;
        this.num = num;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHiid() {
        return hiid;
    }

    public void setHiid(String hiid) {
        this.hiid = hiid;
    }

    public String getHistoryDesc() {
        return historyDesc;
    }

    public void setHistoryDesc(String historyDesc) {
        this.historyDesc = historyDesc;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
