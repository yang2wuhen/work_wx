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

import java.io.InputStream;
import java.util.List;
import java.util.Set;

public interface AuditServer {

    public void insertAuditModel(AuditModel auditModel);

    public List<AuditModel> getAuditModels(AuditModel auditModel);

    public KeywordConfigModel getKeywordConfigModel(KeywordConfigModel keywordConfigModel);

    public void insertKeywordConfigModel(KeywordConfigModel keywordConfigModel);

    public List<String> getGroupAuditUsers(AuditModel auditModel, String groupField, String orderField);


}
