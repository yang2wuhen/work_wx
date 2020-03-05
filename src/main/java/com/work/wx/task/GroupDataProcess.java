/*
 * work_wx
 * wuhen 2020/2/19.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.task;

import com.google.gson.*;
import com.work.wx.config.CustomConfig;
import com.work.wx.config.RequestUtil;
import com.work.wx.controller.modle.GroupModel;
import com.work.wx.server.GroupServer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupDataProcess {
    private final static Logger logger = LoggerFactory.getLogger(GroupDataProcess.class);

    /**
     * @todo
     * @author wuhen
     * @param groupServer :
     * @param userId :
     * @param extendToken :
     * @returns void
     * @throws
     * @date 2020/2/19 20:13
     */
    public static void groupGetChatId(GroupServer groupServer,String corpId,String userId,String extendToken) {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/groupchat/list";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status_filter", 0);
        jsonObject.addProperty("offset", 0);
        jsonObject.addProperty("limit", 1000);
        JsonObject subJsonObject = new JsonObject();
        JsonArray jsonElements = new JsonArray();
        jsonElements.add(userId);
        subJsonObject.add("userid_list",jsonElements);
        jsonObject.add("owner_filter", subJsonObject);
        try {
            String response =  new RequestUtil().requestJsonPost(BASE_ADDRESS,extendToken,jsonObject.toString());
            if (StringUtils.isNotEmpty(response)) {
                JsonObject resultRes = JsonParser.parseString(response).getAsJsonObject();
                if (resultRes.get("errcode").getAsInt() == 0) {
                    JsonArray jsonArray = resultRes.get("group_chat_list").getAsJsonArray();
                    if (null != jsonArray && jsonArray.size() > 0) {
                        for (JsonElement jsonElement : jsonArray) {
                            JsonObject object = jsonElement.getAsJsonObject();
                            String chatId = object.get("chat_id").getAsString();
                            logger.debug("get chat room detail from chat_id is "+chatId);
                            int status = object.get("status").getAsInt();
                            groupChatProcess(groupServer,chatId,status, corpId, extendToken);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @todo
     * @author wuhen
     * @param groupServer :
     * @param chatId :
     * @param status :
     * @param token :
     * @returns void
     * @throws
     * @date 2020/2/19 20:13
     */
    private static void groupChatProcess(GroupServer groupServer,String chatId, int status, String corpId, String token) {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/groupchat/get";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("chat_id", chatId);
        try {
            String response =  new RequestUtil().requestJsonPost(BASE_ADDRESS, token,jsonObject.toString());
            if (StringUtils.isNotEmpty(response)) {
                JsonObject resultRes = JsonParser.parseString(response).getAsJsonObject();
                if (resultRes.get("errcode").getAsInt() == 0) {
                    logger.debug(response);
                    GroupModel groupModel = new Gson().fromJson(resultRes.get("group_chat").getAsJsonObject(), GroupModel.class);
                    groupModel.setCorp(corpId);
                    groupModel.setStatus(status);
                    groupModel.setInsertTime(System.currentTimeMillis());
                    GroupModel queryGroupModel = new GroupModel(corpId,chatId);
                    groupServer.insertUpdateGroupModel(queryGroupModel, groupModel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
