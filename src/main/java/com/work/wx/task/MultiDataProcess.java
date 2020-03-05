/*
 * work_wx
 * wuhen 2020/2/5.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.task;

import com.mongodb.client.gridfs.GridFSUploadStream;
import com.work.wx.config.CustomConfig;
import com.work.wx.controller.modle.ChatModel;
import com.work.wx.controller.modle.CorpModel;
import com.work.wx.controller.modle.FileModel;
import com.work.wx.server.ChatServer;
import org.hibernate.validator.cfg.context.CrossParameterConstraintMappingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

public class MultiDataProcess {
    private final static Logger logger = LoggerFactory.getLogger(MultiDataProcess.class);

    public void FileTypeProcess(ChatServer chatServer, CorpModel corpModel) {
        String mediaType[] = new String[] {"image","voice","video","file","emotion"};
        for (String type : mediaType) {
            ChatModel chatModel = new ChatModel(corpModel.getCorp());
            chatModel.setMark(false);
            chatModel.setMsgtype(type);
            List list = chatServer.getChatList(chatModel);
            if (null != list && list.size() > 0) {
                logger.debug("back up media " + type +" size is "+list.size());
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    ChatModel model = (ChatModel) iterator.next();
                    FileModel fileModel = getFileModel(model);
                    GridFSUploadStream uploadStream = chatServer.insertChatData(fileModel);
                    boolean flag = AuditBackUpUtil.getMediaData(corpModel,uploadStream,"", fileModel.getFileSDKField());
                    if (flag) {
                        logger.debug("update chat model msgId "+model.getMsgid());
                        model.setMark(true);
                        ChatModel queryModel = new ChatModel(corpModel.getCorp());
                        queryModel.setMsgid(model.getMsgid());
                        chatServer.updateChat(queryModel, model);
                    }
                }
                logger.debug("back up media finish");
            }
        }
    }



    private FileModel getFileModel(ChatModel model) {
        FileModel fileModel = new FileModel();
        fileModel.setFileName(model.getMsgid());
        fileModel.setFileType(model.getMsgtype());
        switch (model.getMsgtype()) {
            case "image":
                fileModel.setLength((Long.valueOf(model.getImage().getFilesize())));
                fileModel.setMD5(model.getImage().getMd5sum());
                fileModel.setFileSDKField(model.getImage().getSdkfileid());
                break;
            case "voice":
                fileModel.setLength(Long.valueOf(model.getVoice().getFilesize()));
                fileModel.setMD5(model.getVoice().getMd5sum());
                fileModel.setFileSDKField(model.getVoice().getSdkfileid());
                break;
            case "video":
                fileModel.setLength(Long.valueOf(model.getVideo().getFilesize()));
                fileModel.setMD5(model.getVideo().getMd5sum());
                fileModel.setFileSDKField(model.getVideo().getSdkfileid());
                break;
            case "file":
                fileModel.setLength(Long.valueOf(model.getFile().getFilesize()));
                fileModel.setMD5(model.getFile().getMd5sum());
                fileModel.setFileSDKField(model.getFile().getSdkfileid());
                break;
            case "emotion":
                fileModel.setLength(model.getEmotion().getImagesize());
                fileModel.setMD5(model.getEmotion().getMd5sum());
                fileModel.setFileSDKField(model.getEmotion().getSdkfileid());
                break;
        }
        return fileModel;
    }



}
