/*
 * work_wx
 * wuhen 2020/2/4.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.controller.modle.subChatItem;

import com.work.wx.controller.modle.BaseModel;

public class ChatModelRevoke extends BaseModel {

    private String pre_msgid;   //	标识撤回的原消息的msgid。String类型


    public String getPre_msgid() {
        return pre_msgid;
    }

    public void setPre_msgid(String pre_msgid) {
        this.pre_msgid = pre_msgid;
    }
}
