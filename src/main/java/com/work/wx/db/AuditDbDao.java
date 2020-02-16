/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.db;

import com.work.wx.controller.modle.AuditModel;
import com.work.wx.controller.modle.ChatModel;
import org.springframework.stereotype.Repository;


@Repository
public class AuditDbDao extends MongoDbDao {

    @Override
    protected Class<AuditModel> getEntityClass() {
        return AuditModel.class;
    }


}
