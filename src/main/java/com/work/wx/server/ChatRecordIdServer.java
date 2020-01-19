/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;

import com.work.wx.controller.modle.ChatRecordID;
import com.work.wx.controller.modle.TokenModel;

public interface ChatRecordIdServer {

    public long updateInsertToken(ChatRecordID queryChatRecordID, ChatRecordID chatRecordID);

    public ChatRecordID getChatRecordID(ChatRecordID queryChatRecordID);

}
