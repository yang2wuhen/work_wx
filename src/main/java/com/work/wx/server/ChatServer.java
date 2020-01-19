/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;

import com.tencent.wework.ChatModel;

import java.util.List;

public interface ChatServer {

    public void insert(ChatModel chatModel);

    public void updateChat(List<ChatModel> chatModels);


}
