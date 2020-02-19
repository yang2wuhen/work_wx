/*
 * work_wx
 * wuhen 2020/2/10.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle;

import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

public class GroupModel {
    private String corp;

    private String group_chat;      //	客户群详情
    private String chat_id;         //	客户群ID
    private String name;            //	群名
    private String owner;           //	群主ID
    private Long create_time;       //	群的创建时间
    private String notice;          //	群公告
    private Integer status;
    private List<GroupMember> member_list;


    class GroupMember {
        private String userid;          //	群成员id
        private Integer type;           //	成员类型。  1 - 企业成员  2 - 外部联系人
        private Long join_time;         //	入群时间
        private Integer join_scene;     //	入群方式。 1 - 由成员邀请入群（直接邀请入群） 2 - 由成员邀请入群（通过邀请链接入群）3 - 通过扫描群二维码入群

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Long getJoin_time() {
            return join_time;
        }

        public void setJoin_time(Long join_time) {
            this.join_time = join_time;
        }

        public Integer getJoin_scene() {
            return join_scene;
        }

        public void setJoin_scene(Integer join_scene) {
            this.join_scene = join_scene;
        }
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getGroup_chat() {
        return group_chat;
    }

    public void setGroup_chat(String group_chat) {
        this.group_chat = group_chat;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public List<GroupMember> getMember_list() {
        return member_list;
    }

    public void setMember_list(List<GroupMember> member_list) {
        this.member_list = member_list;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public GroupModel() {
    }

    public GroupModel(String corp, String chat_id) {
        this.corp = corp;
        this.chat_id = chat_id;
    }


}
