/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.db;

import com.work.wx.controller.modle.AuditModel;
import com.work.wx.controller.modle.GroupModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class GroupDbDao extends MongoDbDao {

    @Override
    protected Class<GroupModel> getEntityClass() {
        return GroupModel.class;
    }

}