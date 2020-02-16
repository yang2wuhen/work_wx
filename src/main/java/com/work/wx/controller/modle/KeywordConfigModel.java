/*
 * work_wx
 * wuhen 2020/2/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle;

public class KeywordConfigModel extends BaseModel {

    private String corp;
    private String[] keywords;
    private Long insertTime;

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public KeywordConfigModel() {
    }

    public KeywordConfigModel(String corp) {
        this.corp = corp;
    }

    public Long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Long insertTime) {
        this.insertTime = insertTime;
    }
}
