/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.db;

import com.work.wx.controller.modle.ChatModel;
import com.work.wx.controller.modle.ContactModel;
import org.springframework.stereotype.Repository;


@Repository
public class ContactDbDao extends MongoDbDao {

    @Override
    protected Class<ContactModel> getEntityClass() {
        return ContactModel.class;
    }






}
