/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle;

import java.math.BigInteger;

public class ChatRecordID {
    private Long seq;
    private String corpId;
    private String msgid;
    private Long  getTime;
    private Integer publickey_ver;

    public ChatRecordID(String corpId) {
        this.corpId = corpId;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public Long getGetTime() {
        return getTime;
    }

    public void setGetTime(Long getTime) {
        this.getTime = getTime;
    }

    public Integer getPublickey_ver() {
        return publickey_ver;
    }

    public void setPublickey_ver(Integer publickey_ver) {
        this.publickey_ver = publickey_ver;
    }

    @Override
    public String toString() {
        return "ChatRecordID{" +
                "seq=" + seq +
                ", corpId='" + corpId + '\'' +
                ", msgid='" + msgid + '\'' +
                ", getTime=" + getTime +
                ", publickey_ver=" + publickey_ver +
                '}';
    }
}
