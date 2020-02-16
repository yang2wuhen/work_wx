/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.task;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.work.wx.config.CustomConfig;
import com.work.wx.controller.modle.AuditModel;
import com.work.wx.controller.modle.ChatModel;
import com.work.wx.controller.modle.KeywordConfigModel;
import com.work.wx.server.AuditServer;
import com.work.wx.server.ChatServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutoBackUpAuditTask {
    private final static int LIMIT = 1000;
    private final static Logger logger = LoggerFactory.getLogger(AutoBackUpAuditTask.class);

    private CustomConfig customConfig;
    private ChatServer chatServer;
    private AuditServer auditServer;

    @Autowired
    public void setChatRecordIdServer(ChatServer chatServer) {
        this.chatServer = chatServer;
    }

    @Autowired
    public void setCustomConfig(CustomConfig customConfig) {
        this.customConfig = customConfig;
    }

    @Autowired
    public void setAuditServer(AuditServer auditServer) {
        this.auditServer = auditServer;
    }

    /**
     * @todo 自动备份消息存档
     * @author wuhen
     * @returns void
     * @throws
     * @date 2020/1/27 13:14
     */
    @Async
    @Scheduled(fixedRate = 1000*60*5, initialDelay = 1000*10)
    public void backupWeChat() {
        ChatModel queryChatModel = new ChatModel(customConfig.getCorp());
        queryChatModel.setMark(null);
        ChatModel chatModel = chatServer.getChat(queryChatModel);
        long seq = 0;
        if (null != chatModel && chatModel.getSeq() != null) {
            seq = chatModel.getSeq();
        }
        logger.debug("start backup seq start with "+seq);
        boolean repeat = AuditBackUpUtil.insertChat(chatServer,auditServer,customConfig,seq,LIMIT);
        if (repeat) {
            backupWeChat();
        }
    }



    /**
     * @todo 自动备份消息存档
     * @author wuhen
     * @returns void
     * @throws
     * @date 2020/1/27 13:14
     */
    @Async
    @Scheduled(fixedRate = 1000*60*6, initialDelay = 1000*20)
    public void backupWeChatData() {
        MultiDataProcess multiDataProcess = new MultiDataProcess();
        multiDataProcess.FileTypeProcess(chatServer,customConfig);
    }






}
