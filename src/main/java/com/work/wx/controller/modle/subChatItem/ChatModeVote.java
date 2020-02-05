/*
 * work_wx
 * wuhen 2020/2/4.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle.subChatItem;


public class ChatModeVote {

    private String votetitle;   //	投票主题。String类型
    private String voteitem;    //	投票选项，可能多个内容。String类型
    private Long   votetype;    //	投票类型.101发起投票、102参与投票。Uint32类型
    private String voteid;      //	投票id，方便将参与投票消息与发起投票消息进行前后对照。String类型


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
}
