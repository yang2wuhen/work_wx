/*
 * work_wx
 * wuhen 2020/2/4.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle.subChatItem;

import com.work.wx.controller.modle.BaseModel;

public class ChatModelAgree extends BaseModel {

    private String userid;      //	同意/不同意协议者的userid，外部企业默认为external_userid。String类型
    private Long agree_time;    //	同意/不同意协议的时间，utc时间，ms单位。


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Long getAgree_time() {
        return agree_time;
    }

    public void setAgree_time(Long agree_time) {
        this.agree_time = agree_time;
    }
}
