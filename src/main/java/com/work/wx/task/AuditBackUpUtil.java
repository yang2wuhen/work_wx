/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.task;

import com.google.gson.*;
import com.mongodb.client.gridfs.GridFSUploadStream;
import com.tencent.wework.Finance;
import com.work.wx.config.CustomConfig;
import com.work.wx.controller.modle.ChatDataModel;
import com.work.wx.controller.modle.ChatModel;
import com.work.wx.server.ChatServer;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class AuditBackUpUtil {
    private final static Logger logger = LoggerFactory.getLogger(AuditBackUpUtil.class);
    private static long sdk = 0;
    private static long timeout = 10 * 60 * 1000;

    private static void initSDK (CustomConfig customConfig) {
        if (sdk == 0) {
            sdk = Finance.NewSdk();
            Finance.Init(sdk,customConfig.getCorp(),customConfig.getAuditSecret());
        }
    }


    /**
     * @todo
     * @author wuhen
     * @param seq :
     * @param limit :
     * @returns boolean
     * @throws
     * @date 2020/1/24 18:25
     */
    public static boolean insertChat(ChatServer chatServer,CustomConfig customConfig,long seq, long limit) {
        initSDK(customConfig);
        List list = new ArrayList();
        long slice = Finance.NewSlice();
        long ret = Finance.GetChatData(sdk, seq, limit, "", "",timeout,slice);
        if (ret != 0) {
            logger.warn("获取存档消息 " + ret);
        } else {
            String content = Finance.GetContentFromSlice(slice);
            JsonArray jsonElements = JsonParser.parseString(content).getAsJsonObject().get("chatdata").getAsJsonArray();
            if (null != jsonElements && jsonElements.size() > 0) {
                ChatModel chatModel = null;
                long newSlice = Finance.NewSlice();
                for (JsonElement jsonElement : jsonElements) {
                    try {
                        JsonObject jsonObject = jsonElement.getAsJsonObject();
                        long LocalSEQ = jsonObject.get("seq").getAsLong();
                        String key = com.work.wx.util.BackUpUtil.decryptData(jsonObject.get("encrypt_random_key").getAsString());
                        int i = Finance.DecryptData(sdk, key, jsonObject.get("encrypt_chat_msg").getAsString(), newSlice);
                        if (i == 0) {
                            String msg = Finance.GetContentFromSlice(newSlice);
                            msg.replace("\"id\"", "table_id");
                            logger.debug(msg);
                            chatModel = new Gson().fromJson(msg,ChatModel.class);
                            chatModel.setCorpId(customConfig.getCorp());
                            chatModel.setSeq(LocalSEQ);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    list.add(chatModel);
                }
            }
            chatServer.insertAll(list);
        }
        Finance.FreeSlice(slice);
        return list.size() == limit ? true:false;
    }




    /**
     * @todo
     * @author wuhen
     * @param customConfig :
     * @param gridFSUploadStream :
     * @param indexbuf :
     * @param sdkField :
     * @returns boolean
     * @throws
     * @date 2020/2/11 15:15
     */
    public static boolean getMediaData(CustomConfig customConfig, GridFSUploadStream gridFSUploadStream,
                                       String indexbuf, String sdkField) {
        initSDK(customConfig);
        long slice = Finance.NewMediaData();
        long ret = Finance.GetMediaData(sdk, indexbuf, sdkField, "", "",timeout,slice);
        if (ret != 0) {
            logger.warn("获取媒体数据 " + ret);
        } else {
            logger.debug("upload media  "+sdkField);
            byte[] b = Finance.GetData(slice);
            boolean isFinish = Finance.IsMediaDataFinish(slice) > 0;
            String outIndex = Finance.GetOutIndexBuf(slice);
            gridFSUploadStream.write(b);
            b = null;
            Finance.FreeMediaData(slice);
            if (isFinish) {
                logger.debug("upload media finish "+sdkField);
                gridFSUploadStream.flush();
                gridFSUploadStream.close();
            } else {
                getMediaData(customConfig,gridFSUploadStream,outIndex, sdkField);
            }
            return true;
        }
        return false;
    }




}
