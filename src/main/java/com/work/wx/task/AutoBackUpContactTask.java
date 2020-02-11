/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.task;

import com.google.gson.*;
import com.work.wx.config.CustomConfig;
import com.work.wx.config.RequestUtil;
import com.work.wx.controller.api.token.ContactAccessToken;
import com.work.wx.controller.modle.ChatModel;
import com.work.wx.controller.modle.ContactModel;
import com.work.wx.server.ChatServer;
import com.work.wx.server.ContactServer;
import com.work.wx.server.TokenServer;
import org.apache.catalina.util.ParameterMap;
import org.apache.commons.lang3.StringUtils;
import org.attoparser.util.TextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AutoBackUpContactTask {
    private final static Logger logger = LoggerFactory.getLogger(AutoBackUpContactTask.class);

    private TokenServer tokenServer;
    private CustomConfig customConfig;
    private ContactServer contactServer;

    @Autowired
    public void setCustomConfig(CustomConfig customConfig) {
        this.customConfig = customConfig;
    }

    @Autowired
    public void setTokenServer(TokenServer tokenServer) {
        this.tokenServer = tokenServer;
    }

    @Autowired
    public void setContactServer(ContactServer contactServer) {
        this.contactServer = contactServer;
    }

    private String getToken() {
        return new ContactAccessToken().getContactAccessToken(tokenServer,customConfig);
    }

    /**
     * @todo 自动备份通讯录
     * @author wuhen
     * @returns void
     * @throws
     * @date 2020/1/27 13:14
     */
    @Async
    @Scheduled(fixedRate = 1000*60*60*2, initialDelay = 1000*60)
    public void backupWeChat() {
        String BASE_ADDRESS = "https://qyapi.weixin.qq.com/cgi-bin/user/list";
        ParameterMap parameterMap = new ParameterMap();
        parameterMap.put("department_id",1);
        parameterMap.put("fetch_child",1);
        try {
            String response =  new RequestUtil().requestGet(BASE_ADDRESS, getToken(),parameterMap);
            if (StringUtils.isNotEmpty(response)) {
                JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
                int status = jsonObject.get("errcode").getAsInt();
                if (status == 0) {
                    JsonArray jsonArray = jsonObject.get("userlist").getAsJsonArray();
                    List list = new ArrayList();
                    ContactModel contactModel = null;
                    for (JsonElement jsonElement : jsonArray) {
                        contactModel = new Gson().fromJson(jsonElement, ContactModel.class);
                        contactModel.setCorp(customConfig.getCorp());
                        list.add(contactModel);
                    }
                    contactServer.insertAll(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
