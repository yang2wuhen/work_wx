/*
 * work_wx
 * wuhen 2020/1/15.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;

import com.work.wx.controller.modle.FollowModel;
import com.work.wx.db.FollowDbDao;
import com.work.wx.db.TokenDbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FollowServerImpl implements FollowServer {

    private FollowDbDao followDbDao;

    @Autowired
    public void setFollowDbDao(FollowDbDao followDbDao) {
        this.followDbDao = followDbDao;
    }

    @Override
    public long updateFollowConfig(FollowModel queryModel, FollowModel followModel) {
        return followDbDao.updateInsert(queryModel,followModel);
    }

    @Override
    public FollowModel getFollowConfig(FollowModel followModel) {
        return (FollowModel) followDbDao.queryOne(followModel);
    }


    @Override
    public List<FollowModel> getAllFollowConfig(FollowModel followModel) {
        return  followDbDao.queryList(followModel);
    }

}
