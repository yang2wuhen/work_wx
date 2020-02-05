/*
 * work_wx
 * wuhen 2020/2/4.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle.subChatItem;

import com.work.wx.controller.modle.BaseModel;

public class ChatModelRedPacket extends BaseModel {


    private String wish;	        //  红包祝福语。String类型
    private Integer totalcnt;       //	红包总个数。Uint32类型
    private Integer totalamount;    //	红包总金额。Uint32类型，单位为分。
    private Integer type;

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public Integer getTotalcnt() {
        return totalcnt;
    }

    public void setTotalcnt(Integer totalcnt) {
        this.totalcnt = totalcnt;
    }

    public Integer getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Integer totalamount) {
        this.totalamount = totalamount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
