/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;

import com.work.wx.controller.modle.ChatDataModel;
import com.work.wx.controller.modle.ChatModel;

import java.util.List;
import java.util.Set;

public interface ChatServer {

    public void insert(ChatModel chatModel);

    public void updateChat(List<ChatModel> chatModels);

    public void updateChat(ChatModel queryChatModel,ChatModel chatModel);

    public ChatDataModel getChatData(ChatDataModel chatDataModel);

    public ChatModel getChat(ChatModel chatModel);

    public void updateInsertChatData(ChatDataModel chatDataModel,ChatDataModel updateChatDataModel);

    public void insertChatData(ChatDataModel chatDataModel);

    public List<ChatModel> getChat(ChatModel chatModel, Set fields);

}
