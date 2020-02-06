/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle;


import com.work.wx.controller.modle.subChatItem.*;

import java.util.Arrays;

public class ChatModel extends BaseModel {

    private String corpId;
    private Long seq;

    private String msgtype;     // 文本消息为：text。String类型  图片消息为：image。String类型  撤回消息为：revoke。String类型
                                // 同意消息为：agree，不同意消息为：disagree。String类型  语音消息为：voice。String类型  视频消息为：video。String类型
                                // 名片消息为：card。String类型   位置消息为：location。String类型  表情消息为：emotion。String类型  file。String类型
                                // 链接消息为：link。String类型   消息为：weapp。String类型  消息为：chatrecord。String类型  _todo。String类型  vote。String                              // 类型  collect。String类型  redpacket。String类型  meeting。String类型  消息id，消息的唯一标识，企业可以使用此字段进行消息去                                // 重。  String类型   docmsg。String类型, 标识在线文档消息类型   markdown。String类型, 标识MarkDown消息类型  news String类                                //型, 标识 图文消息类型  calendar。String类型, 标识日程消息类型



    private String msgid;       //	消息id，消息的唯一标识，企业可以使用此字段进行消息去重。String类型
    private String action;	    // 消息动作，目前有send(发送消息)/recall(撤回消息)/switch(切换企业日志)三种类型。String类型
    private String from;   	    // 消息发送方id。同一企业内容为userid，非相同企业为external_userid。消息如果是机器人发出，也为external_userid。String类型
    private String[] tolist;      //	消息接收方列表，可能是多个，同一个企业内容为userid，非相同企业为external_userid。数组，内容为string类型
    private String roomid;      //	群聊消息的群id。如果是单聊则为空。String类型
    private Long msgtime;       //	消息发送时间戳，utc时间，ms单位。


    private Boolean mark = false;           //  是否已经同步了媒体数据

    private ChatModelText text;    //文本类型
    private ChatModelImage image;   //图片消息为
    private ChatModelMedia voice;
    private ChatModelMedia video;
    private ChatModelFile file;
    private ChatModelLocation location;
    private ChatModelEmotion emotion;
    private ChatModelCard card;
    private ChatModelAgree agree;
    private ChatModelLink link;
    private ChatModeRecord chatrecord;
    private ChatModeVote vote;
    private ChatModeCollect collect;
    private ChatModelRedPacket redpacket;
    private ChatModelRevoke revoke;
    private ChatModelMetting meeting;
    private ChatModelDocMsg docmsg;
    private ChatModelNews news;
    private ChatModelCalendar calendar;


    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
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

    public Boolean isMark() {
        return mark;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
    }


    public ChatModel(String corpId) {
        this.corpId = corpId;
    }


    public ChatModel(String corpId, Long seq) {
        this.corpId = corpId;
        this.seq = seq;
    }

    public ChatModel() {
    }


    public ChatModelText getText() {
        return text;
    }

    public void setText(ChatModelText text) {
        this.text = text;
    }

    public ChatModelImage getImage() {
        return image;
    }

    public void setImage(ChatModelImage image) {
        this.image = image;
    }

    public ChatModelMedia getVoice() {
        return voice;
    }

    public void setVoice(ChatModelMedia voice) {
        this.voice = voice;
    }

    public ChatModelMedia getVideo() {
        return video;
    }

    public void setVideo(ChatModelMedia video) {
        this.video = video;
    }

    public ChatModelLocation getLocation() {
        return location;
    }

    public void setLocation(ChatModelLocation location) {
        this.location = location;
    }

    public ChatModelEmotion getEmotion() {
        return emotion;
    }

    public void setEmotion(ChatModelEmotion emotion) {
        this.emotion = emotion;
    }

    public ChatModelCard getCard() {
        return card;
    }

    public void setCard(ChatModelCard card) {
        this.card = card;
    }

    public ChatModelAgree getAgree() {
        return agree;
    }

    public void setAgree(ChatModelAgree agree) {
        this.agree = agree;
    }

    public ChatModelLink getLink() {
        return link;
    }

    public void setLink(ChatModelLink link) {
        this.link = link;
    }

    public ChatModeRecord getChatrecord() {
        return chatrecord;
    }

    public void setChatrecord(ChatModeRecord chatrecord) {
        this.chatrecord = chatrecord;
    }

    public ChatModeVote getVote() {
        return vote;
    }

    public void setVote(ChatModeVote vote) {
        this.vote = vote;
    }

    public ChatModeCollect getCollect() {
        return collect;
    }

    public void setCollect(ChatModeCollect collect) {
        this.collect = collect;
    }

    public ChatModelRedPacket getRedpacket() {
        return redpacket;
    }

    public void setRedpacket(ChatModelRedPacket redpacket) {
        this.redpacket = redpacket;
    }

    public ChatModelRevoke getRevoke() {
        return revoke;
    }

    public void setRevoke(ChatModelRevoke revoke) {
        this.revoke = revoke;
    }

    public ChatModelMetting getMeeting() {
        return meeting;
    }

    public void setMeeting(ChatModelMetting meeting) {
        this.meeting = meeting;
    }

    public ChatModelDocMsg getDocmsg() {
        return docmsg;
    }

    public void setDocmsg(ChatModelDocMsg docmsg) {
        this.docmsg = docmsg;
    }

    public ChatModelNews getNews() {
        return news;
    }

    public void setNews(ChatModelNews news) {
        this.news = news;
    }

    public ChatModelCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(ChatModelCalendar calendar) {
        this.calendar = calendar;
    }

    public ChatModelFile getFile() {
        return file;
    }

    public void setFile(ChatModelFile file) {
        this.file = file;
    }


    @Override
    public String toString() {
        return "ChatModel{" +
                "corpId='" + corpId + '\'' +
                ", seq=" + seq +
                ", msgtype='" + msgtype + '\'' +
                ", msgid='" + msgid + '\'' +
                ", action='" + action + '\'' +
                ", from='" + from + '\'' +
                ", tolist=" + Arrays.toString(tolist) +
                ", roomid='" + roomid + '\'' +
                ", msgtime=" + msgtime +
                ", mark=" + mark +
                ", text=" + text +
                ", image=" + image +
                ", voice=" + voice +
                ", video=" + video +
                ", file=" + file +
                ", location=" + location +
                ", emotion=" + emotion +
                ", card=" + card +
                ", agree=" + agree +
                ", link=" + link +
                ", chatrecord=" + chatrecord +
                ", vote=" + vote +
                ", collect=" + collect +
                ", redpacket=" + redpacket +
                ", revoke=" + revoke +
                ", meeting=" + meeting +
                ", docmsg=" + docmsg +
                ", news=" + news +
                ", calendar=" + calendar +
                "} " + super.toString();
    }
}
