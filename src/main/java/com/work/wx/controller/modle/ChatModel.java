/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle;


public class ChatModel extends BaseModel{

    private String corpId;
    private Long seq;

    private String msgtype;     // 文本消息为：text。String类型  图片消息为：image。String类型  撤回消息为：revoke。String类型
                                // 同意消息为：agree，不同意消息为：disagree。String类型  语音消息为：voice。String类型  视频消息为：video。String类型
                                // 名片消息为：card。String类型   位置消息为：location。String类型  表情消息为：emotion。String类型  file。String类型
                                // 链接消息为：link。String类型   消息为：weapp。String类型  消息为：chatrecord。String类型  _todo。String类型  vote。String                              // 类型  collect。String类型  redpacket。String类型  meeting。String类型  消息id，消息的唯一标识，企业可以使用此字段进行消息去                                // 重。  String类型   docmsg。String类型, 标识在线文档消息类型   markdown。String类型, 标识MarkDown消息类型  news String类                                //型, 标识 图文消息类型  calendar。String类型, 标识日程消息类型


    private String pre_msgid;   //	标识撤回的原消息的msgid。String类型

    private String msgid;       //	消息id，消息的唯一标识，企业可以使用此字段进行消息去重。String类型
    private String action;	    // 消息动作，目前有send(发送消息)/recall(撤回消息)/switch(切换企业日志)三种类型。String类型
    private String from;   	    // 消息发送方id。同一企业内容为userid，非相同企业为external_userid。消息如果是机器人发出，也为external_userid。String类型
    private String tolist;      //	消息接收方列表，可能是多个，同一个企业内容为userid，非相同企业为external_userid。数组，内容为string类型
    private String roomid;      //	群聊消息的群id。如果是单聊则为空。String类型
    private Long msgtime;       //	消息发送时间戳，utc时间，ms单位。

    private String content;     //	消息内容。String类型

    private String sdkfileid;   // 媒体资源的id信息。String类型
    private String md5sum;      // 图片资源的md5值，供进行校验。String类型
    private Integer filesize;      // 图片资源的文件大小。Uint32类型

    private String userid;      //	同意/不同意协议者的userid，外部企业默认为external_userid。String类型
    private Long agree_time;    //	同意/不同意协议的时间，utc时间，ms单位。

    private Integer voice_size;   	//  语音消息大小。Uint32类型
    private Integer play_length;   //	播放长度。Uint32类型

    private String corpname;    //	名片所有者所在的公司名称。String类型

    private Double longitude;   //	经度，单位double
    private Double latitude;    //	纬度，单位double
    private String address;     //	地址信息。String类型
    private String title;       //	位置信息的title。String类型
    private Integer zoom;          //	缩放比例。Uint32类型

    private Long type;          //	表情类型，png或者gif.1表示gif 2表示png。Uint32类型
    private Integer width;         //  表情图片宽度。Uint32类型
    private Integer height;        //	表情图片高度。Uint32类型
    private Long imagesize;     //	资源的文件大小。Uint32类型

    private String filename;    //	文件名称。String类型
    private String fileext;     //	文件类型后缀。String类型

    private String description; //	消息描述。String类型
    private String link_url;	//  链接url地址。String类型
    private String image_url;	//  链接图片url。String类型

    private String username;	//  用户名称。String类型
    private String displayname; //	小程序名称。String类型

    private ChatItem chatItem;  //	消息记录内的消息内容，批量数据

    private String votetitle;   //	投票主题。String类型
    private String voteitem;    //	投票选项，可能多个内容。String类型
    private Long   votetype;    //	投票类型.101发起投票、102参与投票。Uint32类型
    private String voteid;      //	投票id，方便将参与投票消息与发起投票消息进行前后对照。String类型

    private String room_name;   //	填表消息所在的群名称。String类型
    private String creator;     //	创建者在群中的名字。String类型
    private String create_time; //	创建的时间。String类型
    private Long id;            //	表项id。Uint64类型
    private String  ques;       //	表项名称。String类型
//    type	表项类型，有Text(文本),Number(数字),Date(日期),Time(时间)。String类型

    private String wish;	        //  红包祝福语。String类型
    private Integer totalcnt;       //	红包总个数。Uint32类型
    private Integer totalamount;    //	红包总金额。Uint32类型，单位为分。

    private String topic;           //	会议主题。String类型
    private String  starttime;      //	会议开始时间。Utc时间
    private String  endtime;        //	会议结束时间。Utc时间
    private String remarks;         //	会议备注。String类型
    private Integer meetingtype;    //	会议消息类型。101发起会议邀请消息、102处理会议邀请消息。Uint32类型
    private  Long meetingid;        //	会议id。方便将发起、处理消息进行对照。uint64类型
    private Integer status;         //	会议邀请处理状态。1 参加会议、2 拒绝会议、3 待定。Uint32类型

