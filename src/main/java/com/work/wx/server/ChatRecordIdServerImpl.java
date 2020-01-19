/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.work.wx.controller.modle.ChatRecordID;
import com.work.wx.db.ChatRecordIdDbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChatRecordIdServerImpl implements ChatRecordIdServer {

    private ChatRecordIdDbDao chatRecordIdDbDao;

    @Autowired
    public void setChatRecordIdDbDao(ChatRecordIdDbDao chatRecordIdDbDao) {
        this.chatRecordIdDbDao = chatRecordIdDbDao;
    }

    @Override
    public long updateInsertToken(ChatRecordID queryChatRecordID, ChatRecordID chatRecordID) {
        return chatRecordIdDbDao.updateInsert(queryChatRecordID, chatRecordID);
    }


    @Override
    public ChatRecordID getChatRecordID(ChatRecordID queryChatRecordID) {
        return (ChatRecordID) chatRecordIdDbDao.queryOne(queryChatRecordID);
    }



}
