/*
 * work_wx
 * wuhen 2020/2/4.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle.subChatItem;


public class ChatModeCollect {

    private String room_name;   //	填表消息所在的群名称。String类型
    private String creator;     //	创建者在群中的名字。String类型
    private String create_time; //	创建的时间。String类型
    private Long table_id;            //	表项id。Uint64类型
    private String  ques;       //	表项名称。String类型
    private String type;       //	表项类型，有Text(文本),Number(数字),Date(日期),Time(时间)。String类型

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

    public Long getTable_id() {
        return table_id;
    }

    public void setTable_id(Long table_id) {
        this.table_id = table_id;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
