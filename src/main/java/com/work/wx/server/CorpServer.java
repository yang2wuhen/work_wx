/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;

import com.work.wx.controller.modle.AuditModel;
import com.work.wx.controller.modle.CorpModel;
import com.work.wx.controller.modle.ExtendContactModel;
import com.work.wx.controller.modle.KeywordConfigModel;

import java.util.List;

public interface CorpServer {

    public CorpModel getCorpModel(CorpModel corpModel);

    public void insertCorpModel(CorpModel corpModel);

    public CorpModel getCorpModel(String corpId);

    public List<CorpModel> getCorpModels();

    public void addCorpModel(CorpModel corpModel);


}
