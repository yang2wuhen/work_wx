/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.task;

import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager;
import com.work.wx.controller.api.token.ExternalContactAccessToken;
import com.work.wx.controller.modle.ChatRecordID;
import com.work.wx.server.ChatRecordIdServer;
import com.work.wx.server.ChatServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutoBackUpTask {
    private final static int LIMIT = 1000;
    private final static Logger logger = LoggerFactory.getLogger(AutoBackUpTask.class);

    private ChatRecordIdServer chatRecordIdServer;

    @Autowired
    public void setChatRecordIdServer(ChatRecordIdServer chatRecordIdServer) {
        this.chatRecordIdServer = chatRecordIdServer;
    }


    @Scheduled(fixedRate = 1000*60*5, initialDelay = 1000*10)
    public void backupWeChat() {
        ChatRecordID chatRecordID = chatRecordIdServer.getChatRecordID(new ChatRecordID(ExternalContactAccessToken.CORP_ID));
        long seq = 0;
        if (null != chatRecordID && chatRecordID.getSeq() != null) {
            seq = chatRecordID.getSeq();
        }
        boolean repeat = BackUp.insertChatData(seq,LIMIT);
        if (repeat) {
            backupWeChat();
        }
    }







}
