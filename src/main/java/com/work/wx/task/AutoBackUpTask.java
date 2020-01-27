/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.task;

import com.work.wx.controller.api.token.ExternalContactAccessToken;
import com.work.wx.controller.modle.ChatModel;
import com.work.wx.server.ChatServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.management.modelmbean.ModelMBean;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Component
public class AutoBackUpTask {
    private final static int LIMIT = 1000;
    private final static Logger logger = LoggerFactory.getLogger(AutoBackUpTask.class);

    private ChatServer chatServer;

    @Autowired
    public void setChatRecordIdServer(ChatServer chatServer) {
        this.chatServer = chatServer;
    }


    /**
     * @todo 自动备份消息存档
     * @author wuhen
     * @returns void
     * @throws
     * @date 2020/1/27 13:14
     */
    @Scheduled(fixedRate = 1000*60*5, initialDelay = 1000*10)
    public void backupWeChat() {
        ChatModel chatModel = chatServer.getChat(new ChatModel(ExternalContactAccessToken.CORP_ID));
        long seq = 0;
        if (null != chatModel && chatModel.getSeq() != null) {
            seq = chatModel.getSeq();
        }
        boolean repeat = BackUp.insertChat(seq,LIMIT);
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
    @Scheduled(fixedRate = 1000*60*6, initialDelay = 1000*20)
    public void backupWeChatData() {
        ChatModel chatModel = new ChatModel(ExternalContactAccessToken.CORP_ID);
        chatModel.setMsgtype("text");
        chatModel.setMark(false);
        Set set = new HashSet();
        set.add("msgtype");
        List list = chatServer.getChat(chatModel,set);
        if (null != list && list.size() > 0) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                ChatModel model = (ChatModel) iterator.next();
                boolean flag = BackUp.getMediaData("0", model.getSdkfileid());
                if (flag) {
                    model.setMark(true);
                    ChatModel queryModel = new ChatModel();
                    queryModel.set_id(model.get_id());
                    chatServer.updateChat(queryModel, model);
                }
            }
        }
    }







}
