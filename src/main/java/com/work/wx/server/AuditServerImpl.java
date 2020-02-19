/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;


import com.mongodb.client.gridfs.GridFSUploadStream;
import com.work.wx.controller.modle.AuditModel;
import com.work.wx.controller.modle.ChatModel;
import com.work.wx.controller.modle.FileModel;
import com.work.wx.controller.modle.KeywordConfigModel;
import com.work.wx.db.AuditDbDao;
import com.work.wx.db.ChatDbDao;
import com.work.wx.db.KeywordConfigDbDao;
import com.work.wx.db.MongoGridFSDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AuditServerImpl implements AuditServer {

    private AuditDbDao auditDbDao;
    private KeywordConfigDbDao keywordConfigDbDao;

    @Autowired
    public void setKeywordConfigDbDao(KeywordConfigDbDao keywordConfigDbDao) {
        this.keywordConfigDbDao = keywordConfigDbDao;
    }

    @Autowired
    public void setAuditDbDao(AuditDbDao auditDbDao) {
        this.auditDbDao = auditDbDao;
    }

    @Override
    public void insertAuditModel(AuditModel auditModel) {
        auditDbDao.insert(auditModel);
    }

    @Override
    public List<AuditModel> getAuditModels(AuditModel auditModel) {
        return auditDbDao.queryList(auditModel);
    }


    @Override
    public KeywordConfigModel getKeywordConfigModel(KeywordConfigModel keywordConfigModel) {
        return (KeywordConfigModel)keywordConfigDbDao.queryOne(keywordConfigModel);
    }


    @Override
    public void insertKeywordConfigModel(KeywordConfigModel keywordConfigModel) {
        keywordConfigDbDao.updateInsert(keywordConfigModel,keywordConfigModel);
    }

    @Override
    public List<String> getGroupAuditUsers(AuditModel auditModel, String groupField, String orderField) {
        return auditDbDao.queryGroupBy(auditModel,groupField, orderField);
    }

    @Override
    public void insertUpdateAuditModel(AuditModel queryAuditModel, AuditModel auditModel) {
        auditDbDao.updateInsert(queryAuditModel, auditModel);
    }
}
