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
import com.work.wx.controller.modle.CorpModel;
import com.work.wx.controller.modle.KeywordConfigModel;
import com.work.wx.server.AuditServer;
import com.work.wx.server.ChatServer;
import com.work.wx.server.CorpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoBackUpAuditTask {
    private final static int LIMIT = 1000;
    private final static Logger logger = LoggerFactory.getLogger(AutoBackUpAuditTask.class);

    private ChatServer chatServer;
    private AuditServer auditServer;
    private CorpServer corpServer;

    @Autowired
    public void setCorpServer(CorpServer corpServer) {
        this.corpServer = corpServer;
    }

    @Autowired
    public void setChatRecordIdServer(ChatServer chatServer) {
        this.chatServer = chatServer;
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
        List<CorpModel> corpModels = corpServer.getCorpModels();
        if (null != corpModels && corpModels.size() > 0) {
            for (CorpModel corpModel : corpModels) {
                ChatModel queryChatModel = new ChatModel(corpModel.getCorp());
                queryChatModel.setMark(null);
                ChatModel chatModel = chatServer.getChat(queryChatModel);
                long seq = 0;
                if (null != chatModel && chatModel.getSeq() != null) {
                    seq = chatModel.getSeq();
                }
                logger.debug("start backup seq start with "+seq);
                boolean repeat = AuditBackUpUtil.insertChat(chatServer,auditServer,corpModel,seq,LIMIT);
                if (repeat) {
                    backupWeChat();
                }
            }
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
        List<CorpModel> corpModels = corpServer.getCorpModels();
        if (null != corpModels && corpModels.size() > 0) {
            for (CorpModel corpModel : corpModels) {
                MultiDataProcess multiDataProcess = new MultiDataProcess();
                multiDataProcess.FileTypeProcess(chatServer,corpModel);
            }
        }
    }






}
