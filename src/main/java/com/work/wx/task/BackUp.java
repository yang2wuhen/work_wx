/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.task;

import com.google.gson.*;
import com.tencent.wework.ChatModel;
import com.tencent.wework.Finance;
import com.work.wx.util.BackUpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class BackUp  {
    private final static Logger logger = LoggerFactory.getLogger(BackUp.class);
    private static long sdk = 0;
    private static long timeout = 10 * 60 * 1000;


    private static void initSDK () {
        if (sdk == 0) {
            sdk = Finance.NewSdk();
            Finance.Init(sdk,"wweb009196f1b9425a","247tQldgjRdbDZS-vguzKHHBlfpP60vMunPqS9mqpBs");
        }
    }


    /**
     * @todo
     * @author wuhen
     * @param seq :
     * @param limit :
     * @returns java.util.List
     * @throws
     * @date 2020/1/16 16:04
     */
    public static boolean getChatData(long seq, long limit) {
        initSDK();
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


    /**
     * @todo
     * @author wuhen
     * @param seq :
     * @param limit :
     * @returns java.lang.String
     * @throws
     * @date 2020/1/16 16:04
     */
    public static String getEncryptChatData(long seq, long limit) {
        initSDK();
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


    /**
     * @todo
     * @author wuhen
     * @param indexbuf :
     * @param sdkField :
     * @returns long
     * @throws
     * @date 2020/1/16 16:04
     */
    public static long getMediaData(String indexbuf, String sdkField) {
        initSDK();
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
