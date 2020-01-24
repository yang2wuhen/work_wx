/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;

import com.work.wx.controller.modle.FollowModel;
import com.work.wx.controller.modle.TokenModel;

import java.util.List;

public interface FollowServer {

    public long updateFollowConfig(FollowModel queryModel, FollowModel followModel);

    public FollowModel getFollowConfig(FollowModel followModel);

    public List<FollowModel> getAllFollowConfig(FollowModel followModel);

}
