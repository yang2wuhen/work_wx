/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;


import com.work.wx.controller.modle.ChatDataModel;
import com.work.wx.controller.modle.ChatModel;
import com.work.wx.db.ChatDataDbDao;
import com.work.wx.db.ChatDbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ChatServerImpl implements ChatServer {

    private ChatDbDao chatDbDao;
    private ChatDataDbDao chatDataDbDao;

    @Autowired
    public void setChatDbDao(ChatDbDao chatDbDao) {
        this.chatDbDao = chatDbDao;
    }

    public void setChatDataDbDao(ChatDataDbDao chatDataDbDao) {
        this.chatDataDbDao = chatDataDbDao;
    }

    @Override
    public void insert(ChatModel chatModel) {
         chatDbDao.insert(chatModel);
    }


    @Override
    public void updateChat(List<ChatModel> chatModels) {
        chatDbDao.insertAll(chatModels);
    }



    @Override
    public void updateInsertChatData(ChatDataModel chatDataModel, ChatDataModel updateChatDataModel) {
        chatDataDbDao.updateInsert(chatDataModel,updateChatDataModel);
    }


    public ChatDataModel getChatData(ChatDataModel chatDataModel) {
        return (ChatDataModel) chatDataDbDao.queryOne(chatDataModel);
    }


    public ChatModel getChat(ChatModel chatModel) {
        return (ChatModel)chatDbDao.queryOneDesc(chatModel, "seq");
    }


    @Override
    public void insertChatData(ChatDataModel chatDataModel) {
        chatDataDbDao.insert(chatDataModel);
    }

    @Override
    public List<ChatModel> getChat(ChatModel chatModel, Set fields) {
        return chatDbDao.queryList(chatModel,fields);
    }


    @Override
    public void updateChat(ChatModel queryChatModel,ChatModel chatModel) {
        chatDbDao.updateInsert(queryChatModel,chatModel);
    }




}
