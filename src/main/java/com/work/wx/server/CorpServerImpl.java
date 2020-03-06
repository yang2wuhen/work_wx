/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;


import com.work.wx.controller.modle.CorpModel;
import com.work.wx.db.CorpDbDao;
import com.work.wx.db.ExtendContactDbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CorpServerImpl implements CorpServer {

    private CorpDbDao corpDbDao;

    @Autowired
    public void setCorpDbDao(CorpDbDao corpDbDao) {
        this.corpDbDao = corpDbDao;
    }


    @Override
    public CorpModel getCorpModel(CorpModel corpModel) {
        return (CorpModel) corpDbDao.queryOne(corpModel);
    }


    @Override
    public void insertCorpModel(CorpModel corpModel) {
        corpDbDao.updateInsert(new CorpModel(corpModel.getCorp()), corpModel);
    }

    @Override
    public CorpModel getCorpModel(String corpId) {
        return getCorpModel(new CorpModel(corpId));
    }

    @Override
    public List<CorpModel> getCorpModels() {
        return corpDbDao.queryList();
    }

    @Override
    public void addCorpModel(CorpModel corpModel) {
        corpDbDao.insert(corpModel);
    }
}
