/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;


import com.mongodb.client.gridfs.GridFSUploadStream;
import com.work.wx.controller.modle.ChatModel;
import com.work.wx.controller.modle.FileModel;
import com.work.wx.db.ChatDbDao;
import com.work.wx.db.MongoGridFSDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ChatServerImpl implements ChatServer {

    private ChatDbDao chatDbDao;
    private MongoGridFSDao mongoGridFSDao;

    @Autowired
    public void setChatDbDao(ChatDbDao chatDbDao) {
        this.chatDbDao = chatDbDao;
    }


    @Autowired
    public void setMongoGridFSDao(MongoGridFSDao mongoGridFSDao) {
        this.mongoGridFSDao = mongoGridFSDao;
    }

    @Override
    public void insert(ChatModel chatModel) {
         chatDbDao.insert(chatModel);
    }


    @Override
    public void updateChat(List<ChatModel> chatModels) {
        chatDbDao.insertAll(chatModels);
    }



    public ChatModel getChat(ChatModel chatModel) {
        return (ChatModel)chatDbDao.queryOneDesc(chatModel, "seq");
    }


    @Override
    public List<ChatModel> getChat(ChatModel chatModel, Set fields) {
        return chatDbDao.queryList(chatModel,fields);
    }


    @Override
    public void updateChat(ChatModel queryChatModel,ChatModel chatModel) {
        chatDbDao.updateFirst(queryChatModel,chatModel);
    }


    @Override
    public void insertAll(List list) {
        chatDbDao.insertAll(list);
    }

    @Override
    public List<ChatModel> getChatList(ChatModel chatModel) {
        return chatDbDao.queryList(chatModel);
    }


    @Override
    public GridFSUploadStream insertChatData(FileModel fileModel) {
        return mongoGridFSDao.save(fileModel);
    }


    @Override
    public InputStream getChatFile(String fileName) throws Exception {
        return mongoGridFSDao.getFile(fileName);
    }

    @Override
    public List<ChatModel> getChatList(ChatModel chatModel, String groupByField, String orderField) {
        return chatDbDao.queryListGroupBy(chatModel, groupByField, orderField);
    }

    @Override
    public List<ChatModel> queryChatList(String corpId, String userId, String sendId) {
        return chatDbDao.queryChatList(corpId, userId, sendId);
    }
}
