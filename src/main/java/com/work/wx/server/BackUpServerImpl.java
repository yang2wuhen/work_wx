/*
 * work_wx
 * wuhen 2020/1/2.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.server;

import com.google.gson.*;
import com.tencent.wework.ChatModel;
import com.tencent.wework.Finance;
import com.tencent.wework.util.BackUpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class BackUpServerImpl implements BackUpServer {
    private final static Logger logger = LoggerFactory.getLogger(BackUpServerImpl.class);


    @Override
    public List getChatData(long seq, long limit, long timeout) {
        long sdk = Finance.NewSdk();
        Finance.Init(sdk,"wweb009196f1b9425a","247tQldgjRdbDZS-vguzKHHBlfpP60vMunPqS9mqpBs");
        List list = new ArrayList();
        long slice = Finance.NewSlice();
        long ret = Finance.GetChatData(sdk, seq, limit, "", "",timeout,slice);
        if (ret != 0) {
            logger.warn("getchatdata ret " + ret);
        } else {
            String content = Finance.GetContentFromSlice(slice);
            logger.error(content);
            JsonArray jsonElements = JsonParser.parseString(content).getAsJsonObject().get("chatdata").getAsJsonArray();
            if (null != jsonElements && jsonElements.size() > 0) {
                ChatModel chatModel = null;
                long newSlice = Finance.NewSlice();
                for (JsonElement jsonElement : jsonElements) {
                    try {
                        chatModel = new Gson().fromJson(jsonElement, ChatModel.class);
                        String key = BackUpUtil.decryptData(chatModel.getEncrypt_random_key());
                        logger.debug(key);
                        int i = Finance.DecryptData(sdk, key, chatModel.getEncrypt_chat_msg(), newSlice);
                        if (i == 0) {
                            String msg = Finance.GetContentFromSlice(newSlice);
                            logger.debug(msg);
                            chatModel.setEncrypt_chat_msg(msg);
                        } else {
                            logger.error("解析密文错误");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    chatModel.getEncrypt_chat_msg();
                    list.add(chatModel);
                }
            }
            logger.debug("content is " + content);
        }
        Finance.FreeSlice(slice);
        return list;
    }


    public String getEncryptChatData(long seq, long limit, long timeout) {
        long sdk = Finance.NewSdk();
        Finance.Init(sdk,"wweb009196f1b9425a","247tQldgjRdbDZS-vguzKHHBlfpP60vMunPqS9mqpBs");
        String content = "";
        long slice = Finance.NewSlice();
        long ret = Finance.GetChatData(sdk, seq, limit, "", "",timeout,slice);
        if (ret != 0) {
            logger.warn("getchatdata ret " + ret);
        } else {
            content = Finance.GetContentFromSlice(slice);
            logger.error(content);
            logger.debug("content is " + content);
        }
        Finance.FreeSlice(slice);
        return content;
    }



    @Override
    public long getMediaData(String indexbuf, String sdkField,long timeout) {
        long sdk = Finance.NewSdk();
        Finance.Init(sdk,"wweb009196f1b9425a","247tQldgjRdbDZS-vguzKHHBlfpP60vMunPqS9mqpBs");
        long slice = Finance.NewSlice();
        long ret = Finance.GetMediaData(sdk, indexbuf, sdkField, "", "",timeout,slice);
        if (ret != 0) {
            logger.error("getchatdata ret " + ret);
        } else {
            byte[] b = Finance.GetData(slice);
            logger.debug("media size is  " + b.length);
        }
        Finance.FreeSlice(slice);
        return slice;
    }






}