    private String doc_creator;     //	在线文档创建者。本企业成员创建为userid；外部企业成员创建为external_userid

    private String url;             //	图文消息点击跳转地址。String类型
    private String picurl;          //	图文消息配图的url。String类型

    private String creatorname;     //	日程组织者
    private String attendeename;    //	日程参与人
    private String place;           //	日程地点

    private boolean mark;           //  是否已经同步了媒体数据

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getPre_msgid() {
        return pre_msgid;
    }

    public void setPre_msgid(String pre_msgid) {
        this.pre_msgid = pre_msgid;
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

    public String getTolist() {
        return tolist;
    }

    public void setTolist(String tolist) {
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

    public String getSdkfileid() {
        return sdkfileid;
    }

    public void setSdkfileid(String sdkfileid) {
        this.sdkfileid = sdkfileid;
    }

    public String getMd5sum() {
        return md5sum;
    }

    public void setMd5sum(String md5sum) {
        this.md5sum = md5sum;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

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

    public Integer getVoice_size() {
        return voice_size;
    }

    public void setVoice_size(Integer voice_size) {
        this.voice_size = voice_size;
    }

    public Integer getPlay_length() {
        return play_length;
    }

    public void setPlay_length(Integer play_length) {
        this.play_length = play_length;
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Long getImagesize() {
        return imagesize;
    }

    public void setImagesize(Long imagesize) {
        this.imagesize = imagesize;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileext() {
        return fileext;
    }

    public void setFileext(String fileext) {
        this.fileext = fileext;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public ChatItem getChatItem() {
        return chatItem;
    }

    public void setChatItem(ChatItem chatItem) {
        this.chatItem = chatItem;
    }

    public String getVotetitle() {
        return votetitle;
    }

    public void setVotetitle(String votetitle) {
        this.votetitle = votetitle;
    }

    public String getVoteitem() {
        return voteitem;
    }

    public void setVoteitem(String voteitem) {
        this.voteitem = voteitem;
    }

    public Long getVotetype() {
        return votetype;
    }

    public void setVotetype(Long votetype) {
        this.votetype = votetype;
    }

    public String getVoteid() {
        return voteid;
    }

    public void setVoteid(String voteid) {
        this.voteid = voteid;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

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

    public String getDoc_creator() {
        return doc_creator;
    }

    public void setDoc_creator(String doc_creator) {
        this.doc_creator = doc_creator;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

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

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "ChatModel{" +
                "corpId='" + corpId + '\'' +
                ", seq=" + seq +
                ", msgtype='" + msgtype + '\'' +
                ", pre_msgid='" + pre_msgid + '\'' +
                ", msgid='" + msgid + '\'' +
                ", action='" + action + '\'' +
                ", from='" + from + '\'' +
                ", tolist='" + tolist + '\'' +
                ", roomid='" + roomid + '\'' +
                ", msgtime=" + msgtime +
                ", content='" + content + '\'' +
                ", sdkfileid='" + sdkfileid + '\'' +
                ", md5sum='" + md5sum + '\'' +
                ", filesize=" + filesize +
                ", userid='" + userid + '\'' +
                ", agree_time=" + agree_time +
                ", voice_size=" + voice_size +
                ", play_length=" + play_length +
                ", corpname='" + corpname + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", address='" + address + '\'' +
                ", title='" + title + '\'' +
                ", zoom=" + zoom +
                ", type=" + type +
                ", width=" + width +
                ", height=" + height +
                ", imagesize=" + imagesize +
                ", filename='" + filename + '\'' +
                ", fileext='" + fileext + '\'' +
                ", description='" + description + '\'' +
                ", link_url='" + link_url + '\'' +
                ", image_url='" + image_url + '\'' +
                ", username='" + username + '\'' +
                ", displayname='" + displayname + '\'' +
                ", chatItem=" + chatItem +
                ", votetitle='" + votetitle + '\'' +
                ", voteitem='" + voteitem + '\'' +
                ", votetype=" + votetype +
                ", voteid='" + voteid + '\'' +
                ", room_name='" + room_name + '\'' +
                ", creator='" + creator + '\'' +
                ", create_time='" + create_time + '\'' +
                ", id=" + id +
                ", ques='" + ques + '\'' +
                ", wish='" + wish + '\'' +
                ", totalcnt=" + totalcnt +
                ", totalamount=" + totalamount +
                ", topic='" + topic + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", remarks='" + remarks + '\'' +
                ", meetingtype=" + meetingtype +
                ", meetingid=" + meetingid +
                ", status=" + status +
                ", doc_creator='" + doc_creator + '\'' +
                ", url='" + url + '\'' +
                ", picurl='" + picurl + '\'' +
                ", creatorname='" + creatorname + '\'' +
                ", attendeename='" + attendeename + '\'' +
                ", place='" + place + '\'' +
                ", mark=" + mark +
                ", _id='" + _id + '\'' +
                "} " + super.toString();
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




}
