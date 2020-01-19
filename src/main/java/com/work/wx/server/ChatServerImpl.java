/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;


import com.tencent.wework.ChatModel;
import com.work.wx.db.ChatDbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChatServerImpl implements ChatServer {

    private ChatDbDao chatDbDao;

    @Autowired
    public void setChatDbDao(ChatDbDao chatDbDao) {
        this.chatDbDao = chatDbDao;
    }


    @Override
    public void insert(ChatModel chatModel) {
         chatDbDao.insert(chatModel);
    }


    @Override
    public void updateChat(List<ChatModel> chatModels) {
        chatDbDao.insertAll(chatModels);
    }

}
