/*
 * work_wx
 * wuhen 2020/2/5.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.task;

import com.work.wx.config.CustomConfig;
import com.work.wx.controller.modle.ChatModel;
import com.work.wx.server.ChatServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

public class MultiDataProcess {
    private final static Logger logger = LoggerFactory.getLogger(MultiDataProcess.class);

    public void FileTypeProcess(ChatServer chatServer, CustomConfig customConfig) {
        String mediaType[] = new String[] {"image","voice","video","file","emotion"};
        for (String type : mediaType) {
            ChatModel chatModel = new ChatModel(customConfig.getCorp());
            chatModel.setMark(false);
            chatModel.setMsgtype(type);
            List list = chatServer.getChatList(chatModel);
            if (null != list && list.size() > 0) {
                logger.debug("back up media " + type +" size is "+list.size());
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    ChatModel model = (ChatModel) iterator.next();
                    String sdkFileid = "";
                    switch (type) {
                        case "image":
                            sdkFileid = model.getImage().getSdkfileid();
                            break;
                        case "voice":
                            sdkFileid = model.getVoice().getSdkfileid();
                            break;
                        case "video":
                            sdkFileid = model.getVideo().getSdkfileid();
                            break;
                        case "file":
                            sdkFileid = model.getFile().getSdkfileid();
                            break;
                        case "emotion":
                            sdkFileid = model.getEmotion().getSdkfileid();
                            break;
                    }
                    boolean flag = BackUp.getMediaData(chatServer,customConfig,"", sdkFileid);
                    if (flag) {
                        model.setMark(true);
                        ChatModel queryModel = new ChatModel(customConfig.getCorp());
                        queryModel.setMsgid(model.getMsgid());
                        chatServer.updateChat(queryModel, model);
                    }
                }
            }
        }
    }


}
