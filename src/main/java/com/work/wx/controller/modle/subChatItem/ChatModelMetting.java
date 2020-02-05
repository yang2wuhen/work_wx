/*
 * work_wx
 * wuhen 2020/2/4.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle.subChatItem;

import com.work.wx.controller.modle.BaseModel;

public class ChatModelMetting extends BaseModel {

    private String topic;           //	会议主题。String类型
    private String  starttime;      //	会议开始时间。Utc时间
    private String  endtime;        //	会议结束时间。Utc时间
    private String remarks;         //	会议备注。String类型
    private Integer meetingtype;    //	会议消息类型。101发起会议邀请消息、102处理会议邀请消息。Uint32类型
    private  Long meetingid;        //	会议id。方便将发起、处理消息进行对照。uint64类型
    private Integer status;         //	会议邀请处理状态。1 参加会议、2 拒绝会议、3 待定。Uint32类型

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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

    public Integer getMeetingtype() {
        return meetingtype;
    }

    public void setMeetingtype(Integer meetingtype) {
        this.meetingtype = meetingtype;
    }

    public Long getMeetingid() {
        return meetingid;
    }

    public void setMeetingid(Long meetingid) {
        this.meetingid = meetingid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
