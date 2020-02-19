/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;

import com.work.wx.controller.modle.FollowModel;
import com.work.wx.controller.modle.GroupModel;
import com.work.wx.db.FollowDbDao;
import com.work.wx.db.GroupDbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupServerImpl implements GroupServer {

    private GroupDbDao groupDbDao;

    @Autowired
    public void setGroupDbDao(GroupDbDao groupDbDao) {
        this.groupDbDao = groupDbDao;
    }

    @Override
    public void insertGroupModel(GroupModel groupModel) {
        groupDbDao.insert(groupModel);
    }

    @Override
    public void insertUpdateGroupModel(GroupModel queryGroupModel, GroupModel groupModel) {
        groupDbDao.updateInsert(queryGroupModel, groupModel);
    }


}
