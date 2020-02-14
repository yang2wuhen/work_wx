/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSUploadStream;
import com.work.wx.controller.modle.ChatDataModel;
import com.work.wx.controller.modle.ChatModel;
import com.work.wx.controller.modle.FileModel;
import org.bson.types.ObjectId;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

public interface ChatServer {

    public void insert(ChatModel chatModel);

    public void insertAll(List list);

    public void updateChat(List<ChatModel> chatModels);

    public void updateChat(ChatModel queryChatModel,ChatModel chatModel);

    public ChatModel getChat(ChatModel chatModel);

    public List<ChatModel> getChat(ChatModel chatModel, Set fields);

    public List<ChatModel> getChatList(ChatModel chatModel);

    public GridFSUploadStream insertChatData(FileModel fileModel);

    public InputStream getChatFile(String fileName) throws Exception;

    public List<ChatModel> getChatList(String corpId,String userId,String sendId);

    public List<String> getUserList(ChatModel chatModel,String groupField,String orderField);

    public List<String>  getRoomList(ChatModel chatModel,String groupField,String orderField,String notField);
}
