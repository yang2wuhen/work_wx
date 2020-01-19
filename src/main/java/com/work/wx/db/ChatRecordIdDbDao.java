/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.db;

import com.work.wx.controller.modle.ChatModel;
import com.work.wx.controller.modle.ChatRecordID;
import org.springframework.stereotype.Repository;


@Repository
public class ChatRecordIdDbDao extends MongoDbDao {

    @Override
    protected Class<ChatRecordID> getEntityClass() {
        return ChatRecordID.class;
    }






}
