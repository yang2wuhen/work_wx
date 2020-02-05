/*
 * work_wx
 * wuhen 2020/2/4.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle.subChatItem;

import com.work.wx.controller.modle.BaseModel;

public class ChatModelCalendar extends BaseModel {


    private String creatorname;     //	日程组织者
    private String attendeename;    //	日程参与人
    private String place;           //	日程地点

    private String title;           //	日程主题
    private String starttime;       //	日程开始时间
    private String endtime;         //	日程结束时间
    private String remarks;         //	日程备注

    public String getCreatorname() {
        return creatorname;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public String getAttendeename() {
        return attendeename;
    }

    public void setAttendeename(String attendeename) {
        this.attendeename = attendeename;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
