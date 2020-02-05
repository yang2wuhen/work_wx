/*
 * work_wx
 * wuhen 2020/2/4.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle.subChatItem;


public class ChatModeRecordItem {

    private String type;	// 每条聊天记录的具体消息类型：ChatRecordText/ ChatRecordFile/ ChatRecordImage/ ChatRecordVideo/ ChatRecordLink/
                            // ChatRecordLocation ….
    private Long msgtime;   //	消息时间，utc时间，ms单位。
    private String content; //	消息内容。Json串，内容为对应类型的json。String类型
    private Boolean from_chatroom; //	是否来自群会话。Bool类型


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Boolean getFrom_chatroom() {
        return from_chatroom;
    }

    public void setFrom_chatroom(Boolean from_chatroom) {
        this.from_chatroom = from_chatroom;
    }

    @Override
    public String toString() {
        return "ChatItem{" +
                "type='" + type + '\'' +
                ", msgtime=" + msgtime +
                ", content='" + content + '\'' +
                ", from_chatroom=" + from_chatroom +
                '}';
    }
}
