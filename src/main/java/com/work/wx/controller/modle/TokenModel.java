/*
 * work_wx
 * wuhen 2019/12/27.
 * Copyright (c) 2019  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle;

public class TokenModel extends WXStatusModel {

    private String corpId;
    private Integer expires_in;
    private String access_token;
    private Long loseTime;
    private Integer token_type;     //0 应用token  1 通讯录token


    public TokenModel(String corpId,int token_type) {
        this.corpId = corpId;
        this.token_type = token_type;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getLoseTime() {
        return loseTime;
    }

    public void setLoseTime(Long loseTime) {
        this.loseTime = loseTime;
    }

    public Integer getToken_type() {
        return token_type;
    }

    public void setToken_type(Integer token_type) {
        this.token_type = token_type;
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "corpId='" + corpId + '\'' +
                ", expires_in=" + expires_in +
                ", access_token='" + access_token + '\'' +
                ", loseTime=" + loseTime +
                ", token_type=" + token_type +
                "} " + super.toString();
    }



}
