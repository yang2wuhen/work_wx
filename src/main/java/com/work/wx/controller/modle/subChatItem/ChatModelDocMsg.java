/*
 * work_wx
 * wuhen 2020/2/4.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle.subChatItem;

import com.work.wx.controller.modle.BaseModel;

public class ChatModelDocMsg extends BaseModel {

    private String doc_creator;     //	在线文档创建者。本企业成员创建为userid；外部企业成员创建为external_userid
    private String title;
    private String link_url;

    public String getDoc_creator() {
        return doc_creator;
    }

    public void setDoc_creator(String doc_creator) {
        this.doc_creator = doc_creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }
}
