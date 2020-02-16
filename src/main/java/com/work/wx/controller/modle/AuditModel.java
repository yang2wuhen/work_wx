/*
 * work_wx
 * wuhen 2020/2/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle;


public class AuditModel extends BaseModel {

    private String corpId;
    private Long seq;

    private String msgid;       //	消息id，消息的唯一标识，企业可以使用此字段进行消息去重。String类型
    private String action;	    // 消息动作，目前有send(发送消息)/recall(撤回消息)/switch(切换企业日志)三种类型。String类型
    private String from;   	    // 消息发送方id。同一企业内容为userid，非相同企业为external_userid。消息如果是机器人发出，也为external_userid。String类型
    private String[] tolist;      //	消息接收方列表，可能是多个，同一个企业内容为userid，非相同企业为external_userid。数组，内容为string类型
    private String roomid;      //	群聊消息的群id。如果是单聊则为空。String类型
    private Long msgtime;       //	消息发送时间戳，utc时间，ms单位。
    private String content;     //  消息内容
    private Long insertTime;    //  写入记录数据库时间

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTolist() {
        return tolist;
    }

    public void setTolist(String[] tolist) {
        this.tolist = tolist;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public Long getMsgtime() {
        return msgtime;
    }

    public void setMsgtime(Long msgtime) {
        this.msgtime = msgtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Long insertTime) {
        this.insertTime = insertTime;
    }
}
