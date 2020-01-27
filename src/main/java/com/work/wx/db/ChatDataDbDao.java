/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.db;

import com.work.wx.controller.modle.ChatDataModel;
import com.work.wx.controller.modle.ChatModel;
import org.springframework.stereotype.Repository;


@Repository
public class ChatDataDbDao extends MongoDbDao {

    @Override
    protected Class<ChatDataModel> getEntityClass() {
        return ChatDataModel.class;
    }






}
